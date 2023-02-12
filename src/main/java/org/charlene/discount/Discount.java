package org.charlene.discount;

import org.charlene.Order;

import java.math.BigDecimal;

public abstract class Discount {
    public abstract String getBonusDescription();
    public abstract boolean apply(Order order);
    public abstract BigDecimal getResult();
}
