package app.entities.employee.api.DTO;

import app.entities.customers.api.DTO.CustomerCut;
import app.entities.customers.db.Customer;
import app.entities.employee.db.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeLite {
    private long id;
    private String name;
    private String address;
    private List<CustomerCut> workers;


    public EmployeeLite(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address=employee.getAddress();
        this.workers = convertA(employee.getWorkers());
    }

    public List<CustomerCut> convertA(List<Customer> customers){
        if((customers == null)||(customers.isEmpty())) return new ArrayList<>();
        return customers.stream().map(CustomerCut::new).toList();
    }




}
