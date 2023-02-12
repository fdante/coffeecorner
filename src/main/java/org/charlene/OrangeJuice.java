package org.charlene;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.charlene.ProductHelpers.ProductEnum.ORANGE_JUICE;

public class OrangeJuice extends Product{
    static Set<String> possibleOrangeJuiceExtras = new HashSet<>();

    private OrangeJuice(String baseDescription, BigDecimal basePrice, Map<String, BigDecimal> extras) {
        super(baseDescription, basePrice, extras);
    }

    public static OrangeJuice ofOrangeJuice(Map<String, BigDecimal> extras) {
        Map<String, BigDecimal> filtered = filterPossibleExtras(extras, possibleOrangeJuiceExtras);
        return new OrangeJuice(ORANGE_JUICE.value, ORANGE_JUICE.price, filtered);
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
