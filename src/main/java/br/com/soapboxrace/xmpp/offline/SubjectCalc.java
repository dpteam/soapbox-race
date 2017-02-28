package br.com.soapboxrace.xmpp.offline;

public class SubjectCalc
{
    private static Object[] allMul(long multiplier, long multiplicand)
    {
        long hiMultiplier = multiplier >>> 32 & 0xFFFFFFFFL;
        long loMultiplier = multiplier << 32 >>> 32 & 0xFFFFFFFFL;
        long hiMultiplicand = multiplicand >>> 32 & 0xFFFFFFFFL;
        long loMultiplicand = multiplicand << 32 >>> 32 & 0xFFFFFFFFL;
        long multiplied = hiMultiplicand == 0 && hiMultiplier == 0 ? loMultiplier * loMultiplicand : multiplier * multiplicand;
        long oldMultiplied = multiplied;
        long hiMultiplied = multiplied >>> 32 & 0xFFFFFFFFL;
        long loMultiplied = multiplied << 32 >>> 32 & 0xFFFFFFFFL;
        do {
            if (loMultiplied > 0xFFFFFFFFL) {
                loMultiplied %= 0x100000000L;
            }
            if (hiMultiplied > 0xFFFFFFFFL) {
                hiMultiplied %= 0x100000000L;
            }
            if (loMultiplied < 0) {
                loMultiplied %= 0x100000000L;
            }
            if (hiMultiplied >= 0) continue;
            hiMultiplied %= 0x100000000L;
        } while ((loMultiplied < 0 || hiMultiplied < 0) && (loMultiplied > 0xFFFFFFFFL || hiMultiplied > 0xFFFFFFFFL));
        multiplied = hiMultiplied << 32 | loMultiplied;
        Object[] arrobject = new Object[2];
        arrobject[0] = multiplied;
        arrobject[1] = oldMultiplied == loMultiplied;
        return arrobject;
    }

    public static Long calculateHash(char[] jid, char[] response)
    {
        Object[] bHash;
        int i;
        long hiCdq;
        long loJidHash;
        long hiMultiplier;
        long hiJidHash;
        long loMultiplier;
        long loCdq;
        long multiplier = 0xFFFFFFFFL;
        boolean cFlag = true;
        for (i = 0; i < jid.length; ++i) {
            bHash = SubjectCalc.allMul(multiplier, 33);
            long jidHash = (Long) bHash[0];
            hiJidHash = jidHash >>> 32 & 0xFFFFFFFFL;
            loJidHash = jidHash << 32 >>> 32 & 0xFFFFFFFFL;
            hiCdq = hiJidHash;
            loCdq = (long) jid[i] & 0xFFFFFFFFL;
            hiMultiplier = (hiJidHash >>> 32) + hiCdq + (cFlag ? 1 : 0) & 0xFFFFFFFFL;
            loMultiplier = (loJidHash << 32 >>> 32) + loCdq & 0xFFFFFFFFL;
            multiplier = hiMultiplier << 32 | loMultiplier;
            cFlag = (Boolean) bHash[1];
        }
        for (i = 0; i < response.length; ++i) {
            bHash = SubjectCalc.allMul(multiplier, 33);
            long responseHash = (Long) bHash[0];
            hiJidHash = responseHash >>> 32 & 0xFFFFFFFFL;
            loJidHash = responseHash << 32 >>> 32 & 0xFFFFFFFFL;
            hiCdq = hiJidHash;
            loCdq = (long) response[i] & 0xFFFFFFFFL;
            hiMultiplier = (hiJidHash >>> 32) + hiCdq + (cFlag ? 1 : 0) & 0xFFFFFFFFL;
            loMultiplier = (loJidHash << 32 >>> 32) + loCdq & 0xFFFFFFFFL;
            multiplier = hiMultiplier << 32 | loMultiplier;
            cFlag = (Boolean) bHash[1];
        }
        return multiplier;
    }
}