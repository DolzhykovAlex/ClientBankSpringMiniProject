package app.exeptions.customExeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNotFoundException extends CustomExceptionSuperClass{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
