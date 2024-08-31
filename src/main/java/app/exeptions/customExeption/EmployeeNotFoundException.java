package app.exeptions.customExeption;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeNotFoundException extends CustomExceptionSuperClass {
   public EmployeeNotFoundException(String message) {
      super(message);
   }
}
