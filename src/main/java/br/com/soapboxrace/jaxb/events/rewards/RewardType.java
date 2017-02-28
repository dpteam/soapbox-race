package br.com.soapboxrace.jaxb.events.rewards;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum RewardType
{
    None,
    Busted,
    Evaded,
    RepAmplifier,
    TokenAmplifier,
    SkillMostWanted,
    SkillSocialite,
    SkillTycoon,
    SkillTerminator,
    HeatLevel,
    PursuitLength,
    Bounty,
    CopCarsDeployed,
    CopCarsRammed,
    CopCarsDisabled,
    RhinosDisabled,
    CostToState,
    RoadblocksDodged,
    SpikeStripsDodged,
    Infractions,
    LevelCap,
    EntitlementLevelCap,
    TopenCap,
    SafehouseReached,
    Finished,
    TimeBonus,
    Player1,
    Player2,
    Player3,
    Player4,
    StrikeFree,
    TeamStrikeBonus,
    PowerupBonus,
    SkillMod,
}
