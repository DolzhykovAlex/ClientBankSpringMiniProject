package app.controllers;

import app.entities.customers.api.DTO.CustomerLite;
import app.entities.customers.api.DTO.CustomerMarked;
import app.entities.customers.db.Customer;
import app.entities.customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("app/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("")
    public List<CustomerLite> findAl() {
        return customerService.findAll();
    }


    @GetMapping("one")
    public CustomerMarked getOneInformation(@RequestParam("id") Long id) {
        return checkEntity(customerService.getOneInformation(id));
    }

    @PostMapping("add")
    public void create(@RequestBody Customer customer) {
        System.out.println(customer+" WOOOOOOOOO");
        customerService.create(customer);
    }

    @DeleteMapping("del/entity")
    public void delete(@RequestBody Customer customer) {
        customerService.delete(customer);
    }

    @DeleteMapping("del/personal")
    public boolean delete(@RequestParam("id") Long id) {
      return   customerService.deleteById(id);
    }



    @PutMapping("up/user")
    public CustomerMarked update(@RequestBody Customer customer) {
        return checkEntity(customerService.update(customer));
    }

    @PutMapping("up/admin")
    public CustomerMarked updateByAdmin(@RequestParam("id") long id, @RequestBody Customer customer) {
        return checkEntity(customerService.updateByAdmin(id, customer));
    }


    public CustomerMarked checkEntity(Customer customer) {
        if (customer == null) {
            return new CustomerMarked("Customer not found ", new CustomerLite(new Customer("--", "--", 0)));
        }
        return new CustomerMarked("Work done", new CustomerLite(customer));
    }
}
