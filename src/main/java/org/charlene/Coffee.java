package org.charlene;


import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static org.charlene.ProductHelpers.ProductEnum.*;

public class Coffee extends Product {

    static Set<String> possibleCofeeExtras = Set.of(
            "extra milk", "special roast", "foamed milk");

    private Coffee(String description, BigDecimal basePrice, Map<String, BigDecimal> extras) {
        super(description, basePrice, extras);
    }

    public static Coffee ofSmallSize(Map<String, BigDecimal> extras) {
        Map<String, BigDecimal> filtered = filterPossibleExtras(extras, possibleCofeeExtras);
        return new Coffee(SMALL_COFFEE.value, SMALL_COFFEE.price, filtered);
    }

    public static Coffee ofMediumSize(Map<String, BigDecimal> extras) {
        Map<String, BigDecimal> filtered = filterPossibleExtras(extras, possibleCofeeExtras);
        return new Coffee(NORMAL_COFFEE.value, NORMAL_COFFEE.price, filtered);
    }

    public static Coffee ofBigSize(Map<String, BigDecimal> extras) {
        Map<String, BigDecimal> filtered = filterPossibleExtras(extras, possibleCofeeExtras);
        return new Coffee(LARGE_COFFEE.value, LARGE_COFFEE.price, filtered);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "basePrice=" + basePrice +
                ", extras=" + extras +
                ", finalDescription='" + finalDescription + '\'' +
                '}';
    }

    @Override
    public boolean isBeverage() {
        return true;
    }

    @Override
    public boolean isSnack() {
        return false;
    }

}
