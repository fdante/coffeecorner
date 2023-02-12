package org.charlene;

import org.charlene.discount.BeverageAndSnackDiscount;
import org.charlene.discount.BeverageDiscount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Order class should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTest {

    Order order;
    List<Product> products;

    @BeforeAll
    void setupEnv() {
        Customer customer = new Customer(500567L, "Joe Smith");
        Map<Customer, Integer> beverages = new HashMap<>();
        beverages.put(customer, 0);
        BeverageDiscount discountCalculator = new BeverageDiscount(beverages);
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

        products = ProductHelpers.parseInput(inputs);

        var lines = new ArrayList<OrderLine>();
        products.stream().forEach(product -> lines.add(new OrderLine(product)));
        order = new Order(200201L, customer, lines, List.of(discountCalculator));

    }

    @DisplayName("not allow modification of orderLines after order has been created")
    @Test
    public void checkOrderLineModFails() {
        assertThrows(UnsupportedOperationException.class, () -> order.getOrderLines().add(new OrderLine(products.get(0))));
    }

    @DisplayName("not allow modification of Discount inputs after order has been created")
    @Test
    public void checkDiscountInputModFails() {
        assertThrows(UnsupportedOperationException.class, () -> order.getDiscountsToCheck().add(new BeverageAndSnackDiscount()));
    }

    @DisplayName("not allow modification of Discount results after order has been created")
    @Test
    public void checkDiscountResultModFails() {
        assertThrows(UnsupportedOperationException.class, () -> order.getDiscountResults().add(new BeverageAndSnackDiscount()));
    }

    @DisplayName("correctly calculate Sum for Items in an Order")
    @Test
    void checkOrderIsCorrectlyCalculatesSumWithExtras() {
        // assertEquals(BigDecimal.valueOf(44.40), order.getSum())
        // Expected :44.4
        // Actual   :44.40

        //specify a tolerance of a magnitude smaller than the desired precision -> no rounding, for the test
        assertEquals(Double.valueOf(44.40), order.getSum().doubleValue(), 0.001);
    }
}