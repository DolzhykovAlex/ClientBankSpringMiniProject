package app.entities.account.service;


import app.entities.account.api.DTO.NumberAndSum;
import app.entities.account.db.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;


    public List<Account> getAllInformation() {
        return accountRepository.findAll();
    }


    public boolean updateUp(NumberAndSum numberAndSum) {
        double sum = Double.parseDouble(numberAndSum.getSum());
        if (sum > 0) {
            String s1 = numberAndSum.getNumbers().get(0);
            Account account = accountRepository.getFirstByNumber(s1);
            if (account != null) {
                account.setBalance(account.getBalance() + sum);
                accountRepository.save(account);
                return true;
            }
        }
        return false;
    }


    public boolean updateDown(NumberAndSum numberAndSum) {
        double sum = Double.parseDouble(numberAndSum.getSum());
        if (sum > 0) {
            String s1 = numberAndSum.getNumbers().get(0);
            Account account = accountRepository.getFirstByNumber(s1);
            if (account != null) {
                if ((account.getBalance() - sum) >= 0) {
                    account.setBalance(account.getBalance() - sum);
                    accountRepository.save(account);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean transfer(NumberAndSum numberAndSum) {
        String s1 = numberAndSum.getNumbers().get(0);
        String s2 = numberAndSum.getNumbers().get(1);
        Account account1 = accountRepository.getFirstByNumber(s1);
        Account account2 = accountRepository.getFirstByNumber(s2);
        double sum = Double.parseDouble(numberAndSum.getSum());
        if (account1 != null && account2 != null) {
            if (account1.getBalance() > sum) {
                account1.setBalance(account1.getBalance() - sum);
                account2.setBalance(account2.getBalance() + sum);
                accountRepository.save(account1);
                accountRepository.save(account2);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean create(Account account) {
        if (account != null) {
            if (accountRepository.getFirstByNumber(account.getNumber()) == null) {
                Account account1 = new Account();
                account1.setCustomer(account.getCustomer());
                account1.setCurrency(account.getCurrency());
                accountRepository.save(account1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Account account) {
        if (accountRepository.getFirstByNumber(account.getNumber()) != null) {
            accountRepository.delete(accountRepository.getFirstByNumber(account.getNumber()));
            return true;
        }
        return false;
    }

}
