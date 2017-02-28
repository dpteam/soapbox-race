package br.com.soapboxrace.jaxb.events;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum FinishReason
{
    @XmlEnumValue("0")
    Unknown(0),
    @XmlEnumValue("2")
    Completed(2),
    @XmlEnumValue("6")
    Succeeded(6),
    @XmlEnumValue("10")
    DidNotFinish(10),
    @XmlEnumValue("22")
    CrossedFinish(22),
    @XmlEnumValue("42")
    KnockedOut(42),
    @XmlEnumValue("74")
    Totalled(74),
    @XmlEnumValue("138")
    EngineBlown(138),
    @XmlEnumValue("266")
    Busted(266),
    @XmlEnumValue("518")
    Evaded(518),
    @XmlEnumValue("1030")
    ChallengeCompleted(1030),
    @XmlEnumValue("2058")
    Disconnected(2058),
    @XmlEnumValue("4106")
    FalseStart(4106),
    @XmlEnumValue("8202")
    Aborted(8202),
    @XmlEnumValue("16394")
    TimedOut(16394),
    @XmlEnumValue("32774")
    TimeLimitExpired(32774),
    @XmlEnumValue("65546")
    PauseDetected(65546),
    @XmlEnumValue("131082")
    SpeedHacking(131082),
    @XmlEnumValue("262154")
    CodePatchDetected(262154),
    @XmlEnumValue("524298")
    BadVerifierResponse(524298);

    int code;

    FinishReason(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public static FinishReason fromCode(int code)
    {
        for (FinishReason reason : values()) {
            if (reason.code == code)
                return reason;
        }

        return Unknown;
    }
}
