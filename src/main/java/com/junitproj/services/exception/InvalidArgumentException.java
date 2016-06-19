package com.junitproj.services.exception;

public class InvalidArgumentException extends Exception {

	private static final long serialVersionUID = 3090301893192143752L;

	private String argument;
	
	public InvalidArgumentException(String arg)
	{
		argument = arg;
	}

	/**
	 * @return the argument
	 */
	public String getArgument() {
		return argument;
	}

	
	public String toString()
	{
		return "The argument " + argument + " is null or empty.";
	}
}
