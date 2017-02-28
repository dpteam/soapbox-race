package br.com.soapboxrace.jaxb.convert;

import br.com.soapboxrace.jaxb.BadgePacketType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class BadgesConverter implements AttributeConverter<List<BadgePacketType>, String>
{
    @Override
    public String convertToDatabaseColumn(List<BadgePacketType> badgePacketTypes)
    {
        JsonArray jsonArray = new JsonArray();
        
        if (badgePacketTypes != null) {
            for (BadgePacketType packet : badgePacketTypes) {
                JsonObject badgeObject = new JsonObject();

                badgeObject.addProperty("achievementRankId", packet.getAchievementRankId());
                badgeObject.addProperty("badgeDefinitionId", packet.getBadgeDefinitionId());
                badgeObject.addProperty("isRare", packet.isRare());
                badgeObject.addProperty("rarity", packet.getRarity());
                badgeObject.addProperty("slot", packet.getSlotId());

                jsonArray.add(badgeObject);
            }
        }
        
        return jsonArray.toString();
    }

    @Override
    public List<BadgePacketType> convertToEntityAttribute(String s)
    {
        s = s.trim();
        
        if (s.isEmpty())
            return new ArrayList<>();
        
        List<BadgePacketType> list = new ArrayList<>();
        
        JsonArray jsonArray = new JsonParser().parse(s).getAsJsonArray();
        
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject object = jsonArray.get(i).getAsJsonObject();
            
            BadgePacketType packet = new BadgePacketType();
            packet.setAchievementRankId(object.get("achievementRankId").getAsInt());
            packet.setBadgeDefinitionId(object.get("badgeDefinitionId").getAsInt());
            packet.setRare(object.get("isRare").getAsBoolean());
            packet.setRarity(object.get("rarity").getAsFloat());
            packet.setSlotId(object.get("slot").getAsShort());
            
            list.add(packet);
        }
        
        return list;
    }
}
