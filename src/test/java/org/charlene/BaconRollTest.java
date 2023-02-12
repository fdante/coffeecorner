package org.charlene;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BaconRoll tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaconRollTest {

    List<Product> products;

    @BeforeAll
    void setupEnv() {

        List<String> inputs = new ArrayList<>();
        inputs.add("bacon roll with extra milk");
        inputs.add("bacon roll with special roast");
        inputs.add("bacon roll with foamed milk");
        inputs.add("bacon roll");

        products = ProductHelpers.parseInput(inputs);
    }

    @Test
    public void checkBaconRollDoesntAllowsExtras(){
        assertEquals("bacon roll", products.get(0).getDescription());
        assertEquals("bacon roll", products.get(1).getDescription());
        assertEquals("bacon roll", products.get(2).getDescription());
        assertEquals("bacon roll", products.get(3).getDescription());
    }

    @Test
    public void checkBaconRollCorrectlyCalculatesPrice(){
        assertEquals(BigDecimal.valueOf(4.50), products.get(0).getFinalPrice());
        assertEquals(BigDecimal.valueOf(4.50), products.get(1).getFinalPrice());
        assertEquals(BigDecimal.valueOf(4.50), products.get(2).getFinalPrice());
        assertEquals(BigDecimal.valueOf(4.50), products.get(3).getFinalPrice());
    }
}