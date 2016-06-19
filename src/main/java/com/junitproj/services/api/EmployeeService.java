package com.junitproj.services.api;

import com.junitproj.services.exception.ServiceException;
import com.junitproj.services.model.Employee;

public interface EmployeeService {

	Employee searchEmployee(String empID) throws ServiceException;
	
	void addEmployee(Employee emp) throws ServiceException;
	
	boolean deleteEmployee(String empID) throws ServiceException;

	int getTotalEmployees() throws ServiceException;
	
}
