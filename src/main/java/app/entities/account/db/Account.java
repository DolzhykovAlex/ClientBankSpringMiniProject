package app.entities.account.db;


import app.entities.customers.db.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity

public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "balance")
    private double balance;


    @ManyToOne()
    private Customer customer;


    public Account(Customer customer, String currency) {
        this.customer = customer;
        this.currency = Currency.valueOf(currency);
        this.number = UUID.randomUUID().toString();
        this.balance = 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return Objects.equals(number, account.number) && currency == account.currency && Objects.equals(customer, account.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), number, currency, customer);
    }

    public Account() {
        this.number = UUID.randomUUID().toString();
    }


}
