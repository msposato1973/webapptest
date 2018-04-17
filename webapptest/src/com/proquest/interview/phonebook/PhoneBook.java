package com.proquest.interview.phonebook;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
 
/**
 * 
 * @author m.sposato
 *
 */
public interface PhoneBook {
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	Person findPerson(String firstName, String lastName);
	
	/**
	 * 
	 * @param newPerson
	 */
	void addPerson(Person newPerson);
	
	/***
	 * 
	 * @return
	 */
	Collection<Person> findAll();
	
	/***
	 * 
	 * @param newPerson
	 * @param con
	 * @return
	 */
	boolean findPerson(Person newPerson,Connection con);
	  
	/***
	 * 
	 * @param people
	 */
	void insertAllPeople(Collection<Person> people);
	
	/***
	 * 
	 * @param param
	 * @param criteria
	 * @return
	 */
	List<Person> findByCriteria(String param, String criteria) ;
}
