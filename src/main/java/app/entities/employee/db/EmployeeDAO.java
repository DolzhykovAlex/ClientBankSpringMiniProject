package app.entities.employee.db;

import app.entities.AbstractClass;
import app.entities.Dao;
import app.entities.employee.service.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Service
public class EmployeeDAO implements Dao<Employee> {

    private final EmployeeRepository em;

    @Override
    public Employee save(Employee employee) {
        if (getIdFromEntity(employee) == 0) {
            em.save(employee);
            return employee;
        }
        return null;
    }

    @Override
    public boolean delete(Employee obj) {
        if (em.findAll().contains(obj)) {
            em.delete(obj);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll(List<Employee> entities) {

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
    public Employee getOne(long id) {
        return null;
    }

    public long getIdFromEntity(Employee employee) {
        Optional<Employee> employee1 = em.findAll().stream().filter(c -> c.equals(employee)).findFirst();
        return employee1.map(AbstractClass::getId).orElse(0L);
    }

    public Employee updateEmployee(Employee employee) {
        if (getIdFromEntity(employee) != 0)
            return em.save(employee);
        return null;
    }
}
