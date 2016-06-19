package com.junitproj.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.junitproj.dao.api.EmployeeDao;
import com.junitproj.services.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

	private static final String DATA_SOURCE = "oracle.jdbc.xa.client.OracleXADataSource";

	private static final Logger LOG = Logger.getLogger(EmployeeDaoImpl.class);

	private static final String USERNAME = "COMPANY_DB";

	private static final String PASSWORD = "COMPANY_DB";

	public EmployeeDaoImpl()
	{
		try
		{
			Class.forName(DATA_SOURCE);
		} catch (ClassNotFoundException e) {
			LOG.error(DATA_SOURCE + " could not be found: ", e);
		}
	}

	@Override
	public Employee searchEmployeeById(String empId) throws SQLException
	{
		Employee emp = null;
		ResultSet results = null;
		Statement statement = null;
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(DB_CONNECTION, USERNAME, PASSWORD);

			statement = connection.createStatement();
			results = statement.executeQuery("SELECT EMPLOYEE_ID, EMPLOYEE_NAME, " +
					"EMPLOYEE_SURNAME, ADDRESS FROM EMPLOYEE WHERE EMPLOYEE_ID = '" + empId + "'");

			if (results != null && results.next())
			{
				emp = new Employee(
				    results.getString(1), results.getString(2),
				    results.getString(3), results.getString(4));
			}
		}
		finally
		{
			closeConnections(results, statement, connection);
		}
		return emp;
	}

	public boolean addEmployee(Employee emp) throws SQLException
	{

		if (searchEmployeeById(emp.getId()) == null)
		{
			ResultSet results = null;
			PreparedStatement statement = null;
			Connection connection = null;
			try
			{
				connection = DriverManager.getConnection(DB_CONNECTION, USERNAME, PASSWORD);
				StringBuffer insertSQL = new StringBuffer("INSERT INTO EMPLOYEE VALUES (");
				insertSQL.append("'");
				insertSQL.append(emp.getId());
				insertSQL.append("',");
				insertSQL.append("'");
				insertSQL.append(emp.getName());
				insertSQL.append("',");
				insertSQL.append("'");
				insertSQL.append(emp.getSurname());
				insertSQL.append("',");
				insertSQL.append("'");
				insertSQL.append(emp.getAddress());
				insertSQL.append("')");

				statement = connection.prepareStatement(insertSQL.toString());

				int records = statement.executeUpdate(insertSQL.toString());
				connection.commit();

				return (records > 0);
			}
			catch(Exception e)
			{
				connection.rollback();
				e.printStackTrace();
			}
			finally
			{
				closeConnections(results, statement, connection);
			}
		}
		else
		{
			throw new SQLException("Employee " + emp.getId() + " already exists");
		}
		return false;
	}

	private void closeConnections(ResultSet results, Statement statement, Connection connection)
	{
		try
		{
			if (statement != null)
			{
				statement.close();
			}
			if (results != null)
			{
				results.close();
			}
			if (connection != null)
			{
				connection.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int getTotal() throws SQLException
	{
		ResultSet results = null;
		Statement statement = null;
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(DB_CONNECTION, USERNAME, PASSWORD);

			statement = connection.createStatement();
			results = statement.executeQuery("SELECT COUNT(*)FROM EMPLOYEE");

			if (results != null && results.next())
			{
				return results.getInt(1);
			}
		}
		finally
		{
			closeConnections(results, statement, connection);
		}
		return 0;
	}

	@Override
	public boolean deleteEmployee(String empId) throws SQLException {
		ResultSet results = null;
		Statement statement = null;
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(DB_CONNECTION, USERNAME, PASSWORD);
			String deleteCommand = "DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID = '" + empId + "'";
			statement = connection.prepareStatement(deleteCommand);

			int records = statement.executeUpdate(deleteCommand);
			connection.commit();

			return (records > 0);
		}
		finally
		{
			closeConnections(results, statement, connection);
		}
	}
}
