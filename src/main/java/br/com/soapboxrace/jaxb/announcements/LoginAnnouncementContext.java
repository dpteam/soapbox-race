package br.com.soapboxrace.jaxb.announcements;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum LoginAnnouncementContext
{
    NotApplicable,
    CarPurchase,
    CardsPack,
    PaintShop,
    PerformanceShop,
    AftermarketShop,
    VinylShop
}
