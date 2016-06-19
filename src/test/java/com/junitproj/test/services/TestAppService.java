package com.junitproj.test.services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junitproj.services.ServiceFactory;
import com.junitproj.services.api.EmployeeService;
import com.junitproj.services.exception.ServiceException;
import com.junitproj.services.model.Employee;

public class TestAppService {

	private EmployeeService empService = null;

	private static final String APPLICATION_CONTEXT = "application-context.xml";

	private ApplicationContext context;

	@Before
	public void setUp() throws Exception {

		context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
		ServiceFactory factory = (ServiceFactory) context.getBean("serviceFactory");

		empService = factory.getEmployeeService();

	}

	@Test
	public void testAddEmployee() {
		try {
			int count = empService.getTotalEmployees();
			empService.addEmployee(new Employee("1", "A", "B", "C"));
			assertTrue(empService.getTotalEmployees()  == (count + 1));
			empService.deleteEmployee("1");
		}
		catch (ServiceException se)
		{
			se.printStackTrace();
			fail("Failed" + se.getMessage());
		}
	}

	@Test
	public void testSearchEmployee() {
		try {
			empService.addEmployee(new Employee("121", "ACC", "BCC", "CCC"));
			Employee e = empService.searchEmployee("121");

			assertTrue("121".equals(e.getId()));
			empService.deleteEmployee("121");
		}
		catch (ServiceException se)
		{
			se.printStackTrace();
			fail("Failed" + se.getMessage());
		}

	}

	@Test
	public void testEmployeeNotExists() {
		try {
			Employee e = empService.searchEmployee("6756765");
			assertTrue(e == null);

		}
		catch (ServiceException se)
		{
			se.printStackTrace();
			fail("Failed" + se.getMessage());
		}
	}

	@Test
	public void testDeleteEmployee() {
		try {
			empService.addEmployee(new Employee("1", "A", "B", "C"));
			int count = empService.getTotalEmployees();
			empService.deleteEmployee("1");
			assertTrue(empService.getTotalEmployees() == (count - 1));
			Employee emp = empService.searchEmployee("1");
			assertTrue(emp == null);
		}
		catch (ServiceException se)
		{
			se.printStackTrace();
			fail("Failed" + se.getMessage());
		}
	}
}
