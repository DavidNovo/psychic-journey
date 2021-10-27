package net.novogrodsky.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create interface that extends JpaRepository
 * specifying the domain type of Employee.  This gives
 * us CRUD functionality
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
