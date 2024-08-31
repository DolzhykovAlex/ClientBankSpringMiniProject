package app.entities.employee.service;


import app.entities.employee.db.Employee;
import app.exeptions.customExeption.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository em;

    @Override
    public Employee save(Employee employee) {
        if (getIdFromEntity(employee) != 0)
            throw new EmployeeNotFoundException("EmployeeService Method save:this Employee Exist, cant create the same  ");
        em.save(employee);
        return employee;
    }

    public long getIdFromEntity(Employee employee) {
        Optional<Employee> employee1 = em.findAll().stream().filter(c -> c.equals(employee)).findFirst();
        return employee1.map(Employee::getId).orElse(0L);
    }

    @Override
    public boolean delete(Employee employee) {
        if (em.findAll().contains(employee)) {
            em.delete(employee);
            return true;
        }
        throw new EmployeeNotFoundException("EmployeeService Method delete");
    }

    @Override
    public void deleteAll(List<Employee> entities) {
        em.deleteAll(entities);
    }

    @Override
    public void saveAll(List<Employee> entities) {
    }

    @Override
    public List<Employee> findAll() {
        return em.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }


    @Override
    public Employee update(Employee employee) {
        if (getIdFromEntity(employee) == 0) throw new EmployeeNotFoundException("EmployeeService Method update");
        return em.save(employee);
    }
}
