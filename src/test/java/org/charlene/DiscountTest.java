package org.charlene;

import org.charlene.discount.BeverageAndSnackDiscount;
import org.charlene.discount.BeverageDiscount;
import org.charlene.discount.Discount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("All discount types are applied in the following tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiscountTest {
    Order order;

    @BeforeAll
    void setupEnv() {
        Customer customer = new Customer(500567L, "Joe Smith");
        Map<Customer, Integer> beverages = new HashMap<>();
        beverages.put(customer, 0);
        BeverageDiscount bevDiscount = new BeverageDiscount(beverages);
        BeverageAndSnackDiscount bevAndSnackDiscount = new BeverageAndSnackDiscount();

        List<String> inputs = new ArrayList<>();
        inputs.add("large coffee with extra milk");
        inputs.add("small coffee with special roast");
        inputs.add("large coffee with extra milk with special roast");
        inputs.add("large coffee with foamed milk");
        inputs.add("large coffee with extra milk");
        inputs.add("large coffee with special roast");
        inputs.add("bacon roll with extra milk");
        inputs.add("orange juice with extra milk");
        inputs.add("orange juice");
        inputs.add("orange juice");
        inputs.add("orange juice");

        var lines = new ArrayList<OrderLine>();
        var products = ProductHelpers.parseInput(inputs);
        products.stream().forEach(product -> lines.add(new OrderLine(product)));
        order = new Order(200201L, customer, lines, List.of(bevDiscount, bevAndSnackDiscount));
    }

    @DisplayName("BeverageDiscount is applied two times, and BeverageAndSnackDiscount")
    @Test
    void checkDiscountsIsApplied() {
        List<Discount> discountResults = order.getDiscountResults();
        assertEquals(2, discountResults.size());
        Discount discount1 = discountResults.get(0);
        Discount discount2 = discountResults.get(1);
        assertEquals(BigDecimal.valueOf(7.75), discount1.getResult());
        assertEquals(BigDecimal.valueOf(0.3), discount2.getResult());
    }

    @DisplayName("The type of the Discounts are checked")
    @Test
    void checkDiscountsAreOfCorrectTypes() {
        List<Discount> discountResults = order.getDiscountResults();
        assertEquals(2, discountResults.size());
        Discount discount1 = discountResults.get(0);
        Discount discount2 = discountResults.get(1);
        assertDoesNotThrow( () ->  (BeverageDiscount) discount1);
        assertDoesNotThrow( () ->  (BeverageAndSnackDiscount) discount2);

    }

    @DisplayName("The type of the Discounts are checked2")
    @Test
    void checkDiscountsAreOfCorrectTypesExperimental() {
        List<Discount> discountResults = order.getDiscountResults();
        Discount discount1 = discountResults.get(0);
        Discount discount2 = discountResults.get(1);
        assertInstanceOf(BeverageDiscount.class, discount1);
        assertInstanceOf(BeverageAndSnackDiscount.class, discount2);
    }

    @DisplayName("calculate Total for Order - with Discounts applied")
    @Test
    void checkOrderIsCorrectlyCalculatesTotalWithDiscount() {
        assertEquals(BigDecimal.valueOf(36.35), order.getFinalPrice());
    }

}
