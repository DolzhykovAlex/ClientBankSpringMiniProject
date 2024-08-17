package app.entities.customers.service;

import app.entities.customers.db.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    Customer getFirstById(long id);

     void deleteById(long id);

    Customer findByNameAndEmail(String name, String email);





}

