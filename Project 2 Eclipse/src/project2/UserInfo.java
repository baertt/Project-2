package project2;

import java.time.LocalDate;

public class UserInfo {
	private String password, name, phone, email;
	private LocalDate bday;

	public UserInfo(String password, String name, LocalDate bday, String phone, String email) {
		this.password = password;
		this.name = name;
		this.bday = bday;
		this.phone = phone;
		this.email = email;
	}

	public String getPassword(){return this.password;}
	public String getName(){return this.name;}
	public String getPhone(){return this.phone;}
	public String getEmail(){return this.email;}
	public LocalDate getBday(){return this.bday;}


}
