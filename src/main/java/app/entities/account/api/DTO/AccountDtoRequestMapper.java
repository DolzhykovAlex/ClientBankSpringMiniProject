package app.entities.account.api.DTO;


import app.entities.DtoMapperFacade;
import app.entities.account.db.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountDtoRequestMapper extends DtoMapperFacade<Account, AccountRequest> {
    public AccountDtoRequestMapper() {
        super(Account.class, AccountRequest.class);
    }


    @Override
    protected void decorateEntity(Account entity, AccountRequest dto) {
    }
}
