package app.entities.customers.service;


import app.entities.customers.db.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository cr;

    @Override
    public Customer getOneInformation(long id) {
        Optional<Customer> c = cr.findById(id);
        return c.orElse(null);
    }


    public List<Customer> findAll() {
        return cr.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return cr.findAll(pageable);
    }



    public Customer create(Customer customer) {
        if (getIdFromEntity(customer) == 0) {
            cr.save(customer);
            return cr.findByNameAndEmail(customer.getName(), customer.getEmail());
        }
        return null;
    }

    public long getIdFromEntity(Customer customer) {
        Optional<Customer> customer1 = cr.findAll().stream().filter(c -> c.equals(customer)).findFirst();
        return customer1.map(Customer::getId).orElse(0L);
    }

    @Override
    public boolean delete(Customer customer) {
        if (cr.findAll().contains(customer)) {
            cr.delete(customer);
            return true;
        }
        return false;
    }

    @Override
    public Customer update(Customer customer) {
        customer.setId(getIdFromEntity(customer));
        System.out.println(customer.getId()+"4234234234234234234234234234------------------------");
        if (customer.getId() != 0) {
            cr.save(customer);
            System.out.println("customer: " + customer+"---------------------");

            return cr.getFirstById(customer.getId());
        }
        return null;
    }


    @Override
    public Customer updateByAdmin(long id, Customer customer) {
        customer.setId(id);
        return cr.save(customer);
    }


    public boolean deleteById(long id) {
        if (cr.findById(id).isPresent()) {
            cr.deleteById(id);
            return true;
        }
        return false;
    }
}
