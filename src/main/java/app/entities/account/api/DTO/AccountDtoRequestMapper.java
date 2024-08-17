package app.entities.account.api.DTO;


import app.entities.DtoMapperFacade;
import app.entities.customers.api.DTO.CustomerRequest;
import app.entities.customers.db.Customer;
import org.springframework.stereotype.Service;

@Service
public class AccountDtoRequestMapper extends DtoMapperFacade<Customer, CustomerRequest> {
    public AccountDtoRequestMapper() {
        super(Customer.class, CustomerRequest.class);
    }


    @Override
    protected void decorateEntity(Customer entity, CustomerRequest dto) {
    }
}
