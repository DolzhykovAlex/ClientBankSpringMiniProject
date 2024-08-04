package app.entities.employee.service;

import app.entities.account.db.Account;
import app.entities.employee.api.DTO.EmployeeLite;
import app.entities.employee.db.Employee;
import app.entities.employee.db.EmployeeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public boolean delete(Employee obj) {
        return employeeDAO.delete(obj);
    }

    @Override
    public void deleteAll(List<Employee> entities) {
        employeeDAO.deleteAll(entities);

    }

    @Override
    public void saveAll(List<Employee> entities) {

    }

    @Override
    public List<EmployeeLite> findAll() {
        return employeeDAO.findAll().stream().map(EmployeeLite::new).toList();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Account getOne(long id) {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
      return   employeeDAO.updateEmployee(employee);
    }
}
