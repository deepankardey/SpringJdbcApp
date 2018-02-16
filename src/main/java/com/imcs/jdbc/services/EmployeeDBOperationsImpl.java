package com.imcs.jdbc.services;

import java.sql.SQLException;
import java.util.List;

import com.imcs.core.entity.Employee;
import com.imcs.core.exceptions.EmployeeNotFoundException;
import com.imcs.core.exceptions.InvalidSalaryException;
import com.imcs.jdbc.dao.EmployeeDAOimpl;
import com.imcs.jdbc.interfaces.EmployeeDAO;
import com.imcs.jdbc.interfaces.EmployeeDBOperations;
import com.imcs.jdbc.util.EmployeeUtil;

public class EmployeeDBOperationsImpl implements EmployeeDBOperations{
	
	EmployeeDAO dao = null;
	
	public EmployeeDBOperationsImpl(){
		dao = new EmployeeDAOimpl();
	}

	@Override
	public boolean addEmployee(Employee employee) throws InvalidSalaryException {
		if(employee.getSalary()<5000)
			throw new InvalidSalaryException("Invalid Salary Details ");
		try {
			return dao.addEmployee(employee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) throws EmployeeNotFoundException {
		try {
			return dao.deleteEmployee(id);
		} catch (SQLException e) {
			throw new EmployeeNotFoundException("Employee Not Found. ");
		}
	}

	@Override
	public boolean updateEmployeeInfo(Employee employee) throws EmployeeNotFoundException, InvalidSalaryException {
		if(employee.getSalary()<5000)
			throw new InvalidSalaryException("Invalid Salary Details ");
		try {
			return dao.updateEmployeeInfo(employee);
		} catch (SQLException e) {
			throw new EmployeeNotFoundException("Employee Not Found. ");
		}
	}

	@Override
	public Employee getEmployeeInfo(int id) {
		try {
			return dao.getEmployeeInfo(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public double getEmployeeHRA(int id) {
		try {
			return dao.getEmployeeSalary(id)*0.2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	@Override
	public double getEmployeeGrossSalary(int id) {
		try {
			Employee e = new Employee();
			e.setSalary(dao.getEmployeeSalary(id));
			return EmployeeUtil.calculateGrossSalary(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	@Override
	public List<Employee> getAllEmployeeInfo(int order) {
		try {
			return dao.getAllEmployeeInfo(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}