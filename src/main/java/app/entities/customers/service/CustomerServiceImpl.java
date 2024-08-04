package app.entities.customers.service;


import app.entities.customers.api.DTO.CustomerLite;
import app.entities.customers.db.Customer;
import app.entities.customers.db.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public Customer getOneInformation(long id) {
        return customerDao.getOne(id);
    }


    public List<CustomerLite> findAll() {
        return customerDao.findAll().stream().map(this::convertToCustomerLite).toList();
    }


    public void create(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        System.out.println((customerDao.delete(customer)));
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.update(customer);
    }


    @Override
      public Customer updateByAdmin(long id, Customer customer) {
          return customerDao.saveByAdmin(id, customer);
      }


    public CustomerLite convertToCustomerLite(Customer customer) {
        return new CustomerLite(customer);
    }


    public boolean deleteById(long id) {
        return customerDao.deleteById(id);
    }
}
