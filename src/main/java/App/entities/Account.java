package App.entities;

import App.enums.Currency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter

public class Account {

    private long id;
    private final String number;           // number of account
    private final Currency currency;
    private double balance;
    private final Customer customer;

    public Account(Customer customer, String currency) {
        this.customer = customer;
        this.currency = Currency.valueOf(currency);
        this.number = UUID.randomUUID().toString();
        this.balance = 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", currency=" + currency +
                ", number='" + number + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && currency == account.currency && Objects.equals(number, account.number) && Objects.equals(customer, account.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, currency, customer);
    }


}
