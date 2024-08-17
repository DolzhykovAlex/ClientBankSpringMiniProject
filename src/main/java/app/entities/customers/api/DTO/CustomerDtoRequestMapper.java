package app.entities.customers.api.DTO;


import app.entities.DtoMapperFacade;
import app.entities.customers.db.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerDtoRequestMapper extends DtoMapperFacade<Customer, CustomerRequest> {
    public CustomerDtoRequestMapper() {
        super(Customer.class, CustomerRequest.class);
    }


    @Override
    protected void decorateEntity(Customer entity, CustomerRequest dto) {



    }
}
