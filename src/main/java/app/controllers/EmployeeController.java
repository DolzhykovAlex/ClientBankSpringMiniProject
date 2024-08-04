package app.controllers;

import app.entities.employee.api.DTO.EmployeeLite;
import app.entities.employee.db.Employee;
import app.entities.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emp")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    @GetMapping("all")
    public List<EmployeeLite> getAllEmployees() {
        return employeeService.findAll();
    }

    @PostMapping("add")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @DeleteMapping("del")
    public boolean deleteEmployee(@RequestBody Employee employee) {
        return employeeService.delete(employee);
    }

    @PutMapping("up")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
    }


}
