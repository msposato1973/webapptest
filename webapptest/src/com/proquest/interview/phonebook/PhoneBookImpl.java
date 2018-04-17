package com.proquest.interview.phonebook;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.proquest.interview.constant.ConstatPropertis;
import com.proquest.interview.dao.PhoneBookMap;
import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
	
	public List<Person> people = new ArrayList<Person>();
 
	
	public PhoneBookImpl(){
		super();
	}
	
	@Override
	public Person findPerson(String firstName, String lastName) {
		
		System.out.println("START: findPerson");
		
		String fullName = "";
		
		if ((firstName == null || firstName.equalsIgnoreCase("")) || (lastName == null || lastName.equalsIgnoreCase(""))) {
			throw new IllegalArgumentException("null");
		}else {
			fullName = firstName + " " +lastName;
		}
		
		StringBuilder sqlQuery = new StringBuilder(ConstatPropertis.SELECT);
		if(fullName!=null && !fullName.equalsIgnoreCase("")){
			sqlQuery.append(" WHERE ");
			sqlQuery.append(PhoneBookMap.NAME.getDatabaseColumnName());
			sqlQuery.append(" = " + fullName.toUpperCase());
		}
		
		System.out.println("SQL QUERY : "+sqlQuery.toString());
			
		for (Person person : people) {
				if (fullName.equals(person.getName())) return person;
		}
			 
		
		 
		System.out.println("END: findPerson");
		return null;
	}

	@Override
	public void addPerson(Person newPerson) {
	     System.out.println("START: addPerson");
	 	 
		 if (newPerson == null) throw new IllegalArgumentException("null");
		 people.add(newPerson);
		 
		 
		 System.out.println("END: addPerson");
	}

	@Override
	public Collection<Person> findAll() {
		
		System.out.println("START: findAll");
		if (people.isEmpty() || people==null) throw new IllegalArgumentException("null");
		
		System.out.println("END: findAll");
		return Collections.unmodifiableList(people);
		
	}

	@Override 
	public String toString() {
		return people.toString();
	}
	
	@Override
	public boolean findPerson(Person newPerson,Connection con) {
		System.out.println("START: findPerson");
		 
		Statement stmt = null;
		ResultSet resultSet = null;
		Person person = null;
		int count = 0;
		
		try {
		
			StringBuilder sqlQuery = new StringBuilder(ConstatPropertis.COUNT);
			if(newPerson!=null){
				sqlQuery.append(" WHERE ");
				sqlQuery.append(PhoneBookMap.NAME.getDatabaseColumnName());
				sqlQuery.append(" = " + newPerson.getName());
				sqlQuery.append(" AND ");
				sqlQuery.append(PhoneBookMap.ADDRESS.getDatabaseColumnName());
				sqlQuery.append(" = " + newPerson.getAddress());
				sqlQuery.append(" AND ");
				sqlQuery.append(PhoneBookMap.PHONENUMBER.getDatabaseColumnName());
				sqlQuery.append(" = " + newPerson.getPhoneNumber());
			}
			
			System.out.println("SQL QUERY : "+sqlQuery.toString());
			 
			stmt = DatabaseUtil.getStatementObj(con);
			resultSet = DatabaseUtil.getResultSet(stmt, sqlQuery.toString());
			person = new Person();
			
			while (resultSet.next()) {
				if (resultSet.wasNull())  System.out.println("name is null");
			    else count=resultSet.getInt(1);
			    System.out.println("---------------");
		   }
			
			DatabaseUtil.close(stmt, resultSet);
			 
		 } catch (ClassNotFoundException e) {
				e.printStackTrace();
		 } catch (SQLException e) {
			e.printStackTrace();
		 }finally{
			DatabaseUtil.close(stmt, resultSet);
		 }
		 
		System.out.println("END: findPerson");
		return (count>=0) ? true : false;
	}

	@Override
	public void insertAllPeople(Collection<Person> people) {
		  
		  Connection con = null;
		  Statement stmt = null;
		 
	       
	       if(people.isEmpty() || people.size() < 1) {
	    	   throw new IllegalArgumentException("null");
	       }
	       
	       
		   try {
			   
			   con = DatabaseUtil.getConnection();
			   con.setAutoCommit(false);
			   stmt = DatabaseUtil.getStatementObj(con);
			   for (Person person : people) {
				   String query = getSqlQueryInsert(person);
				   stmt.addBatch(query);
			   }
			   
			  //Create an int[] to hold returned values
			   stmt.executeBatch();
			  //Explicitly commit statements to apply changes
			   con.commit();
		   } catch (ClassNotFoundException e) {
				e.printStackTrace();
		   } catch (BatchUpdateException be) {
			 //handle batch update exception
				int[] counts = be.getUpdateCounts();
				for (int i=0; i<= counts.length; i++) {
					System.out.println("Statement["+i+"] :"+counts[i]);
				}
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		   } catch (SQLException e) {
			   e.printStackTrace();
			   try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		   }finally{
				DatabaseUtil.close(con,stmt);
		   }
		   
		 
		
	}
	
	
	private String getSqlQueryInsert(Person p) {
		   System.out.println(" START - getSqlQueryInsert ");
		   StringBuilder sqlQuery = new StringBuilder(ConstatPropertis.INSERT);
           sqlQuery.append(" (");
		   sqlQuery.append(PhoneBookMap.NAME.getDatabaseColumnName());
	       sqlQuery.append(", " + PhoneBookMap.PHONENUMBER.getDatabaseColumnName()  );
	       sqlQuery.append(", " + PhoneBookMap.ADDRESS.getDatabaseColumnName());
	       sqlQuery.append(") VALUES ('");
	       sqlQuery.append(p.getName());
	       sqlQuery.append("', '"+p.getPhoneNumber());
	       sqlQuery.append("', '"+ p.getAddress());
	       sqlQuery.append("')");
	       
	       System.out.println("getSqlQueryInsert - SQL QUERY : "+sqlQuery.toString()); 
	       System.out.println(" END - getSqlQueryInsert ");
	       return sqlQuery.toString();
	}

	@Override
	public List<Person> findByCriteria(String param, String criteria) {
		System.out.println("START: findByCriteria");
		
		if(param==null || param.equalsIgnoreCase("")) {
	    	   throw new IllegalArgumentException("param null");
	    }
		
		if(criteria==null || criteria.equalsIgnoreCase("")) {
	    	   throw new IllegalArgumentException(" criteria null");
	    }
		
		
		List<Person> search = new ArrayList<Person>();
		 
		Statement stmt = null;
		ResultSet resultSet = null;
		Person person = null;
		Connection con = null;
		
		try {
			
			StringBuilder sqlQuery = new StringBuilder(ConstatPropertis.CRITERIA);
			String query = sqlQuery.toString().replaceFirst(ConstatPropertis.PARAM, param);
			query = query.replaceFirst(ConstatPropertis.VAL, criteria);
			
			con = DatabaseUtil.getConnection();
			stmt = DatabaseUtil.getStatementObj(con);
			resultSet = DatabaseUtil.getResultSet(stmt, query);
			
			while (resultSet.next()) {
				
				person = new Person();
				person.setName(resultSet.getString(PhoneBookMap.NAME.getDatabaseColumnName()));
				person.setPhoneNumber(resultSet.getString(PhoneBookMap.PHONENUMBER.getDatabaseColumnName()));
				person.setAddress(resultSet.getString(PhoneBookMap.ADDRESS.getDatabaseColumnName()));
				
				if (resultSet.wasNull()) {
					System.out.println(" "+param + " is null");
			    } else {
			        System.out.println(" "+param + " is not null");
			        search.add(person);
			    }
				
				System.out.println("---------------");
		   }
		 
	 } catch (ClassNotFoundException e) {
		e.printStackTrace();
	 } catch (SQLException e) {
		e.printStackTrace();
	 }finally{
		DatabaseUtil.close(con,stmt, resultSet);
	 }
		
		System.out.println("END: findByCriteria");
		return search;
	}
	
	
	public static Connection getConnection() throws SQLException {
		try {
			return DatabaseUtil.getConnection();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
	}

}
