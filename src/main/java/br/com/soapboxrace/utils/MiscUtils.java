package br.com.soapboxrace.utils;

import java.math.BigInteger;
import java.util.Random;

public class MiscUtils
{
    public static BigInteger getRandomNumber(final int digCount)
    {
        return getRandomNumber(digCount, new Random());
    }

    public static BigInteger getRandomNumber(final int digCount, Random rnd)
    {
        final char[] ch = new char[digCount];
        for (int i = 0; i < digCount; i++) {
            ch[i] =
                    (char) ('0' + (i == 0 ? rnd.nextInt(9) + 1 : rnd.nextInt(10)));
        }
        return new BigInteger(new String(ch));
    }

    public static void noop()
    {
        // do nothing
    }
}
