package com.proquest.interview.phonebook;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
 

public interface PhoneBook {
	  Person findPerson(String firstName, String lastName);
	  void addPerson(Person newPerson);
	  Collection<Person> findAll();
	  boolean findPerson(Person newPerson,Connection con);
	  void insertAllPeople(Collection<Person> people);
	  List<Person> findByCriteria(String param, String criteria) ;
}
