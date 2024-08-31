package app.controllers;


import app.entities.account.api.DTO.*;
import app.entities.account.db.Account;
import app.entities.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/acc")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountDtoRequestMapper accountDtoRequestMapper;
    private final AccountDtoResponseMapper accountDtoResponseMapper;



    @PutMapping("/rich")
    public boolean updateAccount(@Valid @RequestBody NumberAndSum numberAndSum) {
        log.info("updateAccount plus money ");
        return accountService.updateUp(numberAndSum);
    }

    @PutMapping("poor")
    public boolean decreaseAccount(@Valid @RequestBody NumberAndSum numberAndSum) {
        log.info("updateAccount minus money  ");
        return accountService.updateDown(numberAndSum);
    }

    @PutMapping("transfer")
    public boolean transferAccount(@Valid @RequestBody NumberAndSum numberAndSum) {
        log.info("updateAccounts  transfer  money ");
        return accountService.transfer(numberAndSum);
    }

    @GetMapping("all")
    public List<AccountResponse> getAllAccounts() {
        log.info("Get all accounts ");
        return accountService.getAllInformation().stream()
                .map(accountDtoResponseMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("add")
    public boolean create(@Valid @RequestBody AccountRequest accountRequest) {
        log.info("Create new account ");
        Account account = accountDtoRequestMapper.convertToEntity(accountRequest);
        return accountService.create(account);
    }

    @DeleteMapping("delete/entity")
    public boolean deleteAccount(@Valid @RequestBody AccountRequest accountRequest) {
        log.info("Delete  account ");
        Account account = accountDtoRequestMapper.convertToEntity(accountRequest);
        return accountService.delete(account);
    }
}