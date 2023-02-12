package org.charlene;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.charlene.ProductHelpers.ProductEnum.BACON_ROLL;

public class BaconRoll extends Product {

    static Set<String> possibleBaconRollExtras = new HashSet<>();

    private BaconRoll(String baseDescription, BigDecimal basePrice, Map<String, BigDecimal> extras) {
        super(baseDescription, basePrice, extras);
    }

    public static BaconRoll ofBaconRoll(Map<String, BigDecimal> extras) {
        Map<String, BigDecimal> filtered = filterPossibleExtras(extras, possibleBaconRollExtras);
        return new BaconRoll(BACON_ROLL.value, BACON_ROLL.price, filtered);
    }

    public boolean isBeverage() {
        return false;
    }

    public boolean isSnack() {
        return true;
    }

}

