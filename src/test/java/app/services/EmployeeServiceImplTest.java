package app.services;


import app.entities.employee.db.Employee;
import app.entities.employee.service.EmployeeRepository;
import app.entities.employee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;
    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    public void saveTest() {
        Employee employee = new Employee();
        employee.setId(1L);
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Assertions.assertEquals(employee, employeeServiceImpl.save(employee));

    }


    @Test
    public void getIdFromEntityTest() {
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();
        employee2.setName("22");
        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(employee2, employee3));
        Optional<Employee> employee4 = employeeRepository.findAll().stream().filter(c -> c.equals(employee)).findFirst();
        Assertions.assertEquals(employee3, employee4.get());

    }


    @Test
    public void delete() {
        Employee employee = new Employee();
        Mockito.when((employeeRepository.findAll())).thenReturn(List.of(employee));
        Assertions.assertTrue(employeeServiceImpl.delete(employee));
    }





    @Test
    public void findAllTest() {
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(employee, employee2));
        Assertions.assertEquals(2, employeeServiceImpl.findAll().size());

    }


    @Test
    public void update() {
        Employee employee = new Employee();
        employee.setId(1L);
        Assertions.assertNull( employeeServiceImpl.update(employee));
    }
}