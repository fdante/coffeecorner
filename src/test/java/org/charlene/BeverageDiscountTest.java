package org.charlene;

import org.charlene.discount.BeverageDiscount;
import org.charlene.discount.Discount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Beverage discount should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BeverageDiscountTest {
    Order order;
    List<String> inputs = new ArrayList<>();

    @BeforeAll
    void setupEnv() {
        Customer customer = new Customer(500567L, "Joe Smith");
        Map<Customer, Integer> beverages = new HashMap<>();
        beverages.put(customer, 0);
        BeverageDiscount discountCalculator = new BeverageDiscount(beverages);

        inputs.add("large coffee with extra milk");
        inputs.add("small coffee with special roast");
        inputs.add("large coffee with extra milk with special roast");
        inputs.add("large coffee with extra milk");
        inputs.add("large coffee with extra milk");

        var lines = new ArrayList<OrderLine>();
        var products = ProductHelpers.parseInput(inputs);
        products.stream().forEach(product -> lines.add(new OrderLine(product)));
        order = new Order(200201L, customer, lines, List.of(discountCalculator));

    }

    @DisplayName("be applied at every 5th Beverage")
    @Test
    void check5thBevDiscountIsApplied() {
        List<Discount> discountResults = order.getDiscountResults();
        assertEquals(1, discountResults.size());
        Discount discount = discountResults.get(0);
        assertEquals(BigDecimal.valueOf(3.8), discount.getResult());
    }

    @DisplayName("be of type BeverageDiscount")
    @Test
    void check5thBevDiscountIsOfCorrectType() {
        List<Discount> discountResults = order.getDiscountResults();
        assertEquals(1, discountResults.size());
        Discount discount = discountResults.get(0);
        assertDoesNotThrow( () ->  (BeverageDiscount) discount);
    }

    @DisplayName("be of type BeverageDiscount2")
    @Test
    void check5thBevDiscountIsOfCorrectTypeExperimental() {
        List<Discount> discountResults = order.getDiscountResults();
        Discount discount = discountResults.get(0);
        assertInstanceOf(BeverageDiscount.class, discount);
    }

    @DisplayName("calculate Sum for Items in an Order")
    @Test
    void checkOrderIsCorrectlyCalculatesSumWithExtras() {
        assertEquals(BigDecimal.valueOf(19.5), order.getSum());
    }

}
