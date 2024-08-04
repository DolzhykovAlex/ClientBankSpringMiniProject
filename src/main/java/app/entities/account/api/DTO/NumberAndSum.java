package app.entities.account.api.DTO;

import lombok.Data;

import java.util.List;

@Data
public class NumberAndSum {
    private List<String> numbers;
    private String sum;

}
