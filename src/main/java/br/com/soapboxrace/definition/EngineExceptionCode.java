package br.com.soapboxrace.definition;

/**
 * Some of the many engine error codes. Some that are not needed have been removed.
 */
public enum EngineExceptionCode
{
    SecurityKickedInvalidPowerup(-200005),
    SecurityKickedStatisticalTopSpeed(-200004),
    SecurityKickedStatisticalRaceTime(-200003),
    SecurityKickedRaceTimeRatio(-200002),
    SecurityKickedRaceTime(-200001),
    SecuityKickedArbitration(-200000),
    LiveUpdateInvalidCatalogVersion(-100001),
    LiveUpdateAuthenticationFailed(-100000),
    NotFound(-10404),
    SocialFriendRequestNotResolvable(-10000),
    MoreThanOneRemotePersonaFound(-2000),
    NoProductErrorCode(-1911),
    NotInQueue(-1801),
    WalletBalanceIsNotEnough(-1684),
    NoWalletErrorCode(-1683),
    RemotePersonaIdInvalid(-1646),
    EventNotFound(-1550),
    LuckyDrawCouldNotDrawProduct(-1524),
    LuckyDrawNoLootTablesNotPopulated(-1523),
    LuckyDrawNoMoreDraws(-1522),
    LuckyDrawInvalidDraw(-1521),
    LuckyDrawNoTableDefinedForRace(-1520),
    NullValue(-1002),
    TargetFriendsListExceededMaximumCount(-904),
    FriendsListExceededMaximumCount(-903),
    FriendDoesNotExist(-902),
    FriendAlreadyAdded(-901),
    FriendIsSelf(-900),
    MaximumNumberOfPersonasForUserReached(-788),
    PersonaMottoIsTooLong(-783),
    PersonaCarIsNull(-782),
    MoreThanOneRemoteUserFound(-775),
    UserNotFound(-774),
    RemotePersonaDoesNotBelongToUser(-773),
    PersonaNotFound(-771),
    StatusReasonCodeInvalid(-768),
    DisplayNameSuggestionFailed(-766),
    DisplayNameNotAllowed(-765),
    DisplayNameTooShort(-764),
    DisplayNameTooLong(-763),
    DisplayNameDuplicate(-762),
    DisplayNameMissing(-761),
    RemoteUserIsTempGameBanned(-751), // global ban, temporary
    RemoteUserIsGameBanned(-750), // global ban, permanent
    RemoteUserIsBanned(-748), // shard ban???
    PasswordIncorrect(-747),
    RemoteUserDoesNotExist(-746),
    InvalidFractionCompletedInResult(-617),
    InvalidPlacingInResult(-616),
    InvalidSpeedInResult(-615),
    InvalidRaceTimeInResult(-614),
    InvalidFinishReason(-603),
    InvalidEntrantEventSession(-600),
    PresetCarDoesntExist(-210),
    CantDeleteLastOwnedCar(-209),
    InvalidPaintGroupForPaintSlot(-208),
    MultiplePaintsInSameSlot(-207),
    PowerUpItemInfoDoesntExist(-206),
    CarDataInvalid(-205),
    CarNotOwnedByDriver(-201),
    CustomCarDoesntExist(-200);
    
    int code;

    EngineExceptionCode(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }
    
    static EngineExceptionCode findByCode(int code) {
        for (EngineExceptionCode exceptionCode : values()) {
            if (exceptionCode.code == code)
                return exceptionCode;
        }
        
        return null;
    }
}
