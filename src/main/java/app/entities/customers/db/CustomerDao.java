package app.entities.customers.db;


import app.entities.Dao;
import app.entities.customers.service.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Service
public class CustomerDao implements Dao<Customer> {

    private final CustomerRepository cr;


    public Customer update(Customer customer) {
        customer.setId(getIdFromEntity(customer));
        if (customer.getId() != 0) {
            cr.save(customer);
            return cr.getOne(customer.getId());
        }
        return null;
    }


    public long getIdFromEntity(Customer customer) {
        Optional<Customer> customer1 = cr.findAll().stream().filter(c -> c.equals(customer)).findFirst();
        return customer1.map(Customer::getId).orElse(0L);
    }

    @Override
    public Customer save(Customer customer) {
        if (getIdFromEntity(customer) == 0)
            cr.save(customer);
        return customer;
    }

    public Customer saveByAdmin(long id, Customer customer) {
        if (getOne(id) != null) {
            customer.setId(id);
            cr.save(customer);
        }
        return customer;
    }


    @Override
    public boolean delete(Customer customer) {
        if (cr.findAll().contains(customer)) {
            cr.findAll().stream().filter(c -> c.equals(customer)).forEach(cr::delete);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        cr.deleteAll(entities);
    }

    @Override
    public void saveAll(List<Customer> entities) {
        cr.saveAll(entities);
    }

    @Override
    public List<Customer> findAll() {
        return cr.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        if (cr.existsById(id)) {
            cr.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Customer getOne(long id) {
        Optional<Customer> c = cr.findById(id);
        return c.orElse(null);
    }
}
