package App.DAO;

import App.entities.Account;
import App.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDao implements Dao<Account> {

    List<Account> accounts = new ArrayList<>();

    {
        Customer customer1 = new Customer("Jim", "A@111.com", 21);
        Customer customer2 = new Customer("Jon", "B@111.com", 22);
        Customer customer3 = new Customer("Kit", "C@111.com", 23);
        Customer customer4 = new Customer("Jane", "D@111.com", 23);
        Customer customer5 = new Customer("Jan2", "E@111.com", 23);
        Account account1 = new Account(customer1, "USD");
        Account account2 = new Account(customer2, "EUR");
        Account account3 = new Account(customer3, "UAN");
        Account account4 = new Account(customer4, "CHF");
        Account account5 = new Account(customer5, "GBP");
        insertInDb(account1);
        insertInDb(account2);
        insertInDb(account3);
        insertInDb(account4);
        insertInDb(account5);
        System.out.println(accounts);
    }

    public void insertInDb(Account account) {
        if (!accounts.contains(account)) {
            long l1 = generatedIdHoles();          // todo
            System.out.println(l1 + " need test");// need test
            account.setId(l1);
            accounts.add(account);
        }
    }

    public long generatedIdHoles() {
        ArrayList<Long> list = new ArrayList<>(accounts.stream().map(account -> account.getId())
                .toList().stream()
                .sorted().toList());
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i) - i > 1) return i + 1;
        }
        return i + 1;
    }

    @Override
    public Account save(Account account) {
        if (accounts.contains(account)) {
            Account c = accounts.stream().filter(k -> k.equals(account)).findFirst().get();
            c.setBalance( account.getBalance());

            return c;
        }
        return null;

    }

    @Override
    public boolean delete(Account obj) {
        return false;
    }

    @Override
    public void deleteAll(List<Account> entities) {

    }

    @Override
    public void saveAll(List<Account> entities) {

    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Account getOne(long id) {
        return null;
    }
    public Account getOne(String number) {
        return accounts.stream().filter(account -> account.getNumber().equals(number)).findFirst().orElse(null);
    }

}
