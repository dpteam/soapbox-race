package br.com.soapboxrace.engine;

import br.com.soapboxrace.bo.PersonaBO;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.definition.ServerExceptions;
import br.com.soapboxrace.definition.ServerExceptions.PersonaIdMismatchException;
import br.com.soapboxrace.jaxb.*;
import br.com.soapboxrace.jaxb.util.MarshalXML;
import br.com.soapboxrace.jaxb.util.UnmarshalXML;
import br.com.soapboxrace.jpa.OwnedCarEntity;
import br.com.soapboxrace.jpa.PersonaInventoryEntity;
import br.com.soapboxrace.jpa.PersonaInventoryItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Personas extends Router {

	private PersonaBO personaBO = new PersonaBO();

	private long getPersonaId(boolean isBypass) throws PersonaIdMismatchException {
		String[] targetSplitted = getTarget().split("/");
		Long personaId = Long.valueOf(targetSplitted[4]);
		if (((isBypass || personaId.equals(getLoggedPersonaId()) || getLoggedPersonaId() == -1L)))
			if (getUserId() != -1L && !getSecurityToken().isEmpty() && Router.activeUsers.get(getUserId()).getSecurityToken().equals(getSecurityToken()))
				return personaId;
		throw new ServerExceptions.PersonaIdMismatchException(getLoggedPersonaId(), personaId);
	}

	private long getPersonaId() throws PersonaIdMismatchException {
		return getPersonaId(false);
	}

	private long getDefaultCarId() {
		long carId = 0;
		String[] targetSplitted = getTarget().split("/");
		if (targetSplitted.length == 7) {
			carId = Long.valueOf(targetSplitted[6]);
		}
		return carId;
	}

	public String carslots() throws PersonaIdMismatchException {
		CarSlotInfoTrans carslots = personaBO.carslots(getPersonaId());

//		System.out.println(xml);
		
		return MarshalXML.marshal(carslots);
	}

	public String inventory() throws PersonaIdMismatchException
	{
		InventoryTransType inventoryTransType = new InventoryTransType();
		List<InventoryItemTransType> items = new ArrayList<>();

		Long personaId = getLoggedPersonaId();
		
		PersonaInventoryEntity personaInventoryEntity = DaoFactory.getPersonaInventoryDao().getForPersona(DaoFactory.getPersonaDao().findById(personaId));
		List<PersonaInventoryItemEntity> inventoryItems = personaInventoryEntity.getItems();
		
		for (PersonaInventoryItemEntity inventoryItemEntity : inventoryItems) {
			InventoryItemTransType inventoryItemTransType = new InventoryItemTransType();
			
			inventoryItemTransType.setHash(inventoryItemEntity.getHash());
			inventoryItemTransType.setInventoryId(inventoryItemEntity.getId());
			
			inventoryItemTransType.setEntitlementTag(inventoryItemEntity.getEntitlementTag());
			inventoryItemTransType.setExpirationDate(inventoryItemEntity.getExpirationDate());
			inventoryItemTransType.setVirtualItemType(inventoryItemEntity.getVirtualItemType());
			inventoryItemTransType.setProductId(inventoryItemEntity.getProductId());
			inventoryItemTransType.setRemainingUseCount(inventoryItemEntity.getRemainingUseCount());
			inventoryItemTransType.setResellPrice(inventoryItemEntity.getResalePrice());
			inventoryItemTransType.setStatus(inventoryItemEntity.getStatus());
			inventoryItemTransType.setStringHash(inventoryItemEntity.getStringHash());
			
			items.add(inventoryItemTransType);
		}
		
		InventoryItemsType inventoryItemsType = new InventoryItemsType();
		inventoryItemsType.setInventoryItemTrans(items);
		
		inventoryTransType.setInventoryItems(inventoryItemsType);
		
		inventoryTransType.setPerformancePartsCapacity(personaInventoryEntity.getPerformancePartsCapacity());
		inventoryTransType.setPerformancePartsUsedSlotCount(personaInventoryEntity.getPerformancePartsUsedSlotCount());
		inventoryTransType.setVisualPartsCapacity(personaInventoryEntity.getVisualPartsCapacity());
		inventoryTransType.setVisualPartsUsedSlotCount(personaInventoryEntity.getVisualPartsUsedSlotCount());
		inventoryTransType.setSkillModPartsCapacity(personaInventoryEntity.getSkillModPartsCapacity());
		inventoryTransType.setSkillModPartsUsedSlotCount(personaInventoryEntity.getSkillModPartsUsedSlotCount());

		return MarshalXML.marshal(inventoryTransType);
	}

	public String defaultcar() throws PersonaIdMismatchException, ServerExceptions.EngineException
	{
		long personaId = getPersonaId(true);
		long defaultCarId = getDefaultCarId();
		if (defaultCarId != 0) {
			personaBO.changeDefaultCar(personaId, defaultCarId);
			return "";
		}
		OwnedCarEntity ownedCarEntity = personaBO.defaultCar(personaId);
		if (ownedCarEntity != null) {
			return MarshalXML.marshal(ownedCarEntity);
		}
		return "";
	}

	public String commerce() throws PersonaIdMismatchException, ServerExceptions.EngineException
	{
		String commerceXml = readInputStream();
		System.out.println(commerceXml);
		CommerceSessionTransType commerceSessionTransType = new CommerceSessionTransType();
		commerceSessionTransType = (CommerceSessionTransType) UnmarshalXML.unMarshal(commerceXml, commerceSessionTransType);
		CommerceSessionResultTransType commerceSessionResultTrans = personaBO.commerce(
				getPersonaId(), 
				commerceSessionTransType.getUpdatedCar(), 
				commerceSessionTransType.getBasketTrans()
						.getBasketItems()
						.getBasketItemTrans()
						.stream()
						.map(BasketItemTransType::getProductId)
						.collect(Collectors.toList()).toArray(new String[0])); // HELP
		return MarshalXML.marshal(commerceSessionResultTrans);
	}

	public String baskets() throws PersonaIdMismatchException, ServerExceptions.EngineException
	{
		String basketXml = readInputStream();
		BasketTransType basketTransType = new BasketTransType();
		basketTransType = (BasketTransType) UnmarshalXML.unMarshal(basketXml, basketTransType);
		String productId = basketTransType.getBasketItems().getFirstItem().getProductId();
		CommerceResultTransType commerceResultTrans = personaBO.basket(getPersonaId(), productId);

		final String xml = MarshalXML.marshal(commerceResultTrans);
		System.out.println(xml);
		
		return xml;
	}

	public String cars() throws PersonaIdMismatchException, ServerExceptions.EngineException
	{
		if (getRequest().getMethod().equals("POST")) { // sell car
			String serialNumber = getParam("serialNumber");
			if (serialNumber != null) {
				Long personaId = getPersonaId();
				Long carId = Long.valueOf(serialNumber);
				OwnedCarEntity defaultCar = personaBO.sellCar(personaId, carId);
				return MarshalXML.marshal(defaultCar);
			}
		} else if (getRequest().getMethod().equals("PUT")) {
			long personaId = getPersonaId();
			OwnedCarEntity ownedCarEntity = personaBO.defaultCar(personaId);
			if (ownedCarEntity != null) {
				ownedCarEntity.setExpirationDate("");
				return MarshalXML.marshal(ownedCarEntity);
			}
		} else { // get cars
			Long personaId = getPersonaId(true);
			ArrayOfOwnedCarTransType arrayofOwnedCarTrans = personaBO.getCars(personaId);
			return MarshalXML.marshal(arrayofOwnedCarTrans);
		}
		return "";
	}
}