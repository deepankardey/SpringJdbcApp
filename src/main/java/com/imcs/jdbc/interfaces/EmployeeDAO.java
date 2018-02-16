package com.imcs.jdbc.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.imcs.core.entity.Employee;

public interface EmployeeDAO {
	public boolean addEmployee(Employee employee)throws SQLException;
	public boolean deleteEmployee(int id)throws SQLException;
	public boolean updateEmployeeInfo(Employee employee)throws SQLException;
	public Employee getEmployeeInfo(int id)throws SQLException;
	public int getEmployeeSalary(int id)throws SQLException;
	public List<Employee> getAllEmployeeInfo(int order)throws SQLException;
}
