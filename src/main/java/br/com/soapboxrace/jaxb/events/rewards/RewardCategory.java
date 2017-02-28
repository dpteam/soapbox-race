package br.com.soapboxrace.jaxb.events.rewards;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum RewardCategory
{
    Base,
    Rank,
    Bonus,
    TeamBonus,
    Amplifier,
    Skill,
    Pursuit,
    Objective,
    SkillMod
}
