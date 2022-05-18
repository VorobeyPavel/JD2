package by.htp.it.bean;

import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

public class RegistrationInfo {
	
	private String name;
	private String email;
	private String enter_password;
	private String repeat_password;
	private String surname;
	private String role;
	private String newPassword;
		
	public RegistrationInfo() {
		super();
	}
	
	public RegistrationInfo(String enter_password, String newPassword) {
		super();
		this.enter_password = enter_password;
		this.newPassword = newPassword;
	}
	
	public RegistrationInfo(HttpServletRequest request) {
		this.email = request.getParameter("email");
		this.enter_password = request.getParameter("enter_password");
		this.repeat_password = request.getParameter("repeat_password");
		this.name = request.getParameter("name");
		this.surname = request.getParameter("surname");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnter_password() {
		return enter_password;
	}

	public void setEnter_password(String enter_password) {
		this.enter_password = enter_password;
	}

	public String getRepeat_password() {
		return repeat_password;
	}

	public void setRepeat_password(String repeat_password) {
		this.repeat_password = repeat_password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enter_password, name, newPassword, repeat_password, role, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationInfo other = (RegistrationInfo) obj;
		return Objects.equals(email, other.email) && Objects.equals(enter_password, other.enter_password)
				&& Objects.equals(name, other.name) && Objects.equals(newPassword, other.newPassword)
				&& Objects.equals(repeat_password, other.repeat_password) && Objects.equals(role, other.role)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "RegistrationInfo [name=" + name + ", email=" + email + ", enter_password=" + enter_password
				+ ", repeat_password=" + repeat_password + ", surname=" + surname + ", role=" + role + ", newPassword="
				+ newPassword + "]";
	}
	
	
	
}
