package net.novogrodsky.demo.exception;

/**
 * Represent an exception that occurs during runtime.
 */
public class EmployeeNotFoundException extends RuntimeException{
  public EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }

}
