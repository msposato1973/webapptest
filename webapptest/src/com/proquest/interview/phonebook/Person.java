package com.proquest.interview.phonebook;

import java.io.Serializable;

/**
 * 
 * @author m.sposato
 *
 */
public class Person implements Serializable{
	
	public String name;
	public String phoneNumber;
	public String address;
	
	/************************************** COSTRUCTORS. ********************************************/
	/***
	 * 
	 * @param name
	 * @param phoneNumber
	 * @param address
	 */
	public Person(String name, String phoneNumber, String address){
		if (name == null || phoneNumber == null || address == null) {
			throw new IllegalArgumentException("null");
		}
		
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	/***
	 * 
	 * @param name
	 * @param phoneNumber
	 */
	public Person(String name, String phoneNumber){
		if (name == null || phoneNumber == null ) {
			throw new IllegalArgumentException("null");
		}
		
		this.name = name;
		this.phoneNumber = phoneNumber;
		 
	}
	
	public Person(){
		super();
	}
	
	/************************************** SET. *************************************************/
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/***
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/************************************** GET. *************************************************/
	
	/** Returns a formatted string of the person's name. */
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/** Returns a formatted string of the person's address. */
	/***
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	
	/** Returns a formatted string of the person's phoneNumber. */
	/**
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
	/** Returns a formatted string of the person's data. */
	@Override
	public String toString() {
		return "Person [name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null) return false;
		} else if (!address.equals(other.address)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null) return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		
		return true;
	}
}
