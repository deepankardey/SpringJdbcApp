package com.imcs.jdbc.main;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.imcs.core.entity.Employee;
import com.imcs.core.exceptions.EmployeeNotFoundException;
import com.imcs.core.exceptions.InvalidSalaryException;
import com.imcs.jdbc.context.MyApplicationContext;
import com.imcs.jdbc.services.EmployeeServiceImpl;

@Component
public class EmployeeDBOperationsApp {
	@Autowired
	EmployeeServiceImpl impl;
	@Autowired
	DateFormat sdf;
	
	static ApplicationContext context =  null;
	
	public static void main(String args[])throws Exception {
		context =  MyApplicationContext.getInstance();
		System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
		context.getBean(EmployeeDBOperationsApp.class).execute();
	}
	
	public void execute() {
		try(Scanner sc = new Scanner(System.in);) {
			while (true) {
				System.out.println("=========================================================================");
				System.out.println("Select one option from the menu");
				System.out.println("1. Add an Employee");
				System.out.println("2. Delete an Employee");
				System.out.println("3. Update existing Employee data with the given new information.");
				System.out.println("4. Display employee information for the given employee id.");
				System.out.println("5. Display all employees information.");
				System.out.println("6. Display selected employee hra");
				System.out.println("7. Display selected employee gross salary");
				System.out.println("8. Sort All Employees By Salary");
				System.out.println("9. Sort All Employees By Name And Salary");
				System.out.println("10. Sort All Employees By Id");
				System.out.println("11. Exit");
				System.out.println("=========================================================================");
				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					addEmployeeOptions(sc);
					break;
				case 2:
					deleteEmployeeOption(sc);
					break;
				case 3:
					updateEmployeeOptions(sc);
					break;
				case 4:
					displayEmployeeOption(sc);
					break;
				case 5:
					displayAllEmployeeOption(sc);
					break;
				case 6:
					displayHRAOption(sc);
					break;
				case 7:
					displayGrossSalOption(sc);
					break;
				case 8:
					sortBySalary();
					break;
				case 9:
					sortByNameAndSalary();
					break;
				case 10:
					sortByID();
					break;
				case 11:
					System.exit(0);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	private void addEmployeeOptions(Scanner sc)throws Exception {
		System.out.println("Enter Employee ID : ");
		int id = sc.nextInt();
		System.out.println("Enter Employee Name : ");
		String name=sc.next(); 
		System.out.println("Enter Employee Salary : ");
		int salary = sc.nextInt();
		System.out.println("Enter Employee Age : ");
		int age = sc.nextInt();
		Employee emp = context.getBean(Employee.class);
		emp.setId(id);
		emp.setName(name);
		emp.setSalary(salary);
		emp.setAge(age);
		try {
			if(impl.addEmployee(emp));
				System.out.println("Employee with Employee id : "+id+" added to the list");
		} catch (InvalidSalaryException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void deleteEmployeeOption(Scanner sc)throws Exception {
		System.out.println("Enter Employee ID : ");
		int id = sc.nextInt();
		try {
			if(impl.deleteEmployee(id))
				System.out.println("Employee with id "+id+" got deleted");
		} catch (EmployeeNotFoundException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void updateEmployeeOptions(Scanner sc)throws Exception {
		System.out.println("Enter Employee ID : ");
		int id = sc.nextInt();
		System.out.println("Enter Employee Name : ");
		String name=sc.next(); 
		System.out.println("Enter Employee Salary : ");
		int salary = sc.nextInt();
		System.out.println("Enter Employee Age : ");
		int age = sc.nextInt();
		Employee emp = context.getBean(Employee.class);
		emp.setId(id);
		emp.setName(name);
		emp.setSalary(salary);
		emp.setAge(age);
		try {
			if(impl.updateEmployeeInfo(emp))
				System.out.println("Employee with id "+id+" got updated");
		} catch (EmployeeNotFoundException e) {
			System.out.println("Exception occured: "+e.getMessage());
		} catch(InvalidSalaryException e) {
			System.out.println("Exception occured: "+e.getMessage());
		}
	}
	
	private void displayEmployeeOption(Scanner sc) {
		System.out.println("Enter Employee ID : ");
		int id = sc.nextInt();
		Employee emp = impl.getEmployeeInfo(id);
		if(emp==null)
			System.out.println("Provided Employee Id not valid");
		else
			System.out.println(emp);
	}
	
	private void displayAllEmployeeOption(Scanner sc) {
		List<Employee> employeeList = impl.getAllEmployeeInfo(0);
		if(employeeList!=null && employeeList.size()==0)
			System.out.println("************No Employees to Display************");
		for(Employee employee : employeeList)
			if(employee!=null)
				System.out.println(employee);
	}
	
	private void displayHRAOption(Scanner sc) {
		System.out.println("Enter Employee ID : ");
		int id = sc.nextInt();
		double hra = impl.getEmployeeHRA(id);
		if(hra==0)
			System.out.println("Provided Employee Id not valid");
		else
			System.out.println("Employee id : "+id+" , HRA : "+hra);
	}
	
	private void displayGrossSalOption(Scanner sc) {
		System.out.println("Enter Employee ID : ");
		int id = sc.nextInt();
		double salary = impl.getEmployeeGrossSalary(id);
		if(salary==0)
			System.out.println("Provided Employee Id not valid");
		else
			System.out.println("Employee id : "+id+" , Gross Salary : "+salary);
	}
	
	private void sortBySalary() {
		List<Employee> employeeList = impl.getAllEmployeeInfo(1);
		for(Employee employee : employeeList)
			if(employee!=null)
				System.out.println(employee);
	}
	
	private void sortByNameAndSalary() {
		List<Employee> employeeList = impl.getAllEmployeeInfo(2);
		for(Employee employee : employeeList)
			if(employee!=null)
				System.out.println(employee);
	}
	
	private void sortByID() {
		List<Employee> employeeList = impl.getAllEmployeeInfo(3);
		for(Employee employee : employeeList)
			if(employee!=null)
				System.out.println(employee);
	}
	
}
