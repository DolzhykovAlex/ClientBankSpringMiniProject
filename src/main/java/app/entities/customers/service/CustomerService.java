package app.entities.customers.service;

import app.entities.customers.db.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    Customer getOneInformation(long id);

    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Customer create(Customer customer);

    boolean delete(Customer customer);

    Customer update(Customer customer);

    Customer updateByAdmin(long id, Customer customer);


    boolean deleteById(long id);
}
