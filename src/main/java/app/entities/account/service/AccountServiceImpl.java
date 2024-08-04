package app.entities.account.service;


import app.entities.account.api.DTO.AccountLite;
import app.entities.account.api.DTO.NumberAndSum;
import app.entities.account.db.Account;
import app.entities.account.db.AccountDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public Account getOneInformation(long id) {
        return accountDao.getOne(id);
    }


    public List<AccountLite> getAllInformation() {
        return accountDao.findAll().stream().map(this::convertToAccountLite).toList();
    }

    public AccountLite convertToAccountLite(Account account) {
        return new AccountLite(account);
    }

    public boolean updateUp(NumberAndSum numberAndSum) {
        String s1 = numberAndSum.getNumbers().get(0);
        Account account = accountDao.getOne(s1);
        double sum = Double.parseDouble(numberAndSum.getSum());
        if (account != null) {
            account.setBalance(account.getBalance() + sum);
            accountDao.save(account);
            return true;
        }
        return false;
    }


    public boolean updateDown(NumberAndSum numberAndSum) {

        String s1 = numberAndSum.getNumbers().get(0);
        Account account = accountDao.getOne(s1);
        double sum = Double.parseDouble(numberAndSum.getSum());
        if (account != null) {
            if ((account.getBalance() - sum) >= 0) {
                account.setBalance(account.getBalance() - sum);
                accountDao.save(account);
                return true;
            }
        }
        return false;
    }

    public boolean transfer(NumberAndSum numberAndSum) {
        String s1 = numberAndSum.getNumbers().get(0);
        String s2 = numberAndSum.getNumbers().get(1);
        Account account1 = accountDao.getOne(s1);
        Account account2 = accountDao.getOne(s2);
        double sum = Double.parseDouble(numberAndSum.getSum());
        if (account1 != null && account2 != null) {
            if (account1.getBalance() > sum) {
                account1.setBalance(account1.getBalance() - sum);
                account2.setBalance(account2.getBalance() + sum);
                accountDao.save(account1);
                accountDao.save(account2);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean create(Account account) {
        if (account != null) {
            if (accountDao.getOne(account.getNumber()) == null) {
                Account account1 = new Account();
                account1.setCustomer(account.getCustomer());
                account1.setCurrency(account.getCurrency());
                accountDao.save(account1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Account account) {
        if (accountDao.getOne(account.getNumber()) != null) {
            return accountDao.delete(accountDao.getOne(account.getNumber()));
        }
        return false;
    }

}
