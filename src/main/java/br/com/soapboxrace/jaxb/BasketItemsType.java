package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasketItemsType", propOrder = { "basketItemTrans" })
public class BasketItemsType {

	@XmlElement(name = "BasketItemTrans", required = true)
	protected List<BasketItemTransType> basketItemTrans;

	public List<BasketItemTransType> getBasketItemTrans() {
		return basketItemTrans;
	}
	
	public BasketItemTransType getFirstItem() {
		return basketItemTrans.get(0);
	}
	
	public void setBasketItemTrans(BasketItemTransType value) {
		this.basketItemTrans = Collections.singletonList(value);
	}

	public void setBasketItemTrans(List<BasketItemTransType> basketItemTrans)
	{
		this.basketItemTrans = basketItemTrans;
	}
}
