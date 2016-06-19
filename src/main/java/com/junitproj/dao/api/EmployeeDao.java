package com.junitproj.dao.api;

import java.sql.SQLException;

import com.junitproj.services.model.Employee;

public interface EmployeeDao {

	Employee searchEmployeeById(String empId)  throws SQLException;

	boolean addEmployee(Employee emp) throws SQLException;

	int getTotal() throws SQLException;

	boolean deleteEmployee(String empId) throws SQLException;
}
