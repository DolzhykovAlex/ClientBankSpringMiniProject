package app.controllers;

import app.entities.employee.api.DTO.EmployeeDtoRequestMapper;
import app.entities.employee.api.DTO.EmployeeDtoResponseMapper;
import app.entities.employee.api.DTO.EmployeeRequest;
import app.entities.employee.api.DTO.EmployeeResponse;
import app.entities.employee.db.Employee;
import app.entities.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureDataJpa
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDtoRequestMapper requestDtoMapper;

    @MockBean
    private EmployeeDtoResponseMapper responseDtoMapper;


    @Test
    public void createTest() throws Exception {

        EmployeeRequest employeeRequest = new EmployeeRequest();
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Test");
        employee.setAddress("Kyiv");

        when(requestDtoMapper.convertToEntity(employeeRequest)).thenReturn(employee);

        mockMvc.perform(post("/emp/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeRequest)))
                .andExpect(status().isOk());
        verify(employeeService).save(employee);
    }

    @Test
    public void getAllTest() throws Exception {

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        EmployeeResponse response1 = new EmployeeResponse();
        EmployeeResponse response2 = new EmployeeResponse();
        List<Employee> employees = List.of(employee1, employee2);

        when(employeeService.findAll()).thenReturn(employees);
        when(responseDtoMapper.convertToDto(employee1)).thenReturn(response1);
        when(responseDtoMapper.convertToDto(employee2)).thenReturn(response2);

        mockMvc.perform(get("/emp/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void deleteTestPositive() throws Exception {

        EmployeeRequest employeeRequest = new EmployeeRequest();
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Test");
        employee.setAddress("Kyiv");

        when(requestDtoMapper.convertToEntity(employeeRequest)).thenReturn(employee);
        when(employeeService.delete(employee)).thenReturn(true);

        mockMvc.perform(delete("/emp/del")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(employeeService).delete(employee);
    }


    @Test
    public void updateTest() throws Exception {

        EmployeeRequest employeeRequest = new EmployeeRequest();
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Test");
        employee.setAddress("Kyiv");
        when(requestDtoMapper.convertToEntity(employeeRequest)).thenReturn(employee);
        mockMvc.perform(put("/emp/up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeRequest)))
                .andExpect(status().isOk());
        verify(employeeService).update(employee);
    }


}
