package by.htp.it.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class News implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String brief;
	private String content;
	private Date date;


	public News() {
		super();
	}

	public News(String title, String brief) {
		super();
		this.title = title;
		this.brief = brief;
	}
	
	public News(String title, String brief, String content) {
		super();
		this.title = title;
		this.brief = brief;
		this.content = content;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(brief, content, date, id, title);
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
		return Objects.equals(brief, other.brief) && Objects.equals(content, other.content)
				&& Objects.equals(date, other.date) && id == other.id && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", brief=" + brief + ", content=" + content + ", date=" + date
				+ "]";
	}
	
	
	
}
