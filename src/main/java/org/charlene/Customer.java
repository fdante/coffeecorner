package org.charlene;


import static org.charlene.Validation.checkThat;

public record Customer(Long id, String name) {
    public Customer(Long id, String name) {
        checkThat(id != null, "id must not be null");
        checkThat(name != null, "name must not be null");

        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello : " + name;
    }
}
