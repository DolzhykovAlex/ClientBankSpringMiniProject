package app.entities.account.api.DTO;

import app.entities.account.db.Account;
import app.entities.account.db.Currency;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Data
public class AccountRequest {

    private long id;
    private final String number;           // number of account
    private final Currency currency;

    @Min(0)
    private double balance;



    public AccountRequest(Account account) {
        this.id = account.getId();
        this.currency = account.getCurrency();
        this.number = account.getNumber();
        this.balance = account.getBalance();

    }

}
