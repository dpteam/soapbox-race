package br.com.soapboxrace;

import br.com.soapboxrace.config.Config;
import br.com.soapboxrace.jaxb.ArrayOfintType;
import br.com.soapboxrace.jaxb.util.UnmarshalXML;

public class Data
{
    private static final String REP_POINTS = "<ArrayOfint>\n" +
            "<int>100</int>\n" +
            "<int>975</int>\n" +
            "<int>2025</int>\n" +
            "<int>3625</int>\n" +
            "<int>5875</int>\n" +
            "<int>8875</int>\n" +
            "<int>12725</int>\n" +
            "<int>17525</int>\n" +
            "<int>23375</int>\n" +
            "<int>30375</int>\n" +
            "<int>39375</int>\n" +
            "<int>50575</int>\n" +
            "<int>64175</int>\n" +
            "<int>80375</int>\n" +
            "<int>99375</int>\n" +
            "<int>121375</int>\n" +
            "<int>146575</int>\n" +
            "<int>175175</int>\n" +
            "<int>207375</int>\n" +
            "<int>243375</int>\n" +
            "<int>283375</int>\n" +
            "<int>327575</int>\n" +
            "<int>376175</int>\n" +
            "<int>429375</int>\n" +
            "<int>487375</int>\n" +
            "<int>550375</int>\n" +
            "<int>618575</int>\n" +
            "<int>692175</int>\n" +
            "<int>771375</int>\n" +
            "<int>856375</int>\n" +
            "<int>950875</int>\n" +
            "<int>1055275</int>\n" +
            "<int>1169975</int>\n" +
            "<int>1295375</int>\n" +
            "<int>1431875</int>\n" +
            "<int>1579875</int>\n" +
            "<int>1739775</int>\n" +
            "<int>1911975</int>\n" +
            "<int>2096875</int>\n" +
            "<int>2294875</int>\n" +
            "<int>2506375</int>\n" +
            "<int>2731775</int>\n" +
            "<int>2971475</int>\n" +
            "<int>3225875</int>\n" +
            "<int>3495375</int>\n" +
            "<int>3780375</int>\n" +
            "<int>4081275</int>\n" +
            "<int>4398475</int>\n" +
            "<int>4732375</int>\n" +
            "<int>5083375</int>\n" +
            "<int>5481355</int>\n" +
            "<int>5898805</int>\n" +
            "<int>6336165</int>\n" +
            "<int>6793875</int>\n" +
            "<int>7272375</int>\n" +
            "<int>7772105</int>\n" +
            "<int>8293505</int>\n" +
            "<int>8837015</int>\n" +
            "<int>9403075</int>\n" +
            "<int>9992125</int>\n" +
            "</ArrayOfint>";
    
    public static int getRequiredRep(int level, int curentRep)
    {
        if (level == 60)
            return 0;
        ArrayOfintType arrayOfintType = (ArrayOfintType) UnmarshalXML.unMarshal(REP_POINTS, new ArrayOfintType());
        int rep = arrayOfintType.getInt().get(level - 1);

        return Math.max(0, rep - curentRep);
    }
}
