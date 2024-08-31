package app.entities.account.service;


import app.entities.account.api.DTO.NumberAndSum;
import app.entities.account.db.Account;
import app.exeptions.customExeption.AccountNotFoundException;
import app.exeptions.customExeption.NotEnoughMoneyException;
import app.exeptions.customExeption.ParseStringToDoubleException;
import app.websocket.dto.RsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final String WS_PATH = "/front/endpoint1";


    public List<Account> getAllInformation() {
        return accountRepository.findAll();
    }


    public boolean updateUp(NumberAndSum numberAndSum) {
      double sum;
        try {
            sum = Double.parseDouble(numberAndSum.getSum());
        } catch (NumberFormatException e) {
            throw new ParseStringToDoubleException("AccountService- Method updateUp ");
        }
        if (sum > 0) {
            String s1 = numberAndSum.getNumbers().get(0);
            Account account = accountRepository.getFirstByNumber(s1);
            if (account == null) throw new AccountNotFoundException("AccountService- Method updateUp ");
            account.setBalance(account.getBalance() + sum);
            accountRepository.save(account);

            String answer = "account " + account.getNumber() + " was successfully updated on + " + numberAndSum.getSum();
            messagingTemplate.convertAndSend(WS_PATH, new RsDto(answer));
            return true;
        }
        return false;
    }


    public boolean updateDown(NumberAndSum numberAndSum) {
        double sum;
        try {
            sum = Double.parseDouble(numberAndSum.getSum());
        } catch (NumberFormatException e) {
            throw new ParseStringToDoubleException("AccountService C updateDown ");
        }
        if (sum > 0) {
            String s1 = numberAndSum.getNumbers().get(0);
            Account account = accountRepository.getFirstByNumber(s1);
            if (account == null) throw new AccountNotFoundException("AccountServiceImpl- Method updateDown ");
            if ((account.getBalance() - sum) < 0)
                throw new NotEnoughMoneyException("AccountService- Method updateDown ");
            account.setBalance(account.getBalance() - sum);
            accountRepository.save(account);
            String answer = "account " + account.getNumber() + " was successfully updated on minus(-) " + numberAndSum.getSum();
            messagingTemplate.convertAndSend(WS_PATH, new RsDto(answer));
            return true;
        }
        return false;
    }

    public boolean transfer(NumberAndSum numberAndSum) {
        String s1 = numberAndSum.getNumbers().get(0);
        String s2 = numberAndSum.getNumbers().get(1);
        Account account1 = accountRepository.getFirstByNumber(s1);
        Account account2 = accountRepository.getFirstByNumber(s2);
        double sum = Double.parseDouble(numberAndSum.getSum());
        if (account1 == null || account2 == null)
            throw new AccountNotFoundException("AccountService Method transfer entity account");
        if (account1.getBalance() < sum) throw new NotEnoughMoneyException("Method transfer entity account");
        account1.setBalance(account1.getBalance() - sum);
        account2.setBalance(account2.getBalance() + sum);
        accountRepository.save(account1);
        accountRepository.save(account2);
        String answer1 = "Transfer: account " + account1.getNumber() + " was successfully updated on minus(-)  " + numberAndSum.getSum();
        String answer2 = "Transfer: account " + account2.getNumber() + " was successfully updated on + " + numberAndSum.getSum();
        messagingTemplate.convertAndSend(WS_PATH, new RsDto(answer1));
        messagingTemplate.convertAndSend(WS_PATH, new RsDto(answer2));

        return true;
    }

    @Override
    public boolean create(Account account) {
        if (account == null)
            throw new AccountNotFoundException("AccountService Method crate: you enter not valid account ");
        if (accountRepository.getFirstByNumber(account.getNumber()) != null)
            throw new AccountNotFoundException("AccountService Method create: this account Exist, cant create the same ");
        Account account1 = new Account();
        account1.setCustomer(account.getCustomer());
        account1.setCurrency(account.getCurrency());
        accountRepository.save(account1);
        return true;
    }

    @Override
    public boolean delete(Account account) {
        if (accountRepository.getFirstByNumber(account.getNumber()) == null)
            throw new AccountNotFoundException("AccountService -Method delete ");
        accountRepository.delete(accountRepository.getFirstByNumber(account.getNumber()));
        return true;

    }

}
