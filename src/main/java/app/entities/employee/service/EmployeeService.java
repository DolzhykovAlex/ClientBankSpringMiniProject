package app.entities.employee.service;


import app.entities.employee.db.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    Employee save(Employee employee);

    Employee update(Employee employee);

    boolean delete(Employee obj);

    void deleteAll(List<Employee> entities);

    void saveAll(List<Employee> entities);

    List<Employee> findAll();

    boolean deleteById(long id);


}
