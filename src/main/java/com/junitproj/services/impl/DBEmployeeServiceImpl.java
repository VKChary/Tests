package com.junitproj.services.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.junitproj.dao.api.EmployeeDao;
import com.junitproj.services.api.EmployeeService;
import com.junitproj.services.exception.ServiceException;
import com.junitproj.services.model.Employee;

public class DBEmployeeServiceImpl implements EmployeeService {

	private static final Logger LOG = Logger.getLogger(DBEmployeeServiceImpl.class);

	private EmployeeDao employeeDao;

	public DBEmployeeServiceImpl(EmployeeDao employeeDao)
	{
		super();
		this.employeeDao = employeeDao;
	}

	public Employee searchEmployee(String empID) throws ServiceException
	{
		try
		{
			Employee emp = employeeDao.searchEmployeeById(empID);
			if (emp != null) LOG.debug("Found employee: " + emp.getId());
			return emp;
		}
		catch (SQLException sqle)
		{
			LOG.error(sqle);
			throw new ServiceException(sqle);
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new ServiceException(e);
		}

	}

	public void addEmployee(Employee emp) throws ServiceException {

		try
		{
			employeeDao.addEmployee(emp);
		}
		catch (SQLException sqle)
		{
			LOG.error(sqle);
			throw new ServiceException(sqle);
		}
	}

	public boolean deleteEmployee(String empID) throws ServiceException {

		try {
			return employeeDao.deleteEmployee(empID);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	public int getTotalEmployees() throws ServiceException {

		try {
			return employeeDao.getTotal();
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}
