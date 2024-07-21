package App.services;

import App.DAO.CustomerDao;
import App.entities.Account;
import App.entities.Customer;
import App.entities.CustomerLite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;

    public Customer getOneInformation(long id) {
        return customerDao.getOne(id);
    }


    public List<CustomerLite> findAll() {
        return customerDao.findAll().stream().map(this::convertToCustomerLite).toList();
    }

    public void create(Customer customer) {
        customerDao.insertInDb(customer);
    }

    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    public Customer update(Customer customer) {
        return customerDao.save(customer);
    }

    public Customer updateByAdmin(long id, Customer customer) {
        return customerDao.saveByAdmin(id, customer);
    }

    public boolean createAccount(Account account) {
        Customer r = customerDao.getOne(account.getCustomer().getId());
        if (r != null) {
            r.getAccounts().add(account);
            System.out.println(  r.getAccounts());
            customerDao.save(r);
            return true;
        }
        return false;
    }

    public boolean deleteAccount(Account account) {
        Customer r = customerDao.getOne(account.getCustomer().getId());
        if (r != null)
            if (r.getAccounts().contains(account)) {
                r.getAccounts().remove(account);
                customerDao.save(r);
                return true;
            
            }
        return false;
    }

    public CustomerLite convertToCustomerLite(Customer customer) {
        return new CustomerLite(customer);
    }

}
