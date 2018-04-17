package com.proquest.interview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is just a utility class, you should not have to change anything here
 * @author m.sposato
 */
public class DatabaseUtil {
	
	/**
	 * 
	 */
	public static void initDB() {
		Connection cn = null;
		Statement stmt = null;
		try {
			cn = getConnection();
			stmt = cn.createStatement();
			
			stmt.execute("CREATE TABLE PHONEBOOK (NAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
			stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Chris Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
			stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Dave Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
			cn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}finally {
			close(cn, stmt);
		}
		
	}
	
	/***
	 * 
	 * @param cn
	 * @param stmt
	 * @param sqlQuery
	 * @return
	 */
	public static boolean save(Connection cn, Statement stmt,String sqlQuery) {
		try {
			return stmt.execute(sqlQuery);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/***
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
	}
	
	/***
	 * 
	 * @param cn
	 */
	public static void closeConnection(Connection cn){
		try {
			if(cn!=null)
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param cn
	 * @param stmt
	 */
	public static void close(Connection cn, Statement stmt){
		try {
			if(stmt!=null) stmt.close();
			if(cn!=null) cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param cn
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection cn, Statement stmt, ResultSet rs){
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(cn!=null) cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param stmt
	 * @param rs
	 */
	public static void close(Statement stmt, ResultSet rs){
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param connection
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Statement getStatementObj(Connection connection) throws SQLException, ClassNotFoundException {
		Statement statement = connection.createStatement();
		return	statement;
	}
	
	/***
	 * 
	 * @param stmt
	 * @param sqlQuery
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet getResultSet(Statement stmt,String sqlQuery) throws SQLException, ClassNotFoundException {
		ResultSet rs  = stmt.executeQuery(sqlQuery) ;
		return	rs;
	}
	
	
	/***
	 * 
	 * @param cn
	 * @param sqlQuery
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static PreparedStatement getPrepered(Connection cn,String sqlQuery) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = null;
		cn.setAutoCommit(false);
		ps = cn.prepareStatement(sqlQuery);
		return	ps;
	}
}
