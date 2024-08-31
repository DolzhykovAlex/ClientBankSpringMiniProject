package app.exeptions;

import app.exeptions.customExeption.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {


    private static final Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> exHandler1(AccountNotFoundException ex) {
        log.warn("Account not found in {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("where exception", ex.getMessage());
        response.put("why", ex.getClass().getSimpleName());
        return response;
    }


    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> exHandler2(NotEnoughMoneyException ex) {
        log.warn("Not enough money error in {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("where exception", ex.getMessage());
        response.put("why", ex.getClass().getSimpleName());
        return response;
    }

    @ExceptionHandler(ParseStringToDoubleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> exHandler3(ParseStringToDoubleException ex) {
        log.warn("Parse error in {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("where exception", ex.getMessage());
        response.put("why", ex.getClass().getSimpleName());
        return response;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> exHandler4(EmployeeNotFoundException ex) {
        log.warn("Error Employee in {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("where exception", ex.getMessage());
        response.put("why", ex.getClass().getSimpleName());
        return response;
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> exHandler4(CustomerNotFoundException ex) {
        log.warn("Error Customer in {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("where exception", ex.getMessage());
        response.put("why", ex.getClass().getSimpleName());
        return response;
    }
}


