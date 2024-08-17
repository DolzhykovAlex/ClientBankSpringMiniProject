package app.entities.employee.api.DTO;


import app.entities.DtoMapperFacade;
import app.entities.employee.db.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDtoRequestMapper extends DtoMapperFacade<Employee, EmployeeRequest> {
    public EmployeeDtoRequestMapper() {
        super(Employee.class, EmployeeRequest.class);
    }


    @Override
    protected void decorateEntity(Employee entity, EmployeeRequest dto) {



    }
}
