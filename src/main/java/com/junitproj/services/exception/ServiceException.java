package com.junitproj.services.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 3090301893192143752L;

	private String argument;
	
	public ServiceException(String arg)
	{
		super(arg);
	}
	
	public ServiceException()
	{
		super();
	}
	
	public ServiceException(Exception e)
	{
		super(e);
	}
	
	public ServiceException(Throwable t)
	{
		super(t);
	}

	/**
	 * @return the argument
	 */
	public String getArgument() {
		return argument;
	}

}
