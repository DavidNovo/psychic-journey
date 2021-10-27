package net.novogrodsky.demo;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Employee {

  // attributes of this domain object
  private @Id
  @GeneratedValue
  Long id;
  private String name;
  private String role;

  /**
   * Non-default constructor
   *
   * @author d.novogrodsky
   */
  Employee(String name, String role) {
    this.name = name;
    this.role = role;

  }

  public Employee() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.role);
  }

  @Override
  public boolean equals(Object obj) {
    // a self check
    if (this == obj) {
      return true;
    }

    // check if Object is not of class Employee
    if (!(obj instanceof Employee)) {
      return false;
    }

    // this is the deep compare
    Employee employee = (Employee) obj;
    return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
        && Objects.equals(this.role, employee.role);
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role
        + '\'' + '}';
  }
}
