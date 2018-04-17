package com.proquest.interview.main;
import com.proquest.interview.phonebook.Person;
import com.proquest.interview.phonebook.PhoneBookImpl;
import com.proquest.interview.util.DBConnectionUtil;
import com.proquest.interview.util.DatabaseUtil;

/***
 * 
 * @author m.sposato
 *
 */
public class MainClas {

	/***
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseUtil.initDB(); 
		PhoneBookImpl bookImpl = new PhoneBookImpl();
		//DBConnectionUtil.getConnection();
		
		addPerson(bookImpl);
		
		for (Person person :  bookImpl.findAll()) {
			System.out.println(person);
		}
		
		Person p = bookImpl.findPerson("Massimo", "Sposato");
		System.out.println(p);
		
		// insert the new person objects into the database
		insertPeople(bookImpl);
		
	}

	/**
	 * 
	 * @param bookImpl
	 */
	public static void addPerson(PhoneBookImpl bookImpl ) {
		bookImpl.addPerson(new Person("Massimo Sposato", "(0039) 347-4725917",	"via val di sole,9b - 20141 Milano, IT"));
		bookImpl.addPerson(new Person("Max Spo", "(0039) 339-128758",  "Via Crispo, 9A - 20141 Milano, IT"));
		 
	}
	
	/**
	 * 
	 * @param bookImpl
	 */
	public static void insertPeople(PhoneBookImpl bookImpl ) {
		bookImpl.insertAllPeople(bookImpl.findAll());
	}
}
