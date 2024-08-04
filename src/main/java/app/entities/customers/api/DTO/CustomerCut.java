package app.entities.customers.api.DTO;

import app.entities.customers.db.Customer;
import lombok.Data;

@Data
public class CustomerCut {
    private long id;
    private String name;
    private String email;
    private int age;

    public CustomerCut(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.age = customer.getAge();
    }
}
