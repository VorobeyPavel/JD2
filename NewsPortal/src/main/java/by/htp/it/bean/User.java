package by.htp.it.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String surname;
	private String email;
	private String role;
	private String password;
	private String dateRegistration;

	public User() {
		super();
	}

	public User(int id, String name, String role) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
	}

	/*
	 * public User(int id, String name, String password) { super(); this.id = id;
	 * this.name = name; this.password = password; }
	 */

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
	

	public User(String name, String surname, String email, String password, String dateRegistration, String role) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateRegistration = dateRegistration;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(String dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateRegistration, email, id, name, password, role, surname);
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
		return Objects.equals(dateRegistration, other.dateRegistration) && Objects.equals(email, other.email)
				&& id == other.id && Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", role=" + role
				+ ", password=" + password + ", dateRegistration=" + dateRegistration + "]";
	}

}
