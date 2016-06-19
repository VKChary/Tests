package com.junitproj.services;

import org.springframework.stereotype.Component;

import com.junitproj.services.api.EmployeeService;

@Component
public class ServiceFactory {

	private EmployeeService employeeService;

	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
