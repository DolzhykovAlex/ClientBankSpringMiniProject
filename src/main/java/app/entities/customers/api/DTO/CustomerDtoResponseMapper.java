package app.entities.customers.api.DTO;


import app.entities.DtoMapperFacade;
import app.entities.customers.db.Customer;
import app.entities.employee.api.DTO.EmployeeCut;
import app.entities.employee.db.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerDtoResponseMapper extends DtoMapperFacade<Customer, CustomerResponse> {
    public CustomerDtoResponseMapper() {
        super(Customer.class, CustomerResponse.class);
    }


    @Override
    protected void decorateDto(CustomerResponse dto, Customer entity) {
        if (entity.getWorks() != null) {
            List<EmployeeCut> list = convertE(entity.getWorks());
            dto.setWorks(list);
        }else dto.setWorks(new ArrayList<>());
    }

    public List<EmployeeCut> convertE(List<Employee> employees){
        if((employees == null)||(employees.isEmpty())) return new ArrayList<>();
        return employees.stream().map(EmployeeCut::new).toList();
    }
}
