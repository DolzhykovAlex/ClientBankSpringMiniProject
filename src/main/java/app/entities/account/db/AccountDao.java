package app.entities.account.db;

import app.entities.Dao;
import app.entities.account.service.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Service
public class AccountDao implements Dao<Account> {

    private final AccountRepository ar;



    @Override
    public Account save(Account account) {
        ar.save(account);
        return account;
    }

    @Override
    public boolean delete(Account obj) {
        ar.delete(obj);
        return true;
    }

    @Override
    public void deleteAll(List<Account> entities) {
    }

    @Override
    public void saveAll(List<Account> entities) {
    }

    @Override
    public List<Account> findAll() {
        return ar.findAll();
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
        return ar.findAll().stream().filter(account -> account.getNumber().equals(number)).findFirst().orElse(null);
    }

}
