package app.entities.customers.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class CustomerMarked {
    String marked;
    CustomerLite customerLite;
}
