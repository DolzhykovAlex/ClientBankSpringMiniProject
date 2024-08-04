package app.entities.account.api.DTO;

import app.entities.account.db.Account;
import app.entities.account.db.Currency;
import app.entities.customers.api.DTO.CustomerCut;
import lombok.Data;


@Data
public class AccountLite {

    private long id;
    private final String number;           // number of account
    private final Currency currency;
    private double balance;
    private CustomerCut customer;


    public AccountLite(Account account) {
        this.id = account.getId();
        this.currency = account.getCurrency();
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.customer = new CustomerCut(account.getCustomer());
    }

}
