package com.imcs.jdbc.util;

import com.imcs.core.entity.Employee;

public class EmployeeUtil {

	public static Employee getEquivalentEmployee(Employee employee) {
		return new Employee(employee);
	}
	
	/**
	 * Compare two employees salary and returns the highest salary employee object
	 * @param employee1
	 * @param employee2
	 * @return least salary employee 
	 */
	public static Employee compareEmployeesSalary(Employee employee1,Employee employee2) {
		return employee1.getSalary()>employee2.getSalary() ? employee1 : employee2;
	}
	
	/**
	 * Compare two employees age and returns the older employee object
	 * @param employee1
	 * @param employee2
	 * @return older employee 
	 */
	public static Employee compareEmployeesAge(Employee employee1,Employee employee2) {
		return employee1.getAge()>employee2.getAge() ? employee1 : employee2;
	}
	
	/**
	 * A method which can update by increase lowest paid employee salary by 10%
	 * @param employees
	 */
	public static void updateLeastSalaryEmployee(Employee...employees) {
		int index = 0;
		double minimumSalary = employees[0].getSalary();
		int employeeIndex = 0;
		for(Employee employee : employees) {
			if(employee.getSalary()<minimumSalary){
				minimumSalary = employee.getSalary();
				employeeIndex = index;  
            }
			++index;
		}
		employees[employeeIndex].setSalary(((employees[employeeIndex].getSalary()*10/100)+(employees[employeeIndex].getSalary())));
		System.out.println("Employee with id "+employees[employeeIndex].getId()+" salary got increased to "+employees[employeeIndex].getSalary());
	}
	
	public static void updateEmployeeSalary(Employee employee) {
		if(employee.getSalary()<10000.0 && employee.getAge()>35) {
			employee.setSalary(((employee.getSalary()*15/100)+(employee.getSalary())));
		}else if(employee.getSalary()<15000.0 && employee.getAge()>45) {
			employee.setSalary(((employee.getSalary()*20/100)+(employee.getSalary())));
		}else if(employee.getSalary()<20000.0 && employee.getAge()>55) {
			employee.setSalary(((employee.getSalary()*25/100)+(employee.getSalary())));
		}
		System.out.println("Salary updated to "+employee.getSalary());
	}
	
	public static double calculateGrossSalary(Employee employee) {
		double da;
		double hra;
		
		if(employee.getSalary()<10000.0) {
			da = employee.getSalary()*(0.08);
			hra = employee.getSalary()*(0.15);
		}else if(employee.getSalary()<20000.0) {
			da = employee.getSalary()*(0.1);
			hra = employee.getSalary()*(0.2);
		}else if(employee.getSalary()<30000.0 && employee.getAge()>=40) {
			da = employee.getSalary()*(0.15);
			hra = employee.getSalary()*(0.27);
		}else if(employee.getSalary()<30000.0 && employee.getAge()<40) {
			da = employee.getSalary()*(0.13);
			hra = employee.getSalary()*(0.25);
		}else {
			da = employee.getSalary()*(0.17);
			hra = employee.getSalary()*(0.30);
		}
		
		return employee.getSalary()+da+hra;
	}
	
	
}
