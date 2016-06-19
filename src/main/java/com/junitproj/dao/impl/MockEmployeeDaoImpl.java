package com.junitproj.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.junitproj.dao.api.EmployeeDao;
import com.junitproj.services.model.Employee;

public class MockEmployeeDaoImpl implements EmployeeDao {

	private static final Logger LOG = Logger.getLogger(MockEmployeeDaoImpl.class);

	private List<Employee> employees = new ArrayList<Employee>();

	public MockEmployeeDaoImpl() {

		createEmployees();
	}
	@Override
	public Employee searchEmployeeById(String empId) throws SQLException {
		if (empId == null || empId.trim().equals(""))
		{
			return null;
		}
		for (Employee e: employees)
		{
			if (e.getId().toLowerCase().equals(empId.toLowerCase()))
			{
				return e;
			}
		}
		return null;
	}

	@Override
	public boolean addEmployee(Employee emp) throws SQLException {
		if (emp == null)
		{
			LOG.debug("Employee cannot be added as the employee is null");
			return false;
		}
		boolean exists = false;
		for (Employee e: employees)
		{
			if (e.getId().toLowerCase().equals(emp.getId().toLowerCase()))
			{
				exists = true;
			}
		}
		if (!exists)
		{
			employees.add(emp);
			return true;
		}
		else {
			LOG.debug("Employee " + emp.getId() + " already exists");
			return false;
		}

	}

	private void createEmployees()
	{
		employees.add(new Employee("1001", "Vijay", "Maringanti", "1/54 Prospect Street"));
		employees.add(new Employee("1002", "Srirama", "Maringanti", "1/54 Prospect Street"));
		employees.add(new Employee("1003", "Ramesh", "Babu", "33 Delhi Street"));
		employees.add(new Employee("1004", "Devan", "Mukerjee", "21 Gorakpur Street"));
		employees.add(new Employee("1005", "Harjeet", "Gaut", "22 Pathanpur Street"));
		employees.add(new Employee("1006", "Prasad", "Swami", "10 Gondala Street"));
	}

	@Override
	public int getTotal() throws SQLException {
		return employees.size();
	}

	@Override
	public boolean deleteEmployee(String empId) throws SQLException {
		Employee emp = searchEmployeeById(empId);
		return employees.remove(emp);
	}

}
