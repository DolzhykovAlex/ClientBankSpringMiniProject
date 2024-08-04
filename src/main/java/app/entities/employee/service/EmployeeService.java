package app.entities.employee.service;

import app.entities.account.db.Account;
import app.entities.employee.api.DTO.EmployeeLite;
import app.entities.employee.db.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    public Employee save(Employee employee);

    public Employee update(Employee employee);

    public boolean delete(Employee obj);

    public void deleteAll(List<Employee> entities);

    public void saveAll(List<Employee> entities);

    public List<EmployeeLite> findAll();

    public boolean deleteById(long id);

    public Account getOne(long id);

}
