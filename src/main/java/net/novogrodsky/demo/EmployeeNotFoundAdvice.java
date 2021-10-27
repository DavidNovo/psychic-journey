package net.novogrodsky.demo;

import net.novogrodsky.demo.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Creates custom 404 error message using the EmployeeNotFoundException class.
 */
@ControllerAdvice
class EmployeeNotFoundAdvice {

  // ResponseBody signals that the advice is rendered straight
  // into the response
  @ResponseBody
  @ExceptionHandler(EmployeeNotFoundException.class) //advice is only thrown if this type of exception is thrown
  @ResponseStatus(HttpStatus.NOT_FOUND) // what type of HTTP response to throw
  String employeeNotFoundHandler(EmployeeNotFoundException ex) {
    return ex.getMessage();
  }
}
