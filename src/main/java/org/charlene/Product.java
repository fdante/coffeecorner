package org.charlene;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Product {
    protected final BigDecimal basePrice;
    protected final BigDecimal finalPrice;
    protected final Map<String, BigDecimal> extras;
    protected final String finalDescription;

    protected Product(String baseDescription, BigDecimal basePrice, Map<String, BigDecimal> extras) {
        this.basePrice = basePrice;
        this.extras = extras;
        this.finalDescription = createFinalDescription(baseDescription);
        this.finalPrice = calculatePrice();

    }

    private String createFinalDescription(String description) {
        StringBuilder sb = new StringBuilder(description);
        for (String key : extras.keySet()) {
            sb.append(" with ").append(key);
        }
        return sb.toString();
    }

    protected String getDescription() {
      return finalDescription;
    }

    public Map<String, BigDecimal> getExtras() {
        return Map.copyOf(extras);
    }

    public abstract boolean isBeverage();
    public abstract boolean isSnack();


    private BigDecimal calculatePrice() {
        BigDecimal price = basePrice;
        for (String key : extras.keySet()) {
            price = price.add(extras.get(key));
        }
        return price;
    }

    protected static Map<String, BigDecimal> filterPossibleExtras(Map<String, BigDecimal> extras, Set<String> possibleKeys) {
        Map<String, BigDecimal> filteredExtras = new HashMap<>();
        extras.keySet().forEach(k -> {
            if (possibleKeys.contains(k)) {
                filteredExtras.put(k, extras.get(k)); }
        });
        return filteredExtras;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }
}
