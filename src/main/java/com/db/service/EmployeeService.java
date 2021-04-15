package com.db.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.exception.EmployeeNotFoundException;
import com.db.model.Employee;
import com.db.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	public List<Employee> getAllEmployee() {
		LOGGER.info("Employee find ALL");

		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employee -> employees.add(employee));

		return employees;
	}

	public Employee getEmployeeById(int id) {
		LOGGER.info("Employee find: id={}", id);

		return employeeRepository.findById(id).orElseThrow(() -> {
			LOGGER.error("Error finding employee. Not found employee for id: {}", id);

			return new EmployeeNotFoundException("Employee not found finding by id", this.getClass());
		});
	}

	public void saveOrUpdate(Employee employee) {
		LOGGER.info("Employee add: {}", employee);

		employeeRepository.save(employee);
	}

	public void delete(int id) {
		LOGGER.info("Employee delete: id={}", id);

		employeeRepository.deleteById(id);
	}

}