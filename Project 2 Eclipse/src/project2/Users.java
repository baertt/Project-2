package project2;

import java.time.LocalDate;
import java.util.Hashtable;

import Objects.UserInfo;

public class Users {
	private Hashtable<String, UserInfo> users = new Hashtable<String, UserInfo>();

	public void add(String username, String password, String name, LocalDate bday,
			        String phone, String email /*String biography*/){
		users.put(username, new UserInfo(password, name, bday, phone, email /*biography*/));
	}
	
	/**
	 * Checks whether username exists in hashtable
	 * @param  		String username
	 * @return      boolean 
	 */
	public boolean checkUserName(String username) {
		return users.containsKey(username);
	}
}
