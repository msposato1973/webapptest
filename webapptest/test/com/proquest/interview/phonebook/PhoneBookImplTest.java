package com.proquest.interview.phonebook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PhoneBookImplTest {
	private PhoneBook phoneBook;

	@Before
	public void setUp() {
		phoneBook = new PhoneBookImpl();
		phoneBook.addPerson(new Person("Massimo Sposato", "003920141", "9 Via val di sole, Milano, IT"));
	}
	
	@Test
	public void emptyPhoneBookTest() {
		phoneBook = new PhoneBookImpl();
		assertEquals(phoneBook.findAll().size(), 0);
	}
	
	@Test
	public void addPersonTest() {
		int count =	phoneBook.findAll().size();
		phoneBook.addPerson(new Person("Max Conrad", "(321) 231-7876","63 Mervern rd, London, UK"));
		assertEquals(phoneBook.findAll().size(), count + 1);
	
	}
	
	@Test
	public void findPersonTest() {
		 
		String firstName = "Massimo";
		String lastName = "Sposato";
		
		assertNotNull(phoneBook.findPerson(firstName,lastName));
	
	}
	
	@Test
	public void findByCriteriaTest() {
		 
		String paramName = "NAME";
		String paramValue = "Massimo Sposato";
		assertNotNull(phoneBook.findByCriteria(paramName,paramValue));
		
		paramName = "PHONENUMBER";
		paramValue = "003920141";
		assertNotNull(phoneBook.findByCriteria(paramName,paramValue));
		
		paramName = "ADDRESS";
		paramValue = "9 Via val di sole, Milano, IT";
		assertNotNull(phoneBook.findByCriteria(paramName,paramValue));
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void findByCriteriaErrorTest() {
		 
		String paramName = "NAME";
		String paramValue = "";
		assertNotNull(phoneBook.findByCriteria(paramName,paramValue));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
 	public void addPersonNullErrorTest() {
 		phoneBook.addPerson(null);
 	}
	
	@Test(expected=IllegalArgumentException.class)
	public void findNullErrorTest() {
		phoneBook.findPerson("", "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void personWithSurnameNullErrorTest() {
		phoneBook.findPerson("Max", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void personWithNameNullErrorTest() {
		phoneBook.findPerson(null, "Spot");
	}
	
	 
}
