package com.imcs.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imcs.core.entity.Employee;
import com.imcs.jdbc.interfaces.EmployeeDAO;
import com.imcs.jdbc.util.ReadDBPropertiesFile;

public class EmployeeDAOimpl implements EmployeeDAO {
	
	Connection connection;
    {
        try {
            connection = createConnection();
        } catch (ClassNotFoundException e) {
        	System.out.println(e.getMessage());
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
    }

	public boolean addEmployee(Employee employee) throws SQLException {
		boolean isAdded = false;
        try(PreparedStatement statement = connection.prepareStatement("insert into imcs.employee (empid, name, age, salary) values (?, ?, ?, ?) ");) 
        {
			statement.setInt(1, employee.getId());
			statement.setString(2, employee.getName());
			statement.setInt(3, employee.getAge());
			statement.setInt(4, employee.getSalary());
			isAdded =  statement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return isAdded;
	}

	@Override
	public boolean deleteEmployee(int id) throws SQLException {
		boolean isDeleted = false;
        try(PreparedStatement statement = connection.prepareStatement("delete from imcs.employee where empid = ? ")) 
        {
			statement.setInt(1, id);
			isDeleted = statement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return isDeleted;
	}

	@Override
	public boolean updateEmployeeInfo(Employee employee) throws SQLException {
		boolean isUpdated = false;
		if (employee == null) return false;
        try(PreparedStatement statement = connection.prepareStatement("update imcs.employee set name = ?, age = ?, salary = ? where empid = ?");) 
        {
			statement.setString(1, employee.getName());
			statement.setInt(2, employee.getAge());
			statement.setInt(3, employee.getSalary());
			statement.setInt(4, employee.getId());
			int recordsUpdated = statement.executeUpdate();
			isUpdated =  recordsUpdated > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return isUpdated;
	}

	@Override
	public Employee getEmployeeInfo(int id) throws SQLException {
		Employee employee = null;
		ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement("select * from imcs.employee where empid = ? "))
        {
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee();
			    employee.setId(resultSet.getInt(1));
			    employee.setName(resultSet.getString(2));
			    employee.setAge(resultSet.getInt(3));
			    employee.setSalary(resultSet.getInt(3));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(resultSet!=null)
				resultSet.close();
		}
        return employee;
	}

	@Override
	public List<Employee> getAllEmployeeInfo(int orderby) throws SQLException {
		List<Employee> list = null;
		StringBuilder sb = new StringBuilder("select * from imcs.employee");
		if(orderby==1)
			sb.append(" order by salary");
		else if(orderby==2)
			sb.append(" order by Name,salary");
		else if(orderby==3)
			sb.append(" order by empid");
		try(PreparedStatement statement = connection.prepareStatement(sb.toString());
			ResultSet resultSet = statement.executeQuery();) 
			{
			list = new ArrayList<Employee>();
			while (resultSet.next()) {
				Employee employee = new Employee();
			    employee.setId(resultSet.getInt("empid"));
			    employee.setName(resultSet.getString("name"));
			    employee.setAge(resultSet.getInt("age"));
			    employee.setSalary(resultSet.getInt("salary"));
			    list.add(employee);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return list;
	}

	@Override
	public int getEmployeeSalary(int id) throws SQLException {
		ResultSet resultSet = null;
		int salary = 0;
		try(PreparedStatement statement = connection.prepareStatement("select salary from imcs.employee where empid = ? ")) {
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
			    salary =  resultSet.getInt("salary");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(resultSet!=null)
				resultSet.close();
		}
        return salary;
	}
	
	private Connection createConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		String driverClass = null;
		String url = null;
		String username = null;
		String pwd = null;
		try{
			driverClass = ReadDBPropertiesFile.getProperty("driver");
			url = ReadDBPropertiesFile.getProperty("url");
			username = ReadDBPropertiesFile.getProperty("username");
			pwd = ReadDBPropertiesFile.getProperty("pwd");
			Class.forName(driverClass);
			con = DriverManager.getConnection(url,username,pwd);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}  
		return con;
    }
	
	public void finalize() {
		try {
			if(connection!=null)
				connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
