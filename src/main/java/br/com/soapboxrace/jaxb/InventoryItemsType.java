package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InventoryItemsType", propOrder = { "inventoryItemTrans" })
public class InventoryItemsType {

	@XmlElement(name = "InventoryItemTrans", required = true)
	protected List<InventoryItemTransType> inventoryItemTrans;

	public List<InventoryItemTransType> getInventoryItemTrans() {
		return inventoryItemTrans;
	}

	public void setInventoryItemTrans(List<InventoryItemTransType> value) {
		this.inventoryItemTrans = value;
	}

}
