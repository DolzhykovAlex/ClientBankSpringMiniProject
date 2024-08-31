package app.controllers;


import app.entities.customers.api.DTO.*;
import app.entities.customers.db.Customer;
import app.entities.customers.service.CustomerService;
import app.exeptions.customExeption.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("app/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerDtoRequestMapper requestDtoMapper;
    private final CustomerDtoResponseMapper customerDtoResponseMapper;


    @GetMapping()
    public List<CustomerResponse> getItems2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Pageable request customers: page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return customerService.findAll(pageable).stream()
                .map(customerDtoResponseMapper::convertToDto)
                .toList();
    }

    @GetMapping("all")
    public List<CustomerResponse> findAll() {
        log.info("Get request all customers");
        return customerService.findAll().stream()
                .map(customerDtoResponseMapper::convertToDto)
                .collect(Collectors.toList());
    }


    @GetMapping("one")
    public CustomerMarked getOneInformation(@RequestParam("id") Long id) {
        log.info("Get request one customer: id={}", id);
        return checkEntity(customerService.getOneInformation(id),"get Customer from id");
    }

    @PostMapping("add")
    public CustomerMarked create(@Valid @RequestBody CustomerRequest customerRequest) {
        log.info(" Try Create new  customer");
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return checkEntity(customerService.create(customer),"add Customer");
    }

    @DeleteMapping("del/entity")
    public boolean delete(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return customerService.delete(customer);
    }

    @DeleteMapping("del/personal")
    public boolean delete(@RequestParam("id") Long id) {
        log.info("Try delete  customer: id={}", id);
        return customerService.deleteById(id);
    }


    @PutMapping("up/user")
    public CustomerMarked update(@Valid @RequestBody CustomerRequest customerRequest) {
        log.info("Try update customer: Name={}", customerRequest.getName());
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return checkEntity(customerService.update(customer),"update Customer");
    }

    @PutMapping("up/admin")
    public CustomerMarked updateByAdmin(@RequestParam("id") long id, @Valid @RequestBody CustomerRequest customerRequest) {
        log.info("Try update customer: id={}", id);
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return checkEntity(customerService.updateByAdmin(id, customer),"update Admin");
    }


    public CustomerMarked checkEntity(Customer customer,String where) {
        if (customer == null) throw new CustomerNotFoundException("Customer not found " + where);
        CustomerResponse customerResponse = customerDtoResponseMapper.convertToDto(customer);
        return new CustomerMarked("Work done", customerResponse);
    }
}
