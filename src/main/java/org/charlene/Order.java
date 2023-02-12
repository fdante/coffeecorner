package org.charlene;

import org.charlene.discount.Discount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.charlene.Validation.checkThat;


public class Order {
    private final long id;
    private final Customer customer;
    private final LocalDateTime dateTime = LocalDateTime.now();
    private final List<OrderLine> orderLines;
    private final List<Discount> discountsToCheck;
    private final List<Discount> discountResults;

    public Order(long id, Customer customer, List<OrderLine> lines, List<Discount> discountsToCheck) {
        checkThat(customer != null, "customer must not be null");
        checkThat(dateTime != null, "dateTime must not be null");
        checkThat(lines != null && !lines.isEmpty(), "lines must not be null or empty");

        this.id = id;
        this.customer = customer;
        this.discountsToCheck = List.copyOf(discountsToCheck);
        this.orderLines = List.copyOf(lines);
        discountResults = applyDiscounts();
    }

    public List<Discount> applyDiscounts() {
        return Collections.unmodifiableList(discountsToCheck.stream().filter(
                discount -> discount.apply(this)).collect(Collectors.toList()));
    }

    public BigDecimal getSum() {
        var sum = new BigDecimal(0);
        for (OrderLine line : orderLines) {
            sum = sum.add(line.getSum());
        }
        return sum;
    }

    public BigDecimal getFinalPrice() {
        return getSum().subtract(discountResults.stream().map(Discount::getResult).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("Charlene's Coffee Corner : ");
        sb.append("\n------------------------------------------------------------");
        for (OrderLine orderLine : orderLines) {
            sb.append(orderLine.toString());
        }
        sb.append("\n------------------------------------------------------------");
        String total = sb + String.format("\n%-50s %9.2f", "Sum: ", getSum());

        sb = new StringBuilder(total);
        for (Discount discount : discountResults) {
            String discountRow = String.format("\n%-50s %9.2f", "Discount: " + discount.getBonusDescription(), discount.getResult());
            sb.append(discountRow);
        }
        sb.append("\n------------------------------------------------------------");
        String discounts = sb.toString();
        String finalPrice = String.format("\n%-50s %9.2f", "Total: ", getFinalPrice());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String date = String.format("\n" + now.format(formatter));
        sb = new StringBuilder(discounts).append(finalPrice).append(date);
        return sb.toString();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderLines() {
        return  orderLines;
    }

    public List<Discount> getDiscountsToCheck() {
        return discountsToCheck;
    }

    public List<Discount> getDiscountResults() {
        return List.copyOf(discountResults);
    }
}
