package com.db.repository;

import org.springframework.data.repository.CrudRepository;
import com.db.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}