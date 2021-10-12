package by.htp.it.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String content;
	private Date publishingDate;
	private int id_user;
	private int id_news;

	public Comment(int id, String content, Date publishingDate, int id_user, int id_news) {
		super();
		this.id = id;
		this.content = content;
		this.publishingDate = publishingDate;
		this.id_user = id_user;
		this.id_news = id_news;
	}

	public Comment(int id_news, int id_user, String content) {
		this.id_news = id_news;
		this.id_user = id_user;
		this.content = content;
	}

	public Comment(String comment, Date date) {
		this.content = comment;
		this.publishingDate = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_news() {
		return id_news;
	}

	public void setId_news(int id_news) {
		this.id_news = id_news;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + id_news;
		result = prime * result + id_user;
		result = prime * result + ((publishingDate == null) ? 0 : publishingDate.hashCode());
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
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (id_news != other.id_news)
			return false;
		if (id_user != other.id_user)
			return false;
		if (publishingDate == null) {
			if (other.publishingDate != null)
				return false;
		} else if (!publishingDate.equals(other.publishingDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id=" + id + ", content=" + content + ", publishingDate=" + publishingDate
				+ ", id_user=" + id_user + ", id_news=" + id_news + "]";
	}

}
