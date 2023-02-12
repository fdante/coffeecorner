package org.charlene;

import org.charlene.discount.BeverageAndSnackDiscount;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Beverage and Snack discount should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BeverageAndSnackDiscountTest {
    Order order;

    @BeforeAll
    void setupEnv() {
        Customer customer = new Customer(500567L, "Joe Smith");
        Map<Customer, Integer> beverages = new HashMap<>();
        beverages.put(customer, 0);
        BeverageAndSnackDiscount beverageAndSnackDiscount = new BeverageAndSnackDiscount();

        List<String> inputs = new ArrayList<>();
        inputs.add("large coffee with extra milk");
        inputs.add("bacon roll");

        var lines = new ArrayList<OrderLine>();
        var products = ProductHelpers.parseInput(inputs);
        products.stream().forEach(product -> lines.add(new OrderLine(product)));
        order = new Order(200201L, customer, lines, List.of(beverageAndSnackDiscount));

    }

    @DisplayName("be applied when at least one Beverage and one Snack is ordered")
    @Test
    void checkBevAndSnackDiscountIsApplied() {
        List<Discount> discountResults = order.getDiscountResults();
        assertEquals(1, discountResults.size());
        Discount discount = discountResults.get(0);
        assertEquals(BigDecimal.valueOf(0.3), discount.getResult());

    }

    @DisplayName("be of type BeverageAndSnackDiscount")
    @Test
    void checkBevAndSnackDiscountIsOfCorrectType() {
        List<Discount> discountResults = order.getDiscountResults();
        assertEquals(1, discountResults.size());
        Discount discount = discountResults.get(0);
        assertDoesNotThrow( () ->  (BeverageAndSnackDiscount) discount);
    }

    @DisplayName("calculate Sum for Items in an Order")
    @Test
    void checkOrderIsCorrectlyCalculatesSumWithExtras() {
        assertEquals(BigDecimal.valueOf(8.3), order.getSum());
    }

    @DisplayName("calculate Total for Order - with Discounts applied")
    @Test
    void checkOrderIsCorrectlyCalculatesTotalWithDiscount() {
        assertEquals(BigDecimal.valueOf(8.0), order.getFinalPrice());
    }


}
