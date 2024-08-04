package app.entities.account.service;

import app.entities.account.api.DTO.AccountLite;
import app.entities.account.api.DTO.NumberAndSum;
import app.entities.account.db.Account;

import java.util.List;

public interface AccountService {
    public List<AccountLite> getAllInformation();

    public AccountLite convertToAccountLite(Account account);

    public boolean updateUp(NumberAndSum numberAndSum);


    public boolean updateDown(NumberAndSum numberAndSum);

    public boolean transfer(NumberAndSum numberAndSum);

    public boolean create(Account account);

    public boolean delete(Account account);
}
