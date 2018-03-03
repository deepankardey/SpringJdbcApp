package com.imcs.core.entity;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@AllArgsConstructor
@ToString
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
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

}
