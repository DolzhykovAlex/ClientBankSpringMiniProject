package App.DAO;

import App.entities.Account;
import App.entities.Customer;
import App.enums.Currency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomerDao implements Dao<Customer> {

    List<Customer> customers = new ArrayList<>();

    {
        Customer customer1 = new Customer("Jim", "A@111.com", 21);
        Customer customer2 = new Customer("Jon", "B@111.com", 22);
        Customer customer3 = new Customer("Kit", "C@111.com", 23);
        Customer customer4 = new Customer("Jane", "D@111.com", 23);
        Customer customer5 = new Customer("Jan2", "E@111.com", 23);
        Account account = new Account(customer1, "USD");
        customer1.getAccounts().add(account);
        insertInDb(customer1);
        insertInDb(customer2);
        insertInDb(customer3);
        insertInDb(customer4);
        insertInDb(customer5);
    }

    public long generatedId() {
        if (customers.isEmpty()) return 1;
        return customers.stream().map(Customer::getId).max(Long::compare).get() + 1;
    }

    public long generatedIdHoles() {
        ArrayList<Long> list = new ArrayList<>(customers.stream().map(customer -> customer.getId())
                .toList().stream()
                .sorted().toList());
        int i;
        for ( i = 0; i < list.size(); i++) {
            if (list.get(i) - i >1) return i+1;
        }
        return i+1;
    }


    public void insertInDb(Customer customer) {
        if (!customers.contains(customer)) {
            long l1 = generatedIdHoles();
        //  System.out.println(l1+" need test");// need test
            customer.setId(l1);
            customers.add(customer);
        }
    }


    @Override
    public Customer save(Customer customer) {
        if (customers.contains(customer)) {
            Customer c = customers.stream().filter(k -> k.equals(customer)).findFirst().get();
            c.setAccounts(customer.getAccounts());
            c.setAge(customer.getAge());
            return c;
        }
        return null;
    }

    public Customer saveByAdmin(long id, Customer customer) {
       Customer c= customers.stream().filter(k->k.getId() == id).findFirst().orElse(null);
       if (c==null)return null;
       c.setAccounts(customer.getAccounts());
       c.setAge(customer.getAge());
       c.setName(customer.getName());
       c.setEmail(customer.getEmail());
       return c;
    }


    @Override
    public boolean delete(Customer customer) {
        if (customers.contains(customer)) {
            customers.remove(customer);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        customers.removeAll(entities);
    }

    @Override
    public void saveAll(List<Customer> entities) {
        customers.addAll(entities);
    }

    @Override
    public List<Customer> findAll() {
        System.out.println(customers);
        return customers;
    }

    @Override
    public boolean deleteById(long id) {
        Customer x = getOne(id);
        if (x == null)
            return false;
        customers.remove(x);
        return false;
    }

    @Override
    public Customer getOne(long id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }
}
