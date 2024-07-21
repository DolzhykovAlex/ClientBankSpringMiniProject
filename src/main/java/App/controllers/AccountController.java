package App.controllers;

import App.DAO.AccountDao;
import App.entities.AccountLite;
import App.entities.NumberAndSum;
import App.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("acc")
public class AccountController {

    private final AccountService accountService = new AccountService(new AccountDao());

    @PutMapping("rich")
    public boolean updateAccount(@RequestBody NumberAndSum numberAndSum) {
        System.out.println(numberAndSum.getSum()+ " cumma" );
        return accountService.updateUp(numberAndSum);
    }

    @PutMapping("poor")
    public boolean decreaseAccount(@RequestBody NumberAndSum numberAndSum) {
        return accountService.updateDown(numberAndSum);
    }

    @PutMapping("transfer")
    public boolean transferAccount(@RequestBody NumberAndSum numberAndSum) {
        return accountService.transfer(numberAndSum);
    }

    @GetMapping("all")
    public List<AccountLite> getAllAccounts() {
       return accountService.getAllInformation();

    }


}
