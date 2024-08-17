package app.entities.employee.db;

import app.entities.AbstractClass;
import app.entities.customers.db.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter

@Entity
@NoArgsConstructor
public class Employee extends AbstractClass {

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "works")
    private List<Customer> workers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, address);
    }
}
