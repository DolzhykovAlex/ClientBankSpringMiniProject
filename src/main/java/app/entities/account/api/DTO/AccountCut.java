package app.entities.account.api.DTO;

import app.entities.account.db.Account;
import app.entities.account.db.Currency;
import lombok.Data;


@Data
public class AccountCut {

    private long id;
    private final String number;           // number of account
    private final Currency currency;
    private double balance;



    public AccountCut(Account account) {
        this.id = account.getId();
        this.currency = account.getCurrency();
        this.number = account.getNumber();
        this.balance = account.getBalance();

    }

}
