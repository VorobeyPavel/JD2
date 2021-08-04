package by.myproject.news.bean;

import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

public class RegistrationInfo {
	
	private String name;
	private String email;
	private String enter_password;
	private String repeat_password;
	private String surname;
	private String role;
		
	public RegistrationInfo(HttpServletRequest request) {
		this.email = request.getParameter("email");
		this.enter_password = request.getParameter("enter_password");
		this.repeat_password = request.getParameter("repeat_password");
		this.name = request.getParameter("name");
		this.surname = request.getParameter("surname");
		
	}
	public String getSurname() {
		return surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}
	
	public String getEnterPassword() {
		return enter_password;
	}
	
	public String getRepeatPassword() {
		return repeat_password;
	}
	
	public String getName() {
		return name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());            
	    result = prime * result + ((surname == null) ? 0 : surname.hashCode());
	    result = prime * result + ((email == null) ? 0 : email.hashCode());
	    result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		RegistrationInfo other = (RegistrationInfo) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&&  Objects.equals(role, other.role)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + "name:" + name + ", surname:" + surname + ", email:" + email + ", role:" + role;
	}
}
