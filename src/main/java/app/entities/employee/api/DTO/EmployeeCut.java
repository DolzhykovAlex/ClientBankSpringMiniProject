package app.entities.employee.api.DTO;

import app.entities.employee.db.Employee;
import lombok.Data;

@Data
public class EmployeeCut {
    private long id;
    private String name;
    private String address;

    public EmployeeCut(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }
}
