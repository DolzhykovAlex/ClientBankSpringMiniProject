package app.entities.account.api.DTO;


import app.entities.account.db.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountCut {

    private long id;
    private  String number;           // number of account
    private  Currency currency;
    private double balance;


}
