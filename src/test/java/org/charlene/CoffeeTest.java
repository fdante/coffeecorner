package org.charlene;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CoffeeTest {

    List<Product> products;

    @BeforeAll
    void setupEnv() {
        List<String> inputs = new ArrayList<>();
        inputs.add("large coffee with extra milk");
        inputs.add("small coffee with special roast");
        inputs.add("large coffee with foamed milk");
        inputs.add("large coffee with extra milk with special roast");
        inputs.add("large coffee with toy");

        products = ProductHelpers.parseInput(inputs);
    }

    @Test
    public void checkCoffeeAllowsExtras(){
        assertEquals("large coffee with extra milk", products.get(0).getDescription());
        assertEquals("small coffee with special roast", products.get(1).getDescription());
        assertEquals("large coffee with foamed milk", products.get(2).getDescription());
        assertEquals(true, products.get(3).getDescription().contains("with extra milk"));
        assertEquals(true, products.get(3).getDescription().contains("with special roast"));
        assertEquals("large coffee", products.get(4).getDescription());
    }

    @Test
    public void checkCoffeePrices(){
        assertEquals(BigDecimal.valueOf(3.80), products.get(0).getFinalPrice());
        assertEquals(BigDecimal.valueOf(3.40), products.get(1).getFinalPrice());
        assertEquals(BigDecimal.valueOf(4.00), products.get(2).getFinalPrice());
        assertEquals(BigDecimal.valueOf(4.70), products.get(3).getFinalPrice());
        assertEquals(BigDecimal.valueOf(3.50), products.get(4).getFinalPrice());
    }
}

