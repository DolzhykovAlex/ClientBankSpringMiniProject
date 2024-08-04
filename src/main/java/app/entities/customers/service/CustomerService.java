package app.entities.customers.service;

import app.entities.customers.api.DTO.CustomerLite;
import app.entities.customers.db.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    public Customer getOneInformation(long id);

    public List<CustomerLite> findAll();

    public void create(Customer customer);

    public void delete(Customer customer);

    public Customer update(Customer customer);

    public Customer updateByAdmin(long id, Customer customer);


    public boolean deleteById(long id);
}
