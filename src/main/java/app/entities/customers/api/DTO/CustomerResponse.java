package app.entities.customers.api.DTO;


import app.entities.account.api.DTO.AccountCut;
import app.entities.employee.api.DTO.EmployeeCut;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponse {

    private long id;

    private String name;

    private String email;

    private String phone;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @JsonIgnore
    private String password;

    private int age;

    private List<EmployeeCut> works;

    private List<AccountCut>  accounts;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerResponse that = (CustomerResponse) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}

