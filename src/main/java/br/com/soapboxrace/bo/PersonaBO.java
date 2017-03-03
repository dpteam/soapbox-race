package br.com.soapboxrace.bo;

import br.com.soapboxrace.achievements.AchievementUtils;
import br.com.soapboxrace.dao.factory.*;
import br.com.soapboxrace.definition.CarTypes;
import br.com.soapboxrace.definition.PresetExceptions;
import br.com.soapboxrace.definition.ServerExceptions;
import br.com.soapboxrace.definition.ShoppingCartPurchaseResult;
import br.com.soapboxrace.jaxb.*;
import br.com.soapboxrace.jpa.*;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The persona BO.
 * <p>
 * Handles more persona-specific actions, such as:
 * - updating badges (from achievements)
 * - getting carslot info for a persona
 * - purchasing a car for a persona
 * - basket and commerce actions (car dealer/customization)
 * - getting a list of a persona's cars
 * - getting a persona's default car
 * - changing a persona's default car
 * - selling a car that a persona owns
 *
 * @author leorblx
 */
public class PersonaBO
{
    /* DAO declarations */
    private final IOwnedCarDao ownedCarDao = DaoFactory.getOwnedCarDao();
    private final IBasketDefinitionDao basketDefinitionDao = DaoFactory.getBasketDefinitionDao();
    private final IPersonaDao personaDao = DaoFactory.getPersonaDao();
    private final IProductDao productDao = DaoFactory.getProductDao();
    private final IAchievementDao achievementDao = DaoFactory.getAchievementDao();
    private final IPersonaAchievementDao personaAchievementDao = DaoFactory.getPersonaAchievementDao();

    /**
     * Update a persona's badges.
     *
     * @param personaId   The ID of the persona whose badges should be updated.
     * @param badgeBundle The new badge bundle. The badge bundle consists of multiple badge inputs.
     */
    public void updateBadges(long personaId, BadgeBundleType badgeBundle)
    {
        PersonaEntity persona = DaoFactory.getPersonaDao().findById(personaId);
        Preconditions.checkNotNull(persona, "persona");

        List<BadgePacketType> badgePacketTypes = new ArrayList<>();

        for (BadgeInputType input : badgeBundle.getBadgeInputs()) {
            BadgeDefinitionEntity badge = DaoFactory.getBadgeDefinitionDao().findById((long) input.getBadgeDefinitionId());
            if (badge == null) continue;

            AchievementDefinitionEntity achievement = DaoFactory.getAchievementDao().findByBadgeId(badge.getId());

            if (achievement == null) continue;

            BadgePacketType packet = new BadgePacketType();
            packet.setSlotId(input.getSlotId());
            packet.setBadgeDefinitionId(badge.getId().intValue());

            List<AchievementRankEntity> ranks = DaoFactory.getPersonaAchievementRankDao()
                    .getForPersona(persona, achievement)
                    .stream()
                    .filter(pr -> !pr.getState().equals("Locked"))
                    .map(PersonaAchievementRankEntity::getRank)
                    .collect(Collectors.toList());

            if (ranks.isEmpty()) {
                packet.setRare(false);
                packet.setRarity(0f);
                packet.setAchievementRankId(-1);
            } else {
                packet.setRare(ranks.get(ranks.size() - 1).isRare());
                packet.setRarity(ranks.get(ranks.size() - 1).getRarity());
                packet.setAchievementRankId(ranks.get(ranks.size() - 1).getId());
            }

            badgePacketTypes.add(packet);
        }

        persona.setBadges(badgePacketTypes);
        DaoFactory.getPersonaDao().save(persona);
    }

    /**
     * Get carslot information for a persona.
     *
     * @param personaId The ID of the persona to retrieve carslot data for.
     * @return The carslot information for the persona.
     */
    public CarSlotInfoTrans carslots(long personaId)
    {
        // Get the persona's owned cars.
        final PersonaEntity personaEntity = personaDao.findById(personaId);
        final List<OwnedCarEntity> ownedCars = ownedCarDao.findByIdPersona(personaId);

        CarSlotInfoTrans carSlotInfoTrans = new CarSlotInfoTrans();
        CarsOwnedByPersonaList carsOwnedByPersonaList = new CarsOwnedByPersonaList();
        carSlotInfoTrans.setCarsOwnedByPersonaList(carsOwnedByPersonaList);
        if (ownedCars.size() > 0) {
            carsOwnedByPersonaList.setOwnedCarList(ownedCars);
        }

        carSlotInfoTrans.setDefaultOwnedCarIndex(personaEntity.getCurCarIndex());
        carSlotInfoTrans.setOwnedCarSlotsCount(personaEntity.getOwnedCarlist().size());

        // Add carslot product.
        ObtainableSlotsList obtainableSlotsList = new ObtainableSlotsList();

        ArrayList<ProductEntity> productList = new ArrayList<>();
        ProductEntity carSlotProductData = productDao.findByProductId("SRV-CARSLOT");

        productList.add(carSlotProductData);
        obtainableSlotsList.setProductList(productList);

        carSlotInfoTrans.setObtainableSlots(obtainableSlotsList);

        // All done here.

        return carSlotInfoTrans;
    }

    /**
     * Purchase a car for a persona.
     *
     * @param personaId The ID of the persona to purchase the car for.
     * @param productId The ID of the car product.
     * @return A new owned car entity that belongs to the persona.
     */
    public OwnedCarEntity purchaseCar(long personaId, String productId)
    {
        // Data retrieval.
        PersonaEntity personaEntity = personaDao.findById(personaId);
        BasketDefinitionEntity basketDefinition = basketDefinitionDao.findByProductId(productId);

        if (basketDefinition != null) {
            CustomCarType customCar = basketDefinition.getOwnedCarTrans().getCustomCar();

            OwnedCarEntity ownedCarEntity = new OwnedCarEntity();
            CustomCarEntity customCarEntity = new CustomCarEntity();
            customCarEntity.setApiId(customCar.getApiId());
            customCarEntity.setBaseCarId(customCar.getBaseCarId());
            customCarEntity.setCarClassHash(customCar.getCarClassHash());
            customCarEntity.setPaints(customCar.getPaints());
            customCarEntity.setPerformanceParts(customCar.getPerformanceParts());
            customCarEntity.setPhysicsProfileHash(customCar.getPhysicsProfileHash());
            customCarEntity.setRating(customCar.getRating());
            customCarEntity.setResalePrice(customCar.getResalePrice());
            customCarEntity.setSkillModParts(customCar.getSkillModParts());
            customCarEntity.setSkillModSlotCount(customCar.getSkillModSlotCount());
            customCarEntity.setVinyls(customCar.getVinyls());
            customCarEntity.setVisualParts(customCar.getVisualParts());
            customCarEntity.setParentOwnedCarTrans(ownedCarEntity);

            ownedCarEntity.setCustomCar(customCarEntity);
            ownedCarEntity.setDurability((short) 100);
            ownedCarEntity.setExpirationDate(null);
            ownedCarEntity.setHeatLevel((short) 1);
            ownedCarEntity.setOwnershipType("PresetCar");
            ownedCarEntity.setPersona(personaEntity);

            ownedCarDao.save(ownedCarEntity);

            String make = CarTypes.getMakeFromProductId(productId);

            if (!make.equals("no make")) {
                // check for an actual make
                String achievement = CarTypes.makeToAchievement.get(make);

                AchievementDefinitionEntity achievementDefinitionEntity = achievementDao.findByIdentifier(achievement);

                if (achievementDefinitionEntity != null) {
                    personaAchievementDao.update(
                            personaEntity,
                            achievementDefinitionEntity,
                            1L,
                            updateInfo -> AchievementUtils.broadcastProgress(
                                    personaEntity,
                                    updateInfo.getPersonaAchievement(),
                                    updateInfo.getRanks(),
                                    updateInfo.getScore())
                    );
                }
            }

            return ownedCarEntity;
        }

        return null;
    }

    /**
     * Finalize a commerce action for a persona.
     * Commerce is anything related to customization.
     *
     * @param personaId  The ID of the persona performing the commerce action.
     * @param updatedCar The updated car that was customized.
     * @param productIds The IDs of the products that were purchased.
     * @return A commerce session result.
     */
    public CommerceSessionResultTransType commerce(long personaId, UpdatedCarType updatedCar, String[] productIds) throws ServerExceptions.EngineException
    {
        PersonaEntity personaEntity = personaDao.findById(personaId);

        CommerceSessionResultTransType commerceSessionResultTransType = new CommerceSessionResultTransType();

        // -- Wallet
        WalletTransType walletTransType = new WalletTransType();
        walletTransType.setBalance(personaEntity.getCash());
        walletTransType.setCurrency("CASH");

        WalletsType walletsType = new WalletsType();
        walletsType.setWalletTrans(walletTransType);

        commerceSessionResultTransType.setWallets(walletsType);

        // -- Modify the car on DB
        OwnedCarEntity currentCar = defaultCar(personaEntity.getId());
        currentCar.getCustomCar().setVinyls(updatedCar.getCustomCar().getVinyls());
        currentCar.getCustomCar().setPaints(updatedCar.getCustomCar().getPaints());
        currentCar.getCustomCar().setPerformanceParts(updatedCar.getCustomCar().getPerformanceParts());
        currentCar.getCustomCar().setSkillModParts(updatedCar.getCustomCar().getSkillModParts());
        currentCar.getCustomCar().setVisualParts(updatedCar.getCustomCar().getVisualParts());
        currentCar.setHeatLevel((short) 1);
        currentCar.setOwnershipType("CustomizedCar");

        ownedCarDao.save(currentCar);

        // -- Set the response car
        UpdatedCarType responseCar = new UpdatedCarType();
        responseCar.setCustomCar(currentCar.getCustomCar().getCustomCarType());
        responseCar.setDurability(currentCar.getDurability());
        responseCar.setHeatLevel((short) 1);
        responseCar.setOwnershipType("CustomizedCar");
        responseCar.setUniqueCarId(currentCar.getUniqueCarId());
        commerceSessionResultTransType.setUpdatedCar(responseCar);

        // Currently not important, so we just fill in dummy response
        commerceSessionResultTransType.setInvalidBasket("");
        commerceSessionResultTransType.setInventoryItems(new InventoryItemsType());
        commerceSessionResultTransType.setStatus(ShoppingCartPurchaseResult.aSuccess);

        return commerceSessionResultTransType;
    }

    /**
     * Finalize a basket action for a persona.
     * "Basket" refers to purchasing a car.
     *
     * @param personaId The ID of the persona performing the basket action.
     * @param productId The ID of the car product that the persona is trying to purchase.
     * @return A commerce result
     */
    public CommerceResultTransType basket(long personaId, String productId) throws ServerExceptions.EngineException
    {
        PersonaEntity personaEntity = personaDao.findById(personaId);
        CommerceResultTransType commerceResultTransType = new CommerceResultTransType();
        PurchasedCarsType purchasedCarsType = new PurchasedCarsType();

        // -- Wallet
        WalletTransType walletTransType = new WalletTransType();
        walletTransType.setBalance(personaEntity.getCash());
        walletTransType.setCurrency("CASH");

        WalletsType walletsType = new WalletsType();
        walletsType.setWalletTrans(walletTransType);

        commerceResultTransType.setWallets(walletsType);

        // Currently not important, so we just fill in dummy response
        InventoryItemTransType inventoryItemTransType = new InventoryItemTransType();
        InventoryItemsType inventoryItemsType = new InventoryItemsType();
        inventoryItemsType.setInventoryItemTrans(Collections.singletonList(inventoryItemTransType));

        commerceResultTransType.setCommerceItems("");
        commerceResultTransType.setInvalidBasket("");
        commerceResultTransType.setInventoryItems(inventoryItemsType);

        // -- Set up empty response in case query returns null
        commerceResultTransType.setPurchasedCars(purchasedCarsType);
        commerceResultTransType.setStatus(ShoppingCartPurchaseResult.aFail_itemnotavail);

        BasketDefinitionEntity basketDefinition = basketDefinitionDao.findByProductId(productId);

        if (basketDefinition != null) {
            OwnedCarEntity ownedCarEntity = purchaseCar(personaId, productId);

            ownedCarEntity = (OwnedCarEntity) ownedCarDao.save(ownedCarEntity);
            changeDefaultCar(personaId, ownedCarEntity.getId());

            purchasedCarsType.setOwnedCarTrans(ownedCarEntity.getOwnedCarTransType());
            commerceResultTransType.setPurchasedCars(purchasedCarsType);
            commerceResultTransType.setStatus(ShoppingCartPurchaseResult.aSuccess);
        }
        return commerceResultTransType;
    }

    /**
     * Get a persona's default car.
     * Returns an owned car entity.
     *
     * @param personaId The ID of the persona whose default car should be retrieved.
     * @return The default car of the persona.
     */
    public OwnedCarEntity defaultCar(long personaId) throws ServerExceptions.EngineException
    {
        PersonaEntity personaEntity = personaDao.findById(personaId);
        personaEntity.setId(personaId);
        List<OwnedCarEntity> ownedCarList = ownedCarDao.findByIdPersona(personaId);
        Integer curCarIndex = personaEntity.getCurCarIndex();

        if (ownedCarList.size() > 0) {
            if (curCarIndex >= ownedCarList.size()) {
                curCarIndex--;
                OwnedCarEntity ownedCarEntity = ownedCarList.get(curCarIndex);
                changeDefaultCar(personaId, ownedCarEntity.getId());
            }

            return ownedCarList.get(curCarIndex);
        }

        return null;
    }

    /**
     * Change a persona's default car.
     *
     * @param personaId The ID of the persona whose default car should be changed.
     * @param carId     The ID of the new default car.
     */
    public void changeDefaultCar(long personaId, long carId) throws ServerExceptions.EngineException
    {
        PersonaEntity personaEntity = personaDao.findById(personaId);

        if (personaEntity == null)
            throw PresetExceptions.PERSONA_NOT_FOUND;

        List<OwnedCarEntity> ownedCarList = ownedCarDao.findByIdPersona(personaId);
        int i = 0;

        for (OwnedCarEntity ownedCarEntity : ownedCarList) {
            if (ownedCarEntity.getUniqueCarId() == carId) {
                break;
            }
            i++;
        }

        personaEntity.setCurCarIndex(i);
        personaDao.save(personaEntity);
    }

    /**
     * Get an array of a persona's cars.
     *
     * @param personaId The ID of the target persona.
     * @return An array of the persona's cars.
     */
    public ArrayOfOwnedCarTransType getCars(long personaId) throws ServerExceptions.EngineException
    {
        ArrayOfOwnedCarTransType arrayOfOwnedCarTrans = new ArrayOfOwnedCarTransType();
        PersonaEntity personaEntity = personaDao.findById(personaId);

        if (personaEntity == null)
            throw PresetExceptions.PERSONA_NOT_FOUND;

        arrayOfOwnedCarTrans.setOwnedCarTransList(personaEntity.getOwnedCarlist());

        return arrayOfOwnedCarTrans;
    }

    /**
     * Sell a persona's car.
     *
     * @param personaId The ID of the persona selling the car.
     * @param carId     The ID of the car being sold.
     * @return The persona's new default car.
     */
    public OwnedCarEntity sellCar(long personaId, long carId) throws ServerExceptions.EngineException
    {
        PersonaEntity persona = personaDao.findById(personaId);

        if (persona == null)
            throw PresetExceptions.PERSONA_NOT_FOUND;

        OwnedCarEntity ownedCarEntity = ownedCarDao.findById(carId);

        if (ownedCarEntity == null)
            throw PresetExceptions.CAR_NOT_FOUND;
        else if (!ownedCarEntity.getPersona().getId().equals(personaId))
            throw PresetExceptions.CAR_NOT_OWNED_BY_PERSONA;
        else if (ownedCarEntity.getPersona().getOwnedCarlist().size() == 1)
            throw PresetExceptions.CANT_SELL_LAST_CAR;

        ownedCarDao.del(carId);

        return defaultCar(personaId);
    }
}
