package org.charlene.discount;

import org.charlene.Customer;
import org.charlene.Order;
import org.charlene.OrderLine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BeverageDiscount extends Discount {
    private Map<Customer, Integer> beverages;
    private String description;
    private BigDecimal result;


    public BeverageDiscount(Map<Customer, Integer> map) {
        this.beverages = new HashMap<>(map);
    }

    public String getBonusDescription(){
        return description;
    }

    public boolean apply(Order order){

        Boolean priceDeduction = false;
        var discount = new BigDecimal(0.0);
        for ( OrderLine line : order.getOrderLines()){

            if (line.product().isBeverage()){
                var beverateCntHistory = beverages.get(order.getCustomer());
                if ((beverateCntHistory + 1) % 5 == 0){
                    beverages.put(order.getCustomer(), 0);
                    discount = discount.add(line.product().getFinalPrice());
                    priceDeduction = true;
                    description = "every 5th drink is free!: ";
                } else {
                    beverages.put(order.getCustomer(), ++beverateCntHistory);
                }
            }
        }
        result = discount;
        return priceDeduction;
    }

    public BigDecimal getResult() {
        return result;
    }
}
