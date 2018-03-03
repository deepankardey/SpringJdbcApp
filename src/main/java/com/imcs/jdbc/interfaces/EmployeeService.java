package com.imcs.jdbc.interfaces;

import java.util.List;

import com.imcs.core.entity.Employee;
import com.imcs.core.exceptions.EmployeeNotFoundException;
import com.imcs.core.exceptions.InvalidSalaryException;

public interface EmployeeService {
	public boolean addEmployee(Employee employee)throws InvalidSalaryException;
	public boolean deleteEmployee(int id)throws EmployeeNotFoundException;
	public boolean updateEmployeeInfo(Employee employee)throws EmployeeNotFoundException,InvalidSalaryException;
	public Employee getEmployeeInfo(int id);
	public double getEmployeeHRA(int id);
	public double getEmployeeGrossSalary(int id);
	public List<Employee> getAllEmployeeInfo(int order);
}
