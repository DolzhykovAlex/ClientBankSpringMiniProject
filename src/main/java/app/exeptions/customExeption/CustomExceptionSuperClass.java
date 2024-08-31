package app.exeptions.customExeption;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomExceptionSuperClass extends RuntimeException {
    public CustomExceptionSuperClass(String message) {
        super(message);
    }
}
