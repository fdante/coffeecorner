package org.charlene.discount;

import org.charlene.Order;
import org.charlene.OrderLine;

import java.math.BigDecimal;

import java.util.SortedSet;
import java.util.TreeSet;

public class BeverageAndSnackDiscount extends Discount {
    private BigDecimal result;
    private String description;

    public String getBonusDescription() {
        return description;
    }

    public boolean apply(Order order){

        boolean priceDeduction = false;
        boolean foundBev = false;
        boolean foundSnack = false;

        SortedSet<BigDecimal> cheapestExtraSet = new TreeSet<>();

        for ( OrderLine line : order.getOrderLines()){
            cheapestExtraSet.addAll(line.product().getExtras().values());
            if (foundBev && foundSnack){
                break;
            } else if (line.product().isBeverage()){
                foundBev = true;
            } else if (line.product().isSnack()){
                foundSnack = true;
            }
        }

        if (foundBev && foundSnack){
            priceDeduction = true;
            description = "one drink and one snack, one extra free ";
        }

        result = cheapestExtraSet.first();
        return priceDeduction;
    }

    public BigDecimal getResult() {
        return result;
    }
}
