package by.myproject.news.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private String email;
	private String role;
	private String password;
	private String dateRegistration;

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;

	}

	public User(String name, String email, String password) {

		this.email = email;
		this.password = password;
		this.name = name;
	}

	public User(String name, String surname, String email, String password, String date) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateRegistration = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(String dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());            
	    result = prime * result + ((surname == null) ? 0 : surname.hashCode());
	    result = prime * result + ((email == null) ? 0 : email.hashCode());
	    result = prime * result + ((role == null) ? 0 : role.hashCode());
	    result = prime * result + ((dateRegistration == null) ? 0 : dateRegistration.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + "name:" + name + ", surname:" + surname + ", email:" + email + ", role:" + role + 
				", dateRegistration:" + dateRegistration ;
	}
	
	

}
