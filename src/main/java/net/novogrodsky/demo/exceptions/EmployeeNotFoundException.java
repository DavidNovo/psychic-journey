package net.novogrodsky.demo.exceptions;

/**
 * Represent an exception that occurs during runtime.
 */
public class EmployeeNotFoundException extends RuntimeException{
  public EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }

}
