package br.com.soapboxrace.jaxb.announcements;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum LoginAnnouncementType
{
    ExternalLink,
    SafehouseProduct,
    ImageOnly,
    SafehouseProductNoButton,
    ExternalLinkNoButton
}
