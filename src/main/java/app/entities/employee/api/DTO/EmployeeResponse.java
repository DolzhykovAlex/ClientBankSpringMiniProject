package app.entities.employee.api.DTO;


import app.entities.customers.api.DTO.CustomerCut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class EmployeeResponse {
    private long id;
    private String name;
    private String address;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private List<CustomerCut> workers;
}
