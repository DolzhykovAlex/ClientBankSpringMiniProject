package app.entities.account.api.DTO;

import app.entities.account.db.Account;
import app.entities.account.db.Currency;
import app.entities.customers.api.DTO.CustomerCut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountResponse {

    private long id;
    private String number;           // number of account
    private Currency currency;
    private double balance;
    private CustomerCut customer;


    public AccountResponse(Account account) {
        this.id = account.getId();
        this.currency = account.getCurrency();
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.customer = new CustomerCut(account.getCustomer());
    }

}
