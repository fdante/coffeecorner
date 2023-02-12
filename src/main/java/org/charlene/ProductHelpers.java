package org.charlene;

import org.charlene.discount.BeverageAndSnackDiscount;
import org.charlene.discount.BeverageDiscount;

import java.math.BigDecimal;
import java.util.*;

import static org.charlene.ProductHelpers.ProductEnum.*;

public class ProductHelpers {


    public enum ProductEnum {
        SMALL_COFFEE(BigDecimal.valueOf(2.5), "small coffee"),
        NORMAL_COFFEE(BigDecimal.valueOf(3.0), "normal coffee"),
        LARGE_COFFEE(BigDecimal.valueOf(3.5), "large coffee"),
        ORANGE_JUICE(BigDecimal.valueOf(3.95), "orange juice"),

        BACON_ROLL(BigDecimal.valueOf(4.5), "bacon roll");

        public final BigDecimal price;
        public final String value;
        private ProductEnum(BigDecimal price, String value) {
            this.price = price;
            this.value = value;
        }
    }

    public static List<Product> parseInput(List<String> inputs) {
        List<Product> products = new ArrayList<>();

        for (String input: inputs){
            Map<String, BigDecimal> extrasWanted = new HashMap<>();

            Map<String, BigDecimal> possibleALLExtras = Map.ofEntries(
                    Map.entry("extra milk", BigDecimal.valueOf(0.3)),
                    Map.entry("special roast", BigDecimal.valueOf(0.9)),
                    Map.entry("foamed milk", BigDecimal.valueOf(0.5)));

            possibleALLExtras.keySet().forEach(k -> {
                if (input.contains(k)) {
                    extrasWanted.putIfAbsent(k, possibleALLExtras.get(k)); }
            });

            Product product = null;
            if (input.contains(LARGE_COFFEE.value)){
                product = Coffee.ofBigSize(extrasWanted);
                products.add(product);
            } else if (input.contains(SMALL_COFFEE.value)){
                product = Coffee.ofSmallSize(extrasWanted);
                products.add(product);
            } else if (input.contains(NORMAL_COFFEE.value)){
                product = Coffee.ofMediumSize(extrasWanted);
                products.add(product);
            } else if (input.contains(BACON_ROLL.value)){
                product = BaconRoll.ofBaconRoll(extrasWanted);
                products.add(product);
            } else if (input.contains(ORANGE_JUICE.value)){
                product = OrangeJuice.ofOrangeJuice(extrasWanted);
                products.add(product);
            }

        }
        return products;
    }

}
