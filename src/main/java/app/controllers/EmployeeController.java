package app.controllers;

import app.entities.employee.api.DTO.EmployeeDtoRequestMapper;
import app.entities.employee.api.DTO.EmployeeDtoResponseMapper;
import app.entities.employee.api.DTO.EmployeeRequest;
import app.entities.employee.api.DTO.EmployeeResponse;
import app.entities.employee.db.Employee;
import app.entities.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("emp")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeDtoResponseMapper responseDtoMapper;
    private final EmployeeDtoRequestMapper requestDtoMapper;

    @PostMapping("add")
    public void create(@Valid @RequestBody EmployeeRequest requestDto) {

        Employee employee = requestDtoMapper.convertToEntity(requestDto);
        employeeService.save(employee);
        log.info("Employee created successfully: {}", requestDto);
    }

    @GetMapping("all")
    public List<EmployeeResponse> getAllEmployees() {
        log.info("Employee Get ALL");
        return employeeService.findAll().stream()
                .map(responseDtoMapper::convertToDto)
                .collect(Collectors.toList());

    }


    @DeleteMapping("del")
    public boolean deleteEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
                Employee employee = requestDtoMapper.convertToEntity(employeeRequest);
        log.info("Employee delete {} ", employee.getName());
        return employeeService.delete(employee);
    }

    @PutMapping("up")
    public void updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = requestDtoMapper.convertToEntity(employeeRequest);
        log.info("Employee update {} ", employee.getName());
        employeeService.update(employee);
    }


}
