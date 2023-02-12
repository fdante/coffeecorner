package org.charlene;

import java.math.BigDecimal;

/* if quantity needs to be shown or receipt, we can insert here
 "small coffee       3     2.5"

  instead of

 "small coffee             2.5
  small coffee             2.5
  small coffee             2.5 "
  */

public record OrderLine(Product product) {

    @Override
    public String toString() {
        return String.format("\n%-50s %9.2f" , product.getDescription(),  product.getFinalPrice());
    }

    public BigDecimal getSum(){
        return product.getFinalPrice();
    }
}
