package br.com.soapboxrace.definition;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CarTypes
{
    private static final Map<String, List<String>> carMap = ImmutableMap.<String, List<String>>builder()
            .put("alfaromeo", Arrays.asList(CarIds.split(CarIds.alfaRomeo)))
            .put("astonmartin", Arrays.asList(CarIds.split(CarIds.astonMartin)))
            .put("audi", Arrays.asList(CarIds.split(CarIds.audi)))
            .put("bentley", Arrays.asList(CarIds.split(CarIds.bentley)))
            .put("bmw", Arrays.asList(CarIds.split(CarIds.bmw)))
            .put("cadillac", Arrays.asList(CarIds.split(CarIds.cadillac)))
            .put("caterham", Arrays.asList(CarIds.split(CarIds.caterham)))
            .put("chevrolet", Arrays.asList(CarIds.split(CarIds.chevrolet)))
            .put("chrysler", Arrays.asList(CarIds.split(CarIds.chrysler)))
            .put("dodge", Arrays.asList(CarIds.split(CarIds.dodge)))
            .put("ford", Arrays.asList(CarIds.split(CarIds.ford)))
            .put("fordshelby", Arrays.asList(CarIds.split(CarIds.fordShelby)))
            .put("hummer", Arrays.asList(CarIds.split(CarIds.hummer)))
            .put("infiniti", Arrays.asList(CarIds.split(CarIds.infiniti)))
            .put("jaguar", Arrays.asList(CarIds.split(CarIds.jaguar)))
            .put("jeep", Arrays.asList(CarIds.split(CarIds.jeep)))
            .put("koenigsegg", Arrays.asList(CarIds.split(CarIds.koenigsegg)))
            .put("lamborghini", Arrays.asList(CarIds.split(CarIds.lamborghini)))
            .put("lancia", Arrays.asList(CarIds.split(CarIds.lancia)))
            .put("lexus", Arrays.asList(CarIds.split(CarIds.lexus)))
            .put("lotus", Arrays.asList(CarIds.split(CarIds.lotus)))
            .put("marussia", Arrays.asList(CarIds.split(CarIds.marussia)))
            .put("mazda", Arrays.asList(CarIds.split(CarIds.mazda)))
            .put("mclaren", Arrays.asList(CarIds.split(CarIds.mclaren)))
            .put("mercedesbenz", Arrays.asList(CarIds.split(CarIds.mercedesBenz)))
            .put("mitsubishi", Arrays.asList(CarIds.split(CarIds.mitsubishi)))
            .put("nissan", Arrays.asList(CarIds.split(CarIds.nissan)))
            .put("pagani", Arrays.asList(CarIds.split(CarIds.pagani)))
            .put("plymouth", Arrays.asList(CarIds.split(CarIds.plymouth)))
            .put("pontiac", Arrays.asList(CarIds.split(CarIds.pontiac)))
            .put("porsche", Arrays.asList(CarIds.split(CarIds.porsche)))
            .put("renault", Arrays.asList(CarIds.split(CarIds.renault)))
            .put("scion", Arrays.asList(CarIds.split(CarIds.scion)))
            .put("shelby", Arrays.asList(CarIds.split(CarIds.shelby)))
            .put("subaru", Arrays.asList(CarIds.split(CarIds.subaru)))
            .put("toyota", Arrays.asList(CarIds.split(CarIds.toyota)))
            .put("vauxhall", Arrays.asList(CarIds.split(CarIds.vauxhall)))
            .put("volkswagen", Arrays.asList(CarIds.split(CarIds.volkswagen)))
            .build();
    public static final Map<String, String> makeToAchievement = ImmutableMap.<String, String>builder()
            .put("alfaromeo", "achievement_ACH_OWN_ALFAROMEO")
            .put("astonmartin", "achievement_ACH_OWN_ASTONMARTIN")
            .put("audi", "achievement_ACH_OWN_AUDI")
            .put("bentley", "achievement_ACH_OWN_BENTLEY")
            .put("bmw", "achievement_ACH_OWN_BMW")
            .put("cadillac", "achievement_ACH_OWN_CADILLAC")
            .put("caterham", "achievement_ACH_OWN_CATERHAM")
            .put("chevrolet", "achievement_ACH_OWN_CHEVROLET")
            .put("chrysler", "achievement_ACH_OWN_CHRYSLER")
            .put("dodge", "achievement_ACH_OWN_DODGE")
            .put("ford", "achievement_ACH_OWN_FORD")
            .put("fordshelby", "achievement_ACH_OWN_FORDSHELBY")
            .put("hummer", "achievement_ACH_OWN_HUMMER")
            .put("infiniti", "achievement_ACH_OWN_INFINITI")
            .put("jaguar", "achievement_ACH_OWN_JAGUAR")
            .put("jeep", "achievement_ACH_OWN_JEEP")
            .put("koenigsegg", "achievement_ACH_OWN_KOENIGSEGG")
            .put("lamborghini", "achievement_ACH_OWN_LAMBORGHINI")
            .put("lancia", "achievement_ACH_OWN_LANCIA")
            .put("lexus", "achievement_ACH_OWN_LEXUS")
            .put("lotus", "achievement_ACH_OWN_LOTUS")
            .put("marussia", "achievement_ACH_OWN_MARUSSIA")
            .put("mazda", "achievement_ACH_OWN_MAZDA")
            .put("mclaren", "achievement_ACH_OWN_MCLAREN")
            .put("mercedesbenz", "achievement_ACH_OWN_MERCEDES-BENZ")
            .put("mitsubishi", "achievement_ACH_OWN_MITSUBISHI")
            .put("nissan", "achievement_ACH_OWN_NISSAN")
            .put("pagani", "achievement_ACH_OWN_PAGANI")
            .put("plymouth", "achievement_ACH_OWN_PLYMOUTH")
            .put("pontiac", "achievement_ACH_OWN_PONTIAC")
            .put("porsche", "achievement_ACH_OWN_PORSCHE")
            .put("renault", "achievement_ACH_OWN_RENAULTSPORT")
            .put("scion", "achievement_ACH_OWN_SCION")
            .put("shelby", "achievement_ACH_OWN_SHELBY")
            .put("subaru", "achievement_ACH_OWN_SUBARU")
            .put("toyota", "achievement_ACH_OWN_TOYOTA")
            .put("vauxhall", "achievement_ACH_OWN_VAUXHALL")
            .put("volkswagen", "achievement_ACH_OWN_VOLKSWAGEN")
            .build();

    public static void main(String[] args)
    {
        System.out.println(getMakeFromProductId("SRV-CAR93"));
        System.out.println(getMakeFromProductId("SRV-CAR150"));
        System.out.println(getMakeFromProductId("SRV-CAR206"));
    }

    public static String getMakeFromProductId(String productId)
    {
        Map.Entry<String, List<String>> entry = carMap.entrySet().stream()
                .filter(e -> e.getValue().contains(productId))
                .findFirst()
                .orElse(null);

        return entry == null ? "no make" : entry.getKey();
    }
}
