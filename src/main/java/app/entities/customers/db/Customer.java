package app.entities.customers.db;

import app.entities.AbstractClass;
import app.entities.account.db.Account;
import app.entities.employee.db.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter

@Entity
@NoArgsConstructor
public class Customer extends AbstractClass {



    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @ManyToMany()
    private List<Employee> works;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Account> accounts;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + this.getId() + '\''+
                "created Date='" + this.getCreatedDate() + '\''+
                "Last Modified Date='" + this.getLastModifiedDate() + '\''+

                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", works=" + works +
                ", accounts=" + accounts +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
