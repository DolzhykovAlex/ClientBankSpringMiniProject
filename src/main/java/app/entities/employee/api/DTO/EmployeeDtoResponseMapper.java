package app.entities.employee.api.DTO;


import app.entities.DtoMapperFacade;
import app.entities.customers.api.DTO.CustomerCut;
import app.entities.customers.db.Customer;
import app.entities.employee.db.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class EmployeeDtoResponseMapper extends DtoMapperFacade<Employee, EmployeeResponse> {
    public EmployeeDtoResponseMapper() {
        super(Employee.class, EmployeeResponse.class);

    }


    @Override
    protected void decorateDto(EmployeeResponse dto, Employee entity) {
        if (entity.getWorkers() != null) {
            List<CustomerCut> list = convertA(entity.getWorkers());
            dto.setWorkers(list);
        }else dto.setWorkers(new ArrayList<>());
    }

    public List<CustomerCut> convertA (List < Customer > customers) {
        if ((customers == null) || (customers.isEmpty())) return new ArrayList<>();
        return customers.stream().map(CustomerCut::new).toList();
    }

}