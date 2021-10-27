package net.novogrodsky.demo;

import java.util.List;
import net.novogrodsky.demo.exception.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller will take the data returned by each method and
 * write it to the response body.
 */
@RestController
public class EmployeeController {
  private final EmployeeRepository repository;

  /**
   * non-default constructor
   * @param repository
   */
  EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  // the CRUD functions


  /**
   * Find-all
   * @return a list of employees
   */
  @GetMapping("/employees")
  List<Employee> all() {
    return repository.findAll();
  }

  /**
   * Post new employee to database.
   * @param newEmployee
   * @return
   */
  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  /**
   * Find a single employee
   * @param id
   * @return a single employee
   */
  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  /**
   * Update an existing employee record.  If an existing Employee is not found,
   * add the newEmployee to the repository
   * @param newEmployee
   * @param id
   * @return
   */
  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

    return repository.findById(id)
        .map(employee -> {
          employee.setName(newEmployee.getName());
          employee.setRole(newEmployee.getRole());
          return repository.save(employee);
        })
        .orElseGet(() -> {
          newEmployee.setId(id);
          return repository.save(newEmployee);
        });
  }

  /**
   * Delete an Employee from the database.
   * @param id
   */
  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
