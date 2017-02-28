package br.com.soapboxrace.definition;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class CarClasses
{
    private static final Map<Long, String> carClasses = ImmutableMap.<Long, String>builder()
            .put(872416321L, "E")
            .put(415909161L, "D")
            .put(1866825865L, "C")
            .put(3888493841L, "B")
            .put(3889129816L, "A")
            .put(2152555850L, "S")
            .put(-3422550975L, "E")
            .put(-3879058135L, "D")
            .put(-2428141431L, "C")
            .put(-406473455L, "B")
            .put(-405837480L, "A")
            .put(-2142411446L, "S")
            .build();

    public static String getCarClassFromHash(long carClassHash)
    {
        return carClasses.getOrDefault(carClassHash, "UK");
    }

    public static long getHashFromCarClass(String carClass)
    {
        return carClasses.entrySet().stream()
                .filter(e -> e.getValue().equals(carClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid car class: " + carClass))
                .getKey();
    }
}
