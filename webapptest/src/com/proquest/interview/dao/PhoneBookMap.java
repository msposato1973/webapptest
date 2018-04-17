package com.proquest.interview.dao;

/**
 * 
 * @author m.sposato
 *
 */
public enum PhoneBookMap implements EntityMap {
	 
	NAME("name","NAME"),
	PHONENUMBER("phonenumber","PHONENUMBER"),
	ADDRESS("address","ADDRESS");

	private String databaseColumnName;
	private String entityColumnName;
	
	/***
	 * 
	 * @param entityColumnName
	 * @param databaseColumnName
	 */
	private PhoneBookMap(String entityColumnName, String databaseColumnName){
		this.databaseColumnName = databaseColumnName;
		this.entityColumnName = entityColumnName;
	}

	@Override
	public String getDatabaseColumnName(){
		return databaseColumnName;
	}

	@Override
	public String getEntityColumnName(){
		return entityColumnName;
	}
}
