package com.junitproj.services.model;

public class Employee {

	private String mId;
	
	private String mName;
	
	private String mSurname;
	
	private String mAddress;

	public Employee(String empId, String name, String surname, String address)
	{
		mId = empId;
		mName = name;
		mSurname = surname;
		mAddress = address;
		
	}
	
	/**
	 * @return the mId
	 */
	public String getId() {
		return mId;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setId(String id) {
		this.mId = id;
	}

	/**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @param mName the mName to set
	 */
	public void setName(String name) {
		this.mName = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return mSurname;
	}

	/**
	 * @param mSurname the mSurname to set
	 */
	public void setSurname(String surname) {
		this.mSurname = surname;
	}

	/**
	 * @return the mAddress
	 */
	public String getAddress() {
		return mAddress;
	}

	/**
	 * @param mAddress the mAddress to set
	 */
	public void setAddress(String address) {
		this.mAddress = address;
	}
	
	
}
