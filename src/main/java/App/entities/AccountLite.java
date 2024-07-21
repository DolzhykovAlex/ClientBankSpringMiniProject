package App.entities;

import App.enums.Currency;
import lombok.Data;


@Data
public class AccountLite {

    private long id;
    private final String number;           // number of account
    private final Currency currency;
    private double balance;


    public AccountLite(Account account) {
        this.id = account.getId();
        this.currency = account.getCurrency();
        this.number = account.getNumber();
        this.balance = account.getBalance();
    }

}
