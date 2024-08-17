package app.entities.customers.api.DTO;

import app.entities.account.api.DTO.AccountCut;
import app.entities.employee.api.DTO.EmployeeCut;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequest {

    private long id;

    @Size(min = 2, message = "Client name should have at least 2 characters")
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = " Bad email")
    private String email;

    @Pattern(regexp = "(\\+380)[0-9]{9}", message = "Bad phone")
    private String phone;

    private String password;

    @Min(18)
    private int age;

    private List<AccountCut> accounts;

    private List<EmployeeCut> works;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRequest that = (CustomerRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
