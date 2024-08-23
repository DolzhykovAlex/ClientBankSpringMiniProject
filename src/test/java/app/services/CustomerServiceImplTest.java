package app.services;


import app.entities.customers.db.Customer;
import app.entities.customers.service.CustomerRepository;
import app.entities.customers.service.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

   

    @Test
    public void getOneInformation() {
        Customer customer = new Customer();
        customer.setId(1L);
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Assertions.assertNotNull(customerServiceImpl.getOneInformation(customer.getId()));
    }

    @Test
    public void findAll() {
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        Mockito.when(customerRepository.findAll()).thenReturn(List.of(customer, customer1));
        assertEquals(2, customerServiceImpl.findAll().size());
    }

    @Test
    public void findAll_PageableTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        Page<Customer> page = new PageImpl<>(List.of(customer, customer1));
        Mockito.when(customerRepository.findAll(pageable)).thenReturn(page);
        assertEquals(2, customerServiceImpl.findAll(pageable).getContent().size());
    }

    @Test
    public void createTest() {
        Customer customer = new Customer();
        customer.setPassword("1");
        Mockito.when(passwordEncoder.encode(customer.getPassword())).thenReturn("encode1");
        Mockito.when(customerRepository.findByNameAndEmail(customer.getName(), customer.getEmail())).thenReturn(customer);
        assertAll("Check create Customer =================================================",
                () -> assertEquals("encode1", customerServiceImpl.create(customer).getPassword()),
                () -> assertNull(customerServiceImpl.create(customer).getId()));
    }

    @Test
    public void getIdFromEntityTest() {
        Customer customer = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        customer2.setName("22");
        Mockito.when(customerRepository.findAll()).thenReturn(List.of(customer2, customer3));
        Optional<Customer> customer4 = customerRepository.findAll().stream().filter(c -> c.equals(customer)).findFirst();
        assertEquals(customer3, customer4.get());

    }

    @Test
    public void deleteTest() {
        Customer customer = new Customer();
        Mockito.when((customerRepository.findAll())).thenReturn(List.of(customer));
        Assertions.assertTrue(customerServiceImpl.delete(customer));
    }

    @Test
    public void updateTestNegative() {
        Customer customer = new Customer();
        customer.setId(1L);
        Assertions.assertNull(customerServiceImpl.update(customer));
    }


    @Test
    public void updateByAdminTest() {
        long id = 1;
        Customer customer = new Customer();
        customer.setId(id);
        customer.setPassword("encode1");
        Mockito.when(passwordEncoder.encode(customer.getPassword())).thenReturn("encode1");
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Assertions.assertEquals(customer, customerServiceImpl.updateByAdmin(id, customer));
    }

    @Test
    public void deleteById() {
        long id = 1L;
        Customer customer = new Customer();
        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        Assertions.assertTrue(customerServiceImpl.deleteById(id));
    }
}
