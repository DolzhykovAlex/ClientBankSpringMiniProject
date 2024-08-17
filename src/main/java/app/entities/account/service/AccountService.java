package app.entities.account.service;

import app.entities.account.api.DTO.NumberAndSum;
import app.entities.account.db.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllInformation();



    boolean updateUp(NumberAndSum numberAndSum);


     boolean updateDown(NumberAndSum numberAndSum);

     boolean transfer(NumberAndSum numberAndSum);

     boolean create(Account account);

     boolean delete(Account account);
}
