package app.entities.customers.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerMarked {
    String marked;
    CustomerResponse customerResponse;
}
