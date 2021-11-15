package net.novogrodsky.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * This class converts Employee objects into entityModel<Employee> objects.
 */
@Component
class EmployeeModelAssembler implements
    RepresentationModelAssembler<Employee, EntityModel<Employee>> {

  /**
   * To convert an Employee object into a EntityModel<Employee> object.
   * @param employee
   * @return EntityModel<Employee>
   */
  @Override
  public EntityModel<Employee> toModel(Employee employee) {
    return EntityModel.of(employee,
        linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }
}
