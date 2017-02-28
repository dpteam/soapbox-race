package br.com.soapboxrace.tools;

import br.com.soapboxrace.achievements.AchievementReward;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RewardTesting
{
    public static void main(String[] args) throws IOException
    {
        Path path = Paths.get("data/achievement_rewards/ACH_USE_NOS.json");

        if (Files.exists(path)) {
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

            AchievementReward reward = new Gson().fromJson(content, AchievementReward.class);
            
            System.out.println(reward);
        }
    }
}
