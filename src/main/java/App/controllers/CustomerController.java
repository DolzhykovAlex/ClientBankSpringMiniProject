package App.controllers;

import App.DAO.CustomerDao;
import App.entities.Account;
import App.entities.Customer;
import App.entities.CustomerLite;
import App.entities.CustomerMarked;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import App.services.CustomerService;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("app")
public class CustomerController {

    private final CustomerService customerService = new CustomerService(new CustomerDao());


    @GetMapping("customers")
    public List<CustomerLite> findAll() {
        return customerService.findAll();
    }


    @GetMapping("customers/one")
    public CustomerMarked getOneInformation(@RequestParam("id") Long id) {
        return checkEntity(customerService.getOneInformation(id));

    }


    @PostMapping("customers/add")
    public void create(@RequestBody Customer customer) {
        customerService.create(customer);
    }

    @DeleteMapping("customers/del/entity")
    public void delete(@RequestBody Customer customer) {
        customerService.delete(customer);
    }

    @DeleteMapping("customers/del/personal")
    public void delete(@RequestParam("id") Long id) {
        customerService.delete(customerService.getOneInformation(id));
    }

    @PutMapping("customers/del/acc")
    public boolean deleteAccount(@RequestBody Account account ) {
      return   customerService.deleteAccount(account);
    }

    @PutMapping("customers/account")
    public boolean createAccount(@RequestBody Account account) {;
       return customerService.createAccount(account);
    }

    @PutMapping("customers/up/user")
    public CustomerMarked update(@RequestBody Customer customer) {
        return checkEntity(customerService.update(customer));
    }

    @PutMapping("customers/up/admin")
    public CustomerMarked updateByAdmin(@RequestParam("id") long id, @RequestBody Customer customer) {
        return checkEntity(customerService.updateByAdmin(id, customer));
    }



    public CustomerMarked checkEntity(Customer customer) {
        if (customer == null) {
            return new CustomerMarked("Customer not found ",new CustomerLite (new Customer("--", "--", 0)));
        }
        return new CustomerMarked("Work done", new CustomerLite(customer));
    }

}
