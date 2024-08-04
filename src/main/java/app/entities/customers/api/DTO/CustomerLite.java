package app.entities.customers.api.DTO;

import app.entities.account.api.DTO.AccountCut;
import app.entities.account.db.Account;
import app.entities.customers.db.Customer;
import app.entities.employee.api.DTO.EmployeeCut;
import app.entities.employee.db.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
public class CustomerLite {

    private long id;
    private String name;
    private String email;
    private int age;
    private List<AccountCut> accounts;
    private List<EmployeeCut> works;

    public CustomerLite(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.age = customer.getAge();
        this.accounts = convertA(customer.getAccounts());
        this.works =convertE(customer.getWorks());
    }

    public List<AccountCut> convertA(List<Account> accounts){
        if((accounts == null)||(accounts.isEmpty())) return new ArrayList<>();
        return accounts.stream().map(AccountCut::new).toList();
    }
    public List<EmployeeCut> convertE(List<Employee> employees){
        if((employees == null)||(employees.isEmpty())) return new ArrayList<>();
        return employees.stream().map(EmployeeCut::new).toList();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerLite that = (CustomerLite) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}

