package app.controllers;

import app.entities.customers.api.DTO.CustomerDtoRequestMapper;
import app.entities.customers.api.DTO.CustomerDtoResponseMapper;
import app.entities.customers.api.DTO.CustomerRequest;
import app.entities.customers.api.DTO.CustomerResponse;
import app.entities.customers.db.Customer;
import app.entities.customers.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureDataJpa
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;


    @MockBean
    private CustomerDtoRequestMapper requestDtoMapper;

    @MockBean
    private CustomerDtoResponseMapper customerDtoResponseMapper;


    @Test
    public void getItems2Test() throws Exception {

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        List<Customer> customers = Arrays.asList(customer1, customer2);
        Page<Customer> customerPage = new PageImpl<>(customers);
        Pageable pageable = PageRequest.of(0, 3);
        when(customerService.findAll(pageable)).thenReturn(customerPage);

        CustomerResponse response1 = new CustomerResponse(); // Set response1 fields if needed
        CustomerResponse response2 = new CustomerResponse(); // Set response2 fields if needed

        when(customerDtoResponseMapper.convertToDto(customer1)).thenReturn(response1);
        when(customerDtoResponseMapper.convertToDto(customer2)).thenReturn(response2);

        mockMvc.perform(get("/app/customers")
                        .param("page", "0")
                        .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void findAllTest() throws Exception {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customers = Arrays.asList(customer1, customer2);
        CustomerResponse response1 = new CustomerResponse();
        CustomerResponse response2 = new CustomerResponse();

        when(customerDtoResponseMapper.convertToDto(customer1)).thenReturn(response1);
        when(customerDtoResponseMapper.convertToDto(customer2)).thenReturn(response2);
        when(customerService.findAll()).thenReturn(customers);
        String responseList = objectMapper.writeValueAsString(List.of(response1, response2));

        mockMvc.perform(get("/app/customers/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(content().json(responseList))
                .andExpect(jsonPath("$.length()").value(2));
    }


    @Test
    public void getOneInformationTestPositive() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer();
        CustomerResponse customerResponse = new CustomerResponse();
        when(customerService.getOneInformation(customerId)).thenReturn(customer);
        when(customerDtoResponseMapper.convertToDto(customer)).thenReturn(customerResponse);
        mockMvc.perform(get("/app/customers/one")
                        .param("id", customerId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marked").value("Work done"))
                .andExpect(jsonPath("$.customerResponse").exists());
    }


    @Test
    public void getOneInformationTestNegative() throws Exception {
        Long customerId = 1L;
        when(customerService.getOneInformation(customerId)).thenReturn(null);
        mockMvc.perform(get("/app/customers/one")
                        .param("id", customerId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marked").value("Customer not found or already exist"))
                .andExpect(jsonPath("$.customerResponse").exists());
    }

    @Test
    public void createTestPositive() throws Exception {
        Customer customer = new Customer();
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setAge(30);
        customerRequest.setPhone("+380501234567");
        customerRequest.setName("BBB");
        customerRequest.setEmail("cctomer@email.com");
        customerRequest.setId(1L);
        CustomerResponse customerResponse = new CustomerResponse();

        when(requestDtoMapper.convertToEntity(customerRequest)).thenReturn(customer);
        when(customerService.create(customer)).thenReturn(customer);
        when(customerDtoResponseMapper.convertToDto(customer)).thenReturn(customerResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String customerRequestJson = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/app/customers/add")
                        .contentType("application/json")
                        .content(customerRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marked").value("Work done"))
                .andExpect(jsonPath("$.customerResponse").exists());
    }

    @Test
    public void createTestNegative() throws Exception {
        Customer customer = new Customer();

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setAge(30);
        customerRequest.setPhone("+380501234567");
        customerRequest.setName("BBB");
        customerRequest.setEmail("cctomer@email.com");
        customerRequest.setId(1L);
        when(requestDtoMapper.convertToEntity(customerRequest)).thenReturn(customer);
        when(customerService.create(customer)).thenReturn(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String customerRequestJson = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/app/customers/add")
                        .contentType("application/json")
                        .content(customerRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marked").value("Customer not found or already exist"))
                .andExpect(jsonPath("$.customerResponse").exists());
    }

    @Test
    public void deleteEntityTest() throws Exception {

        Customer customer = new Customer();
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setAge(30);
        customerRequest.setPhone("+380501234567");
        customerRequest.setName("BBB");
        customerRequest.setEmail("cctomer@email.com");
        customerRequest.setId(1L);
        when(requestDtoMapper.convertToEntity(customerRequest)).thenReturn(customer);
        when(customerService.delete(customer)).thenReturn(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String customerRequestJson = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(delete("/app/customers/del/entity")
                        .contentType("application/json")
                        .content(customerRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void deleteTest() throws Exception {
                Long customerId = 1L;
        when(customerService.deleteById(customerId)).thenReturn(true);
        mockMvc.perform(delete("/app/customers/del/personal")
                        .param("id", customerId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }


    @Test
    public void updateUserTest() throws Exception {

        Customer customer = new Customer();
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setAge(30);
        customerRequest.setPhone("+380501234567");
        customerRequest.setName("BBB");
        customerRequest.setEmail("cctomer@email.com");
        customerRequest.setId(1L);
        CustomerResponse customerResponse = new CustomerResponse();

        when(requestDtoMapper.convertToEntity(customerRequest)).thenReturn(customer);
        when(customerService.update(customer)).thenReturn(customer);
        when(customerDtoResponseMapper.convertToDto(customer)).thenReturn(customerResponse);

               mockMvc.perform(put("/app/customers/up/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marked").value("Work done"))
                .andExpect(jsonPath("$.customerResponse").exists());
    }

    @Test
    public void testUpdateByAdmin_CustomerUpdatedSuccessfully() throws Exception {

        long customerId = 1L;

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setAge(30);
        customerRequest.setPhone("+380501234567");
        customerRequest.setName("BBB");
        customerRequest.setEmail("cctomer@email.com");
        customerRequest.setId(1L);

        Customer customer = new Customer();
        CustomerResponse customerResponse = new CustomerResponse();

        when(requestDtoMapper.convertToEntity(customerRequest)).thenReturn(customer);
        when(customerService.updateByAdmin(customerId, customer)).thenReturn(customer);
        when(customerDtoResponseMapper.convertToDto(customer)).thenReturn(customerResponse);

        mockMvc.perform(put("/app/customers/up/admin")
                        .param("id", String.valueOf(customerId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marked").value("Work done"))
                .andExpect(jsonPath("$.customerResponse").exists());
    }
//
//
//    public CustomerMarked checkEntity(Customer customer) {
//        if (customer == null) {
//            return new CustomerMarked("Customer not found or already exist", new CustomerResponse());
//        }
//        System.out.println(customer + " customer" + customer.getId() + "customer id");
//        CustomerResponse customerResponse = customerDtoResponseMapper.convertToDto(customer);
//        return new CustomerMarked("Work done", customerResponse);
//    }

}