package app.services;


import app.entities.account.api.DTO.NumberAndSum;
import app.entities.account.db.Account;
import app.entities.account.service.AccountRepository;
import app.entities.account.service.AccountServiceImpl;
import app.exeptions.customExeption.AccountNotFoundException;
import app.exeptions.customExeption.NotEnoughMoneyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Test
    void getAllInformationTest() {

        Account account1 = new Account();
        Account account2 = new Account();
        Mockito.when(accountRepository.findAll()).thenReturn(List.of(account1, account2));
        Assertions.assertEquals(2, accountServiceImpl.getAllInformation().size());
    }

    @Test
    void updateUpTestPositive() {
        Account account1 = new Account();
        NumberAndSum numberAndSum = new NumberAndSum();
        numberAndSum.setNumbers(List.of(account1.getNumber()));
        numberAndSum.setSum("100");
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(account1);
        Assertions.assertTrue(accountServiceImpl.updateUp(numberAndSum));
    }

    @Test
    void updateUpTestNegative() {

        Account account1 = new Account();
        NumberAndSum numberAndSum = new NumberAndSum();
        numberAndSum.setNumbers(List.of(account1.getNumber()));
        numberAndSum.setSum("-100");
        Assertions.assertFalse(accountServiceImpl.updateUp(numberAndSum));
    }


    @Test
    void updateDownTestPositive() {

        Account account1 = new Account();
        account1.setBalance(1000);
        NumberAndSum numberAndSum = new NumberAndSum();
        numberAndSum.setNumbers(List.of(account1.getNumber()));
        numberAndSum.setSum("100");
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(account1);
        Assertions.assertTrue(accountServiceImpl.updateDown(numberAndSum));
    }

    @Test
    void updateDownTestNegative() {

        Account account1 = new Account();
        account1.setBalance(90);
        NumberAndSum numberAndSum = new NumberAndSum();
        numberAndSum.setNumbers(List.of(account1.getNumber()));
        numberAndSum.setSum("100");
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(account1);
        Assertions.assertThrows(NotEnoughMoneyException.class, () -> accountServiceImpl.updateDown(numberAndSum));
    }

    @Test
    public void transferTestPositive() {

        Account account1 = new Account();
        Account account2 = new Account();
        account1.setBalance(500);
        NumberAndSum numberAndSum = new NumberAndSum();
        numberAndSum.setNumbers(List.of(account1.getNumber(), account2.getNumber()));
        numberAndSum.setSum("100");
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(account1);
        Mockito.when(accountRepository.getFirstByNumber(account2.getNumber())).thenReturn(account2);
        Mockito.when(accountRepository.save(account1)).thenReturn(account1);
        Mockito.when(accountRepository.save(account2)).thenReturn(account2);
        Assertions.assertTrue(accountServiceImpl.transfer(numberAndSum));
    }

    @Test
    public void transferTestNegative() {

        Account account1 = new Account();
        Account account2 = new Account();
        account1.setBalance(50);
        NumberAndSum numberAndSum = new NumberAndSum();
        numberAndSum.setNumbers(List.of(account1.getNumber(), account2.getNumber()));
        numberAndSum.setSum("100");
        Assertions.assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.transfer(numberAndSum));

    }


    @Test
    public void createTestPositive() {

        Account account1 = new Account();
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(null);
        Assertions.assertTrue(accountServiceImpl.create(account1));
    }

    @Test
    public void createTestNegative() {
        Account account1 = new Account();
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(account1);
       Assertions.assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.create(account1));

    }


    @Test
    public void deleteTestPositive() {

        Account account1 = new Account();
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(account1);
        Assertions.assertTrue(accountServiceImpl.delete(account1));
    }

    @Test
    public void deleteTestNegative() {
        Account account1 = new Account();
        Mockito.when(accountRepository.getFirstByNumber(account1.getNumber())).thenReturn(null);
        Assertions.assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.delete(account1));

    }

}


