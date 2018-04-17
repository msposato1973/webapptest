package com.proquest.interview.dao;

/**
 * 
 * @author m.sposato
 *
 */
public interface EntityMap {
	/***
	 * 
	 * @return
	 */
	public String getDatabaseColumnName();
	
	/***
	 * 
	 * @return
	 */
	public String getEntityColumnName();
}
