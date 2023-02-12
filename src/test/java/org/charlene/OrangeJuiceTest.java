package org.charlene;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("OrangeJuice tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrangeJuiceTest {

    List<Product> products;

    @BeforeAll
    void setupEnv() {

        List<String> inputs = new ArrayList<>();
        inputs.add("orange juice with extra milk");
        inputs.add("orange juice with special roast");
        inputs.add("orange juice with foamed milk");
        inputs.add("orange juice");

        products = ProductHelpers.parseInput(inputs);
    }

    @Test
    public void checkOrangeJuiceDoesntAllowsExtras(){
        assertEquals("orange juice", products.get(0).getDescription());
        assertEquals("orange juice", products.get(1).getDescription());
        assertEquals("orange juice", products.get(2).getDescription());
        assertEquals("orange juice", products.get(3).getDescription());
    }

    @Test
    public void checkOrangeJuiceCorrectlyCalculatesPrice(){
        assertEquals(BigDecimal.valueOf(3.95), products.get(0).getFinalPrice());
        assertEquals(BigDecimal.valueOf(3.95), products.get(1).getFinalPrice());
        assertEquals(BigDecimal.valueOf(3.95), products.get(2).getFinalPrice());
        assertEquals(BigDecimal.valueOf(3.95), products.get(3).getFinalPrice());
    }
}