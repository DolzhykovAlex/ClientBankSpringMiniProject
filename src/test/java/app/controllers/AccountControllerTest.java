package app.controllers;

import app.entities.account.api.DTO.*;
import app.entities.account.db.Account;
import app.entities.account.db.Currency;
import app.entities.account.service.AccountService;
import app.entities.customers.db.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureDataJpa
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountDtoRequestMapper accountDtoRequestMapper;

    @MockBean
    private AccountDtoResponseMapper accountDtoResponseMapper;


    @Test
    void testUpdateAccount() throws Exception {
        NumberAndSum numberAndSum = new NumberAndSum();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(numberAndSum);
        when(accountService.updateUp(numberAndSum)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/acc/rich")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


    @Test
    public void decreaseAccountTest() throws Exception {
        NumberAndSum numberAndSum = new NumberAndSum();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(numberAndSum);
        when(accountService.updateDown(numberAndSum)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/acc/poor")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void transferAccountTest() throws Exception {
        NumberAndSum numberAndSum = new NumberAndSum();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(numberAndSum);
        when(accountService.transfer(numberAndSum)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/acc/transfer")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void getAllAccountsTest() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("customer1");
        customer1.setEmail("customer1@gmail.com");
        customer1.setPassword("password");
        customer1.setAccounts(new ArrayList<>());
        Customer customer2 = new Customer();
        customer2.setId(1L);
        customer2.setName("customer2");
        customer2.setEmail("customer2@gmail.com");
        customer2.setPassword("password2");
        customer2.setAccounts(new ArrayList<>());
        Account account1 = new Account(customer1, "USD");
        account1.setId(1L);
        account1.setNumber("1");
        Account account2 = new Account(customer2, "USD");
        account2.setId(2L);
        account2.setNumber("2");
        AccountResponse accountResponse1 = new AccountResponse(account1);
        AccountResponse accountResponse2 = new AccountResponse(account2);

        List<Account> accounts = Arrays.asList(account1, account2);
        when(accountService.getAllInformation()).thenReturn(accounts);
        when(accountDtoResponseMapper.convertToDto(account1)).thenReturn(accountResponse1);
        when(accountDtoResponseMapper.convertToDto(account2)).thenReturn(accountResponse2);

        mockMvc.perform(get("/acc/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"number\":\"1\",\"currency\":\"USD\",\"balance\":0.0,\"customer\":{\"id\":1,\"name\":\"customer1\",\"email\":\"customer1@gmail.com\",\"phone\":null,\"createdDate\":null,\"lastModifiedDate\":null,\"age\":0}}," +
                        "{\"id\":2,\"number\":\"2\",\"currency\":\"USD\",\"balance\":0.0,\"customer\":{\"id\":1,\"name\":\"customer2\",\"email\":\"customer2@gmail.com\",\"phone\":null,\"createdDate\":null,\"lastModifiedDate\":null,\"age\":0}}]"));
    }

    @Test
    public void createTest() throws Exception {
        Account account = new Account();
        account.setId(2L);
        account.setNumber("1");
        account.setCurrency(Currency.EUR);
        AccountRequest accountRequest = new AccountRequest(account);
        ObjectMapper objectMapper = new ObjectMapper();

        when(accountDtoRequestMapper.convertToEntity(accountRequest)).thenReturn(account);
        String jsonRequest = objectMapper.writeValueAsString(accountRequest);
        Mockito.when(accountService.create(account)).thenReturn(true);

        System.out.println(jsonRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/acc/add")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void deleteAccountTest() throws Exception {
        Account account = new Account();
        account.setId(2L);
        account.setNumber("1");
        account.setCurrency(Currency.EUR);
        AccountRequest accountRequest = new AccountRequest(account);
        ObjectMapper objectMapper = new ObjectMapper();

        when(accountDtoRequestMapper.convertToEntity(accountRequest)).thenReturn(account);
        String jsonRequest = objectMapper.writeValueAsString(accountRequest);
        Mockito.when(accountService.delete(account)).thenReturn(true);

        System.out.println(jsonRequest);
        mockMvc.perform(MockMvcRequestBuilders.delete("/acc/delete/entity")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}


