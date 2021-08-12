package by.htp.it.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String title;
	private String brief;
	
	public News(String title, String brief) {
		super();
		this.title = title;
		this.brief = brief;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(brief, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(brief, other.brief) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return title + " " +brief;
	}
	
	
}
