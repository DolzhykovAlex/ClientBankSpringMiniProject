package app.controllers;


import app.entities.customers.api.DTO.*;
import app.entities.customers.db.Customer;
import app.entities.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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
        Pageable pageable = PageRequest.of(page, size);
        return   customerService.findAll(pageable).stream()
                .map(customerDtoResponseMapper::convertToDto)
                .toList();
    }

    @GetMapping("all")
    public List<CustomerResponse> findAll() {
        return customerService.findAll().stream()
                .map(customerDtoResponseMapper::convertToDto)
                .collect(Collectors.toList());
    }


    @GetMapping("one")
    public CustomerMarked getOneInformation(@RequestParam("id") Long id) {
        return checkEntity(customerService.getOneInformation(id));
    }

    @PostMapping("add")
    public CustomerMarked create(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return checkEntity(customerService.create(customer));
    }

    @DeleteMapping("del/entity")
    public boolean delete(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return customerService.delete(customer);
    }

    @DeleteMapping("del/personal")
    public boolean delete(@RequestParam("id") Long id) {
        return customerService.deleteById(id);
    }


    @PutMapping("up/user")
    public CustomerMarked update(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return checkEntity(customerService.update(customer));
    }

    @PutMapping("up/admin")
    public CustomerMarked updateByAdmin(@RequestParam("id") long id, @Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = requestDtoMapper.convertToEntity(customerRequest);
        return checkEntity(customerService.updateByAdmin(id, customer));
    }


    public CustomerMarked checkEntity(Customer customer) {
        if (customer == null) {
            return new CustomerMarked("Customer not found or already exist", new CustomerResponse());
        }
        CustomerResponse customerResponse = customerDtoResponseMapper.convertToDto(customer);
        return new CustomerMarked("Work done", customerResponse);
    }
}
