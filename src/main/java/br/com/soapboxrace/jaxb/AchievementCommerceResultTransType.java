package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AchievementCommerceResultTransType", propOrder = { "commerceItems", "invalidBasket", "inventoryItems",
		"purchasedCars", "status", "wallets", "visualStyle", "achievementRankId" })
@XmlRootElement(name = "AchievementRewards")
public class AchievementCommerceResultTransType
{
	@XmlElement(name = "InvalidBasket", required = true)
	protected String invalidBasket;
	@XmlElement(name = "InventoryItems", required = true)
	protected InventoryItemsType inventoryItems;
	@XmlElement(name = "PurchasedCars", required = true)
	protected PurchasedCarsType purchasedCars;
	@XmlElement(name = "Status", required = true)
	protected String status;
	@XmlElement(name = "Wallets", required = true)
	protected WalletsType wallets;
	
	@XmlElement(name = "VisualStyle")
	protected String visualStyle;
	
	@XmlElement(name = "AchievementRankId")
	protected Long achievementRankId;
	
	@XmlElementWrapper(name = "CommerceItems")
	@XmlElement(name = "CommerceItemTrans")
	protected List<CommerceItemTransType> commerceItems;

	public List<CommerceItemTransType> getCommerceItems() {
		return commerceItems;
	}

	public void setCommerceItems(List<CommerceItemTransType> commerceItems) {
		this.commerceItems = commerceItems;
	}

	public String getInvalidBasket() {
		return invalidBasket;
	}

	public void setInvalidBasket(String value) {
		this.invalidBasket = value;
	}

	public InventoryItemsType getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(InventoryItemsType value) {
		this.inventoryItems = value;
	}

	public PurchasedCarsType getPurchasedCars() {
		return purchasedCars;
	}

	public void setPurchasedCars(PurchasedCarsType value) {
		this.purchasedCars = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public WalletsType getWallets() {
		return wallets;
	}

	public void setWallets(WalletsType value) {
		this.wallets = value;
	}

	public Long getAchievementRankId()
	{
		return achievementRankId;
	}

	public void setAchievementRankId(Long achievementRankId)
	{
		this.achievementRankId = achievementRankId;
	}

	public String getVisualStyle()
	{
		return visualStyle;
	}

	public void setVisualStyle(String visualStyle)
	{
		this.visualStyle = visualStyle;
	}
}
