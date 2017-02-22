package components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	Connection c;
	Statement stmt;
	private static int primaryKey = 0;
	
	// Constructor (creates db file if doesn't exist)
	public Database() {
		c = null;
		stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:profiles.db");
			createTable();
			primaryKey = getID();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.out.println(this.getClass());
			e.printStackTrace();
		    System.exit(0);
		}
	}
	
	private int getID() throws SQLException {
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT MAX(ID) FROM PROFILES" );
	    int id = rs.getInt(1);
	    rs.close();
	    return ++id;
	}
	
	protected void createTable() throws SQLException {
		stmt = c.createStatement();
		String createTableQuery = "CREATE TABLE IF NOT EXISTS PROFILES " +
                				  "(ID INT PRIMARY KEY     NOT NULL," +
                				   "USERNAME      TEXT   NOT NULL," +
                				   "PASSWORD      TEXT   NOT NULL," +
                				   "NAME          TEXT   ," + 
                				   "BIRTHDAY      TEXT   ," +
                				   "EMAIL         TEXT   , " + 
                				   "PHONE_NUMBER  TEXT   ," + 
                				   "BIO			  TEXT   );"; 
		stmt.executeUpdate(createTableQuery);
	}
	
	public void createProfile(String username, String password) throws SQLException {
		stmt = c.createStatement();
		String createQuery = String.format("INSERT INTO PROFILES (ID, USERNAME, PASSWORD)"
				+ "VALUES (%d,'%s','%s');", primaryKey, username, password);
		stmt.executeUpdate(createQuery);
		primaryKey++;
	}
	
	public void editProfile(String username, String name, String birthday, String email, String phoneNumber, String bio) throws SQLException {
		stmt = c.createStatement();
		String editQuery = String.format("UPDATE PROFILES SET NAME = '%s', BIRTHDAY = '%s', EMAIL = '%s', "
				+ "PHONE_NUMBER = '%s', BIO = '%s'", name, birthday, email, phoneNumber, bio);
		stmt.executeQuery(editQuery);
	} 
	
	public String getUP(String username) throws SQLException {
		stmt = c.createStatement();
		ResultSet getQuery = stmt.executeQuery(String.format("SELECT PASSWORD FROM PROFILES WHERE USERNAME = '%s';", username));
		return getQuery.getString(1);
		
	}
	
	public static void main(String[] args) {
		Database d = new Database();
		try {
			d.createProfile("Jonathan", "password");
			System.out.println(d.getUP("Jonathan"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
