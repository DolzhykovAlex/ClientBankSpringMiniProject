package App.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class CustomerMarked {
    String marked;
    CustomerLite customerLite;
}
