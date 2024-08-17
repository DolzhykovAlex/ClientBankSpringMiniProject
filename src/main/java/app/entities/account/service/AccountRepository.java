package app.entities.account.service;

import app.entities.account.db.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getFirstByNumber(String number);


}
