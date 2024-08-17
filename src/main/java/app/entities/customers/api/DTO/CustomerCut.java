package app.entities.customers.api.DTO;

import app.entities.customers.db.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CustomerCut {
    private long id;

    private String name;

    private String email;

    private String phone;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private int age;

    public CustomerCut(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.age = customer.getAge();
        this.createdDate = customer.getCreatedDate();
        this.lastModifiedDate = customer.getLastModifiedDate();
    }
}
