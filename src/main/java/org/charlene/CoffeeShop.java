package org.charlene;

import org.charlene.discount.BeverageAndSnackDiscount;
import org.charlene.discount.BeverageDiscount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeShop {
    public static void main(String[] args) {
        var customer = new Customer(500567L, "Joe Smith");
        Map<Customer, Integer> beverages = new HashMap<>();
        beverages.put(customer, 0);
        BeverageDiscount discountCalculator = new BeverageDiscount(beverages);
        BeverageAndSnackDiscount beverageAndSnackDiscount = new BeverageAndSnackDiscount();

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

        List<Product> products = ProductHelpers.parseInput(inputs);

        var lines = new ArrayList<OrderLine>();
        products.stream().forEach(product -> lines.add(new OrderLine(product)));

        var order = new Order(200201L, customer, lines, List.of(discountCalculator, beverageAndSnackDiscount));
        System.out.println(order);

    }
}
