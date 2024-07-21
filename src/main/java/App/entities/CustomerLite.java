package App.entities;

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
    private List<AccountLite> accounts;

    public CustomerLite(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.age = customer.getAge();
        this.accounts = convert(customer.getAccounts());
    }

    public List<AccountLite> convert(List<Account> accounts){
        if((accounts == null)||(accounts.isEmpty())) return new ArrayList<AccountLite>();
        return accounts.stream().map(AccountLite::new).toList();
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

