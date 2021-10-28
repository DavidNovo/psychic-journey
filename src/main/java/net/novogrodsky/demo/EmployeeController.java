package net.novogrodsky.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import net.novogrodsky.demo.exception.EmployeeNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller will take the data returned by each method and write it to the response body.
 */
@RestController
public class EmployeeController {

  private final EmployeeRepository repository;

  /**
   * non-default constructor
   */
  EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  // the CRUD functions


  /**
   * Find-all employees.  This is also called an aggregate root.
   *
   * @return a list of type Employee, using CollectionModel<>
   */
  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all() {
    // get a list of employees
    // create a http link for the aggregate root
    // original version was repository.findAll()
    List<EntityModel<Employee>> employees =
        repository.findAll().stream().map(employee -> EntityModel.of(employee,
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
            .collect(Collectors.toList());

    // CollectionModel<> is also from Sprng HATEOAS; encapsulating a
    //collection of resources instead of a single resource. Can also include links.
    return CollectionModel.of(employees,
        linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  /**
   * Post new employee to database.
   */
  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {

    return repository.save(newEmployee);
  }

  /**
   * Find a single employee
   *
   * @return a single employee, was Employee type.  Now is EntityModel<Employee>.
   */
  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {
    // EntityModel<T> is a generic container from Spring HATEOAS that
    // includes the data of Employee and a collection of links

    // first get the desired employee
    Employee employee = repository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    // the return builds the Hypertext links associated with this
    // resource Employee
    // linkTo().withSelfRef() tells Spr HATEOAS build a link to the EmployeeControler's
    // one() method and flag it as a self link
    // the linkTo().withRel("employees") tells Spring HATEOAS to build a link to the
    // aggregate root, all() of the EmployeeController class and call it 'employees'
    return EntityModel.of(employee,
        linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }

  /**
   * Update an existing employee record.  If an existing Employee is not found, add the newEmployee
   * to the repository
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
   */
  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
