package app.entities.account.api.DTO;


import app.entities.DtoMapperFacade;
import app.entities.account.db.Account;
import app.entities.customers.api.DTO.CustomerCut;
import org.springframework.stereotype.Service;




@Service
public class AccountDtoResponseMapper extends DtoMapperFacade<Account, AccountResponse> {
    public AccountDtoResponseMapper() {
        super(Account.class, AccountResponse.class);
    }


    @Override
    protected void decorateDto(AccountResponse dto, Account entity) {
        dto.setCustomer(new CustomerCut(entity.getCustomer()));
    }
}
