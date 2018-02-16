package com.imcs.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@AllArgsConstructor
@ToString
public class Employee{

	private int id;
	private String name;
	private int salary;
	private int age;
	
	public Employee(){
		super();
	}
	
	public Employee(Employee employee) {
		super();
		this.id = employee.id;
		this.name = employee.name;
		this.salary = employee.salary;
		this.age = employee.age;
	}
	/*public Employee(int id, int number, String name, int salary, int age, String phone, Date dateOfJoining) {
		super();
		this.id = id;
		this.departmentNumber = number;
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.phone = phone;
		this.dateOfJoining = dateOfJoining;
	}


	static block
	static{
		companyName = "XYZ Comp";
	}
	instance block
	{
		this.salary = 10000;
	}

	public Employee(int id, int number, String name, int salary, int age) {
		super();
		this.id = id;
		this.departmentNumber = number;
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
	
	
	public Employee() {
		super();
	}
	
	public Employee(int id, String name, int salary,int age) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
	
	*//**
	 * Copy constructor
	 * @param employee
	 *//*
	public Employee(Employee employee) {
		super();
		this.departmentNumber = employee.departmentNumber;
		this.name = employee.name;
		this.salary = employee.salary;
	}

	public int getNumber() {
		return departmentNumber;
	}

	public void setNumber(int number) {
		this.departmentNumber = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		Employee.companyName = companyName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getDateOfJoining() {
		return dateOfJoining;
	}


	public void setDateOfJoining(Date dateOfBirth) {
		this.dateOfJoining = dateOfBirth;
	}


	public double calculateHRA(double salary) {
		return (0.2)*salary; 
	}
	
	public void getEmployeeInfo(Employee employee) {
		System.out.println(employee);
	}
	
	public static String getEmployeeCompanyName(Employee employee) {
		return employee.getCompanyName();
	}*/




}
