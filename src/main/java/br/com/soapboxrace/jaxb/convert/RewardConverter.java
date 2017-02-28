package br.com.soapboxrace.jaxb.convert;

import br.com.soapboxrace.achievements.AchievementReward;
import com.google.gson.Gson;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RewardConverter implements AttributeConverter<AchievementReward, String>
{
    @Override
    public String convertToDatabaseColumn(AchievementReward achievementReward)
    {
        return new Gson().toJson(achievementReward);
    }

    @Override
    public AchievementReward convertToEntityAttribute(String s)
    {
        return new Gson().fromJson(s, AchievementReward.class);
    }
}
