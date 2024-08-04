package app.controllers;


import app.entities.account.api.DTO.AccountLite;
import app.entities.account.api.DTO.NumberAndSum;
import app.entities.account.db.Account;
import app.entities.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("acc")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PutMapping("rich")
    public boolean updateAccount(@RequestBody NumberAndSum numberAndSum) {
        System.out.println(numberAndSum.getSum() + " cumma");
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

    @PostMapping("add")
    public boolean create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @DeleteMapping("delete/entity")
    public boolean deleteAccount(@RequestBody Account account) {
        return accountService.delete(account);
    }
}