package br.com.soapboxrace.bo;

import br.com.soapboxrace.achievements.AchievementRankReward;
import br.com.soapboxrace.achievements.AchievementReward;
import br.com.soapboxrace.achievements.RewardItem;
import br.com.soapboxrace.achievements.RewardRandomness;
import br.com.soapboxrace.bo.legacy.LegacyPersonaBO;
import br.com.soapboxrace.dao.factory.*;
import br.com.soapboxrace.definition.ShoppingCartPurchaseResult;
import br.com.soapboxrace.jaxb.*;
import br.com.soapboxrace.jaxb.AchievementDefinitionPacketType.AchievementRankPacket;
import br.com.soapboxrace.jpa.*;
import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles achievement loading and reward generation.
 *
 * @author leorblx
 */
public class AchievementsBO
{
    private final IAchievementDao achievementDao = DaoFactory.getAchievementDao();
    private final IAchievementRankDao achievementRankDao = DaoFactory.getAchievementRankDao();
    private final IPersonaAchievementDao personaAchievementDao = DaoFactory.getPersonaAchievementDao();
    private final IPersonaAchievementRankDao personaAchievementRankDao = DaoFactory.getPersonaAchievementRankDao();
    private final IAchievementRewardDao achievementRewardDao = DaoFactory.getAchievementRewardDao();

    private final IBadgeDefinitionDao badgeDefinitionDao = DaoFactory.getBadgeDefinitionDao();
    
    private final LegacyPersonaBO personaBO = new LegacyPersonaBO();

    public AchievementsPacketType loadAll(PersonaEntity persona)
    {
        Preconditions.checkNotNull(persona, "persona");

        // Definitions
        AchievementsPacketType packet = new AchievementsPacketType();
        List<AchievementDefinitionPacketType> achievementPackets = new ArrayList<>();
        List<BadgeDefinitionPacketType> badgePackets = badgeDefinitionDao.getAll().stream().map(BadgeDefinitionEntity::toPacket).collect(Collectors.toList());

        // Loading data
        List<AchievementDefinitionEntity> achievements = achievementDao.getAll();

        for (AchievementDefinitionEntity achievement : achievements) {
            AchievementDefinitionPacketType achievementPacket = new AchievementDefinitionPacketType();
            achievementPacket.setId(achievement.getId());
            achievementPacket.setStatConversion(achievement.getStatConversion());
            achievementPacket.setBadgeDefinitionId(achievement.getBadgeDefinition().getId());
            achievementPacket.setProgressText(achievement.getProgressText());
            achievementPacket.setVisible(true);
            achievementPacket.setAchievementRankPackets(new ArrayList<>());

            // Persona-specific
            PersonaAchievementEntity personaAchievement = personaAchievementDao.getForPersona(persona, achievement);

            if (personaAchievement != null) {
                achievementPacket.setCurrentValue(personaAchievement.getCurrentValue());
                achievementPacket.setCanProgress(personaAchievement.isCanProgress());
            } else {
                achievementPacket.setCurrentValue(0L);
                achievementPacket.setCanProgress(true);
            }

            // Loading ranks
            for (AchievementRankEntity achievementRank : achievement.getRanks()) {
                AchievementRankPacket rankPacket = achievementRank.toBasePacket();

                // Get persona rank info
                PersonaAchievementRankEntity personaRank = personaAchievementRankDao.getForPersona(persona, achievement, achievementRank);

                if (personaRank == null) {
                    // Persona doesn't have this rank yet
                    rankPacket.setAchievedOn("0001-01-01T00:00:00");
                    rankPacket.setState("Locked");
                } else {
                    // Persona does have this rank
                    rankPacket.setAchievedOn(personaRank.getAchievedOn());
                    rankPacket.setState(personaRank.getState());
                }

                achievementPacket.getAchievementRankPackets().add(rankPacket);
            }

            achievementPackets.add(achievementPacket);
        }

        // Finalize
        packet.setBadgeDefinitionPackets(badgePackets);
        packet.setAchievementDefinitionPackets(achievementPackets);
        packet.setPersonaId(persona.getId());

        return packet;
    }

    public AchievementCommerceResultTransType redeemReward(PersonaEntity persona, AchievementRankEntity rank)
    {
        Preconditions.checkNotNull(persona, "persona");
        Preconditions.checkNotNull(rank, "rank");

        AchievementDefinitionEntity achievement = rank.getAchievementDefinition();

        PersonaAchievementRankEntity personaRank = personaAchievementRankDao.getForPersona(persona, achievement, rank);
        Preconditions.checkNotNull(personaRank, "Persona doesn't have rank");
        Preconditions.checkState(personaRank.getState().equals("RewardWaiting"), "Expected state to be RewardWaiting, was %s", personaRank.getState());

        AchievementCommerceResultTransType commerce = new AchievementCommerceResultTransType();
        commerce.setAchievementRankId(rank.getId());
        commerce.setVisualStyle(rank.getRewardVisualStyle());

        final List<CommerceItemTransType> commerceItems = new ArrayList<>();

        AchievementRewardEntity achievementRewardEntity = achievementRewardDao.findByAchievementIdentifier(achievement.getFriendlyIdentifier());
        PersonaInventoryEntity inventoryEntity = DaoFactory.getPersonaInventoryDao().getForPersona(persona);

        InventoryItemsType inventoryItemsType = new InventoryItemsType();
        PurchasedCarsType purchasedCarsType = new PurchasedCarsType();
        List<InventoryItemTransType> inventoryItems = new ArrayList<>();

        if (achievementRewardEntity != null) {
            AchievementReward reward = achievementRewardEntity.getReward();

            if (reward != null) {
                List<AchievementRankReward> rankRewards = reward.getRanks();

                if (rankRewards.size() > 0) {
                    AchievementRankReward rankReward = rankRewards.stream().filter(achievementRankReward -> achievementRankReward.getRank() == rank.getRank())
                            .findFirst()
                            .orElse(null);
                    System.out.println("rankReward=" + rankReward);
                    if (rankReward != null) {
                        if (rankReward.getItems() != null && !rankReward.getItems().isEmpty()) {
                            for (RewardItem rewardItem : rankReward.getItems()) {
                                boolean use = true;

                                if (
                                        rewardItem.getRandom() != null
                                                && rankReward.getItems().size() > 1
                                                && rankReward.getItems().stream().map(RewardItem::getRandom).mapToDouble(RewardRandomness::getChance).sum() == 1) {
                                    // only use chance if there are:
                                    // (a) more than 1 item
                                    // (b) chances adding up to 1
                                    use = new Random().nextDouble() < rewardItem.getRandom().getChance();
                                }

                                ProductEntity product = DaoFactory.getProductDao().findByProductId(rewardItem.getId());

                                if (product == null) {
                                    System.out.println("[Achievements] Product " + rewardItem.getId() + " not found.");
                                    continue;
                                } else if (!use) {
                                    System.out.println("[Achievements] Not using " + rewardItem.getId());
                                    continue;
                                }

                                CommerceItemTransType commerceItemTransType = new CommerceItemTransType();
                                commerceItemTransType.setHash(product.getHash());
                                commerceItemTransType.setTitle(rewardItem.getTitle());

                                commerceItems.add(commerceItemTransType);

                                if (product.getProductType().equalsIgnoreCase("PRESETCAR")) {
                                    final OwnedCarEntity ownedCarEntity = personaBO.purchaseCar(persona.getId(), product.getProductId());
                                    final OwnedCarTransType ownedCarTransType = new OwnedCarTransType();
                                    
                                    ownedCarTransType.setCustomCar(ownedCarEntity.getCustomCar().getCustomCarType());
                                    ownedCarTransType.setDurability(ownedCarEntity.getDurability());
                                    ownedCarTransType.setExpirationDate(ownedCarEntity.getExpirationDate());
                                    ownedCarTransType.setHeatLevel(ownedCarEntity.getHeatLevel());
                                    ownedCarTransType.setOwnershipType(ownedCarEntity.getOwnershipType());
                                    ownedCarTransType.setUniqueCarId(ownedCarEntity.getUniqueCarId());
                                    
                                    purchasedCarsType.setOwnedCarTrans(ownedCarTransType);
                                } else {
                                    PersonaInventoryItemEntity inventoryItemEntity = DaoFactory.getPersonaInventoryDao()
                                            .createOrUpdate(inventoryEntity, product.getHash(), (long) rewardItem.getCount());
                                    inventoryItems.add(inventoryItemEntity.toInventoryItem());
                                }

                                if (use)
                                    break;
                            }
                        } else if (rankReward.getInfo() != null) {
                            // dynamic
                            JsonObject info = rankReward.getInfo();

                            if (info.has("style")) {
                                String style = info.get("style").getAsString();

                                switch (style) {
                                    case "skillpack":
                                    case "silver":
                                        List<Integer> amounts = Arrays.asList(10, 15, 25, 30, 20, 35, 40);

                                        for (int i = 0; i < 5; i++) {
                                            boolean skillMod = (i == 2 || i == 4);
                                            ProductEntity product;

                                            do {
                                                product = DaoFactory.getProductDao().selectRandom(skillMod ? "SKILLMODPART" : "POWERUP");
                                            } while (shouldRegen(product, commerceItems));

                                            CommerceItemTransType commerceItemTransType = new CommerceItemTransType();
                                            commerceItemTransType.setTitle(product.getProductTitle());
                                            commerceItemTransType.setHash(product.getHash());

                                            commerceItems.add(commerceItemTransType);

                                            if (!skillMod) {
                                                PersonaInventoryItemEntity inventoryItemEntity = DaoFactory.getPersonaInventoryDao()
                                                        .createOrUpdate(inventoryEntity, product.getHash(), (long) new Random().nextInt(amounts.size()));
                                                inventoryItems.add(inventoryItemEntity.toInventoryItem());
                                            }
                                        }

                                        break;
                                    default:
                                        break;
                                }
                            } else if (info.has("currencyAmount") && !info.get("currencyAmount").isJsonNull()) {
                                CommerceItemTransType cash = new CommerceItemTransType();
                                cash.setHash(-429893590L);
                                cash.setTitle("GM_CATALOG_00000190," + info.get("currencyAmount").getAsString().replace(",", ""));
                                
                                commerceItems.add(cash);
                                
                                persona.setCash(persona.getCash() + info.get("currencyAmount").getAsInt());
                                persona = DaoFactory.getPersonaDao().save(persona);
                            }
                        }
                    }
                }
            }
        }

        inventoryItemsType.setInventoryItemTrans(inventoryItems);

        commerce.setStatus(ShoppingCartPurchaseResult.aSuccess);
        commerce.setPurchasedCars(purchasedCarsType);
        commerce.setInvalidBasket("");
        commerce.setInventoryItems(inventoryItemsType);

        WalletsType walletsType = new WalletsType();
        WalletTransType cashWallet = new WalletTransType();
        cashWallet.setCurrency("CASH");
        cashWallet.setBalance(persona.getCash());

        walletsType.setWalletTrans(cashWallet);
        commerce.setWallets(walletsType);
        commerce.setCommerceItems(commerceItems);

        personaRank.setState("Completed");
        personaAchievementRankDao.save(personaRank);

        PersonaAchievementEntity personaAchievement = personaAchievementDao.getForPersona(persona, rank.getAchievementDefinition());

        personaAchievement.setCanProgress(rank.getAchievementDefinition().getRanks().indexOf(rank) != rank.getAchievementDefinition().getRanks().size() - 1);

        personaAchievementDao.save(personaAchievement);

        return commerce;

//        CommerceItemTransType cash = new CommerceItemTransType();
//        cash.setHash(-429893590L);
//        cash.setTitle("GM_CATALOG_00000190,1000000");
//        
//        commerce.setCommerceItems(Collections.singletonList(cash));
//        commerce.setStatus(ShoppingCartPurchaseResult.aSuccess);
//        commerce.setPurchasedCars(new PurchasedCarsType());
//        commerce.setInvalidBasket("");
//        commerce.setInventoryItems(new InventoryItemsType());
//        
//        WalletsType walletsType = new WalletsType();
//        WalletTransType cashWallet = new WalletTransType();
//        cashWallet.setCurrency("CASH");
//        cashWallet.setBalance(5100000);
//        
//        walletsType.setWalletTrans(cashWallet);
//        commerce.setWallets(walletsType);
//
//        
//        return commerce;
    }

    private boolean shouldRegen(ProductEntity productEntity, List<CommerceItemTransType> commerceItems)
    {
        for (CommerceItemTransType item : commerceItems)
            if (item.getHash() == productEntity.getHash())
                return true;

        return false;
    }
}
