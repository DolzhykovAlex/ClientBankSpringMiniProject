package app.entities.account.api.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
public class NumberAndSum {
    private List<String> numbers;
    @Min(0)
    private String sum;
}
