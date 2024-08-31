package app.exeptions.customExeption;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountNotFoundException extends CustomExceptionSuperClass {
   public AccountNotFoundException(String message) {
      super(message);
   }
}
