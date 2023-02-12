package org.charlene;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer(123L, "John Smith");
        assertNotNull(customer);
    }

    @Test
    public void testCustomerCreationNoIdFails(){
    assertThrows(IllegalArgumentException.class,
                () -> { Customer Customer = new Customer(null, "John Smith");});
    }

    @Test
    public void testCustomerCreationNoNameFails(){
        assertThrows(IllegalArgumentException.class,
                () -> { Customer Customer = new Customer(123L, null);});
    }
}

