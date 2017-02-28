package br.com.soapboxrace.engine;

import br.com.soapboxrace.bo.AchievementsBO;
import br.com.soapboxrace.bo.legacy.LegacyPersonaBO;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.definition.ServerExceptions;
import br.com.soapboxrace.jaxb.CommerceItemTransType;
import br.com.soapboxrace.jaxb.util.MarshalXML;
import br.com.soapboxrace.jpa.AchievementDefinitionEntity;
import br.com.soapboxrace.jpa.AchievementRankEntity;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.jpa.ProductEntity;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Achievements extends Router
{
    private final LegacyPersonaBO personaBO = new LegacyPersonaBO();
    private final AchievementsBO achievementsBO = new AchievementsBO();
    private final List<CommerceItemTransType> presetItems = Lists.newArrayList(
            // Powerups
            new CommerceItemTransType("Run Flat Tires", -537557654L),
            new CommerceItemTransType("Traffic Magnet", 125509666),
            new CommerceItemTransType("Instant Cooldown", -1692359144),
            new CommerceItemTransType("Nitrous", -1681514783),

            // Visual
            new CommerceItemTransType("DEVELOPER License Plate", 3241828192L),
            new CommerceItemTransType("HIGHWAY PATROL License Plate", 3380622801L),

            // Cars
            new CommerceItemTransType("HYPERCYCLE'S EVORA", -1584408307),
            new CommerceItemTransType("NFS_FAN'S AVENTADOR", -1825323762),
            new CommerceItemTransType("HOT PURSUIT", 1892976782),
            new CommerceItemTransType("T-MOBILE", 1134069115)
    );

    public String help()
    {
        return DaoFactory.getAchievementDao().getAll().stream()
                .map(AchievementDefinitionEntity::toString)
                .collect(Collectors.joining("\n"));
    }

    public String redeemreward() throws ServerExceptions.EngineException
    {
        if (getLoggedPersonaId() == -1L || getLoggedPersonaId() == null)
            throw new ServerExceptions.EngineException("No persona!");
        PersonaEntity persona = DaoFactory.getPersonaDao().findById(getLoggedPersonaId());
        Long rankId = Long.valueOf(getParam("achievementRankId"));
        AchievementRankEntity rank = (AchievementRankEntity) DaoFactory.getAchievementRankDao().findById(rankId);

        if (rank == null)
            throw new ServerExceptions.EngineException("Invalid rank ID!");
        return MarshalXML.marshal(achievementsBO.redeemReward(persona, rank));
    }

//	public String redeemreward() throws ServerExceptions.EngineException
//	{
//		if (getLoggedPersonaId() == -1L || getLoggedPersonaId() == null)
//			throw new ServerExceptions.EngineException("No persona!");
//		PersonaEntity persona = DaoFactory.getPersonaDao().findById(getLoggedPersonaId());
//		Long rankId = Long.valueOf(getParam("achievementRankId"));
//		AchievementRankEntity rank = (AchievementRankEntity) DaoFactory.getAchievementRankDao().findById(rankId);
//		
//		if (rank == null)
//			throw new ServerExceptions.EngineException("Invalid rank ID!");
//		List<PersonaAchievementEntity> personaAchievement = DaoFactory.getPersonaAchievementDao().getForPersona(persona, rank.getAchievementDefinition());
//		PersonaAchievementRankEntity personaRank = DaoFactory.getPersonaAchievementRankDao().getForPersona(persona, rank.getAchievementDefinition(), rank.getId());
//		
//		if (personaAchievement.isEmpty())
//			throw new ServerExceptions.EngineException("Persona doesn't have any data for this achievement");
//		if (personaRank == null)
//			throw new ServerExceptions.EngineException("Persona doesn't have this rank yet");
//		AchievementCommerceResultTransType achievementCommerce = new AchievementCommerceResultTransType();
//		achievementCommerce.setAchievementRankId(rankId.intValue());
//		achievementCommerce.setVisualStyle(rank.getRewardVisualStyle());
//
//		// -- Wallet
//		WalletTransType walletTransType = new WalletTransType();
//		walletTransType.setBalance(persona.getCash());
//		walletTransType.setCurrency("CASH");
//
//		WalletsType walletsType = new WalletsType();
//		walletsType.setWalletTrans(walletTransType);
//
//		achievementCommerce.setWallets(walletsType);
//
//		// Achievement rewards
//		List<CommerceItemTransType> items = new ArrayList<>();
//		
//		for (int i = 0; i < 5; i++) {
//			CommerceItemTransType item;
//			
//			do {
//			    item = generateRandomItem();
//            } while (items.contains(item));
//			
//			items.add(item);
//		}
//		
//		achievementCommerce.setCommerceItems(items);
//		
////		CommerceItemTransType item = new CommerceItemTransType();
////		item.setHash(-1681514783L);
////		item.setTitle("Test item 1");
////		
////		CommerceItemTransType item2 = new CommerceItemTransType();
////		item2.setHash(-1681514783L);
////		item2.setTitle("Test item 2");
////		
////		CommerceItemTransType item3 = new CommerceItemTransType();
////		item3.setHash(-933831183L);
////		item3.setTitle("Test item 3");
////		
////		achievementCommerce.setCommerceItems(new ArrayList<>());
////		achievementCommerce.getCommerceItems().addAll(Arrays.asList(item, item2, item3));
//
//		achievementCommerce.setPurchasedCars(new PurchasedCarsType());
//		achievementCommerce.setInvalidBasket("");
//		achievementCommerce.setInventoryItems(new InventoryItemsType());
//		achievementCommerce.setStatus(ShoppingCartPurchaseResult.aSuccess);
//		
//		String xml = MarshalXML.marshal(achievementCommerce);
//		
//		System.out.println(xml);
//		
//		personaRank.setState("Completed");
//		DaoFactory.getPersonaAchievementRankDao().save(personaRank);
//		
//		if (personaRank.getRank().getRank() == personaRank.getAchievement().getRanks().size()) {
//			personaAchievement.get(0).setCanProgress(false);
//			DaoFactory.getPersonaAchievementDao().save(personaAchievement.get(0));
//		}
//		
//		return xml;
//	}


    public String loadall()
    {
        Long personaId = getParam("personaId") != null ? Long.valueOf(getParam("personaId")) : getLoggedPersonaId();

        return MarshalXML.marshal(achievementsBO.loadAll(DaoFactory.getPersonaDao().findById(personaId)));
    }
//	public String loadall() {
//		AchievementsPacketType achievementsPacketType = new AchievementsPacketType();
//		Long personaId = getParam("personaId") != null ? Long.valueOf(getParam("personaId")) : getLoggedPersonaId();
//		achievementsPacketType.setPersonaId(personaId);
//
//		PersonaEntity persona = DaoFactory.getPersonaDao().findById(personaId);
//		IPersonaAchievementDao personaAchievementDao = DaoFactory.getPersonaAchievementDao();
//		
////		List<PersonaAchievementEntity> personaAchievements = personaAchievementDao.getForPersona(persona);
//		List<AchievementDefinitionPacketType> achievementPackets = new ArrayList<>();
//		List<BadgeDefinitionPacketType> badgePackets = new ArrayList<>();
//		
//		for (BadgeDefinitionEntity badge : DaoFactory.getBadgeDefinitionDao().getAll()) {
//			badgePackets.add(badge.toPacket());
//		}
//		
//		for (AchievementDefinitionEntity achievement : DaoFactory.getAchievementDao().getAll()) {
//			AchievementDefinitionPacketType packet = new AchievementDefinitionPacketType();
//			
//			List<AchievementDefinitionPacketType.AchievementRankPacket> ranks = new ArrayList<>();
//			
//			for (AchievementRankEntity rank : DaoFactory.getAchievementRankDao().getForAchievement(achievement)) {
//                AchievementDefinitionPacketType.AchievementRankPacket rankPacket = new AchievementDefinitionPacketType.AchievementRankPacket();
//                rankPacket.setId(rank.getId().intValue());
//                
//                PersonaAchievementRankEntity personaRank = DaoFactory.getPersonaAchievementRankDao().getForPersona(persona, achievement, rank.getRank());
//                
//                if (personaRank != null) {
//                    rankPacket.setAchievedOn(personaRank.getAchievedOn());
//                    rankPacket.setState(personaRank.getState());
//                } else {
//                    rankPacket.setAchievedOn("0001-01-01T00:00:00");
//                    rankPacket.setState("Locked");
//                }
//                
//                rankPacket.setPoints(rank.getPoints());
//                rankPacket.setRare(rank.isRare());
//                rankPacket.setRewardDescription(rank.getRewardDescription());
//                rankPacket.setRewardType(rank.getRewardType());
//                rankPacket.setRewardVisualStyle(rank.getRewardVisualStyle());
//                rankPacket.setRank(rank.getRank());
//                rankPacket.setRarity(rank.getRarity());
//                rankPacket.setThresholdValue(rank.getThresholdValue());
//                
//                ranks.add(rankPacket);
//            }
//            
//            List<PersonaAchievementEntity> personaAchievements = DaoFactory.getPersonaAchievementDao().getForPersona(persona, achievement);
//			
//			if (!personaAchievements.isEmpty()) {
//				packet.setCanProgress(personaAchievements.get(0).isCanProgress());
//				packet.setCurrentValue(personaAchievements.get(0).getCurrentValue());
//			} else {
//				packet.setCanProgress(true);
//				packet.setCurrentValue(0);
//			}
//			
//			packet.setAchievementRankPackets(ranks);
//			packet.setBadgeDefinitionId(achievement.getBadgeDefinition().getId().intValue());
//			packet.setId(achievement.getId().intValue());
//			packet.setVisible(achievement.isVisible());
//			packet.setProgressText(achievement.getProgressText());
//			packet.setStatConversion("None");
//			
//			achievementPackets.add(packet);
//		}
//		
//		achievementsPacketType.setAchievementDefinitionPackets(achievementPackets);
//		achievementsPacketType.setBadgeDefinitionPackets(badgePackets);
//		
//		String xml = MarshalXML.marshal(achievementsPacketType);
//		
////		System.out.println(xml);
//		
//		return xml;
//	}

    private CommerceItemTransType generateRandomItem()
    {
        Random random = new Random();

        return presetItems.get(random.nextInt(presetItems.size()));
    }

    private boolean hasTooMany(CommerceItemTransType item, int threshold)
    {
        int count = 0;
        ProductEntity product = DaoFactory.getProductDao().findByHash(item.getHash());

        if (product == null)
            return false;

        for (CommerceItemTransType commerceItem : presetItems) {
            ProductEntity find = DaoFactory.getProductDao().findByHash(commerceItem.getHash());

            if (find == null)
                continue;
            if (find.getProductType().equalsIgnoreCase(product.getProductType()))
                count++;
        }

        return count > threshold;
    }
}
