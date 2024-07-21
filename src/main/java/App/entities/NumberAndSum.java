package App.entities;

import lombok.Data;

import java.util.List;

@Data
public class NumberAndSum {
    private List<String> numbers;
    private String sum;

}
