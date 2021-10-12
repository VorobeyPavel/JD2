package by.htp.main.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="news")
public class News {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int idNews;
	
	@NotNull(message = "This field cannot be empty")
	@Size(min = 3, max= 100, message = "The title must be between 5 and 250 characters")
	@Column(name="title")
	private String titleNews;
	
	@NotNull(message = "This field cannot be empty")
	@Size(min = 3, max= 200, message = "The title must be between 5 and 500 characters")
	@Column(name="brief")
	private String briefNews;
	
	@NotNull(message = "This field cannot be empty")
	@Size(min = 3, max= 5000, message = "The title must be between 5 and 5000 characters")
	@Column(name="content")
	private String contentNews;
	
	@Column(name="date")
	private Date dateNews=new Date();
	
	public News() {
		
	}
	

	public News(String titleNews, String briefNews, String contentNews, Date dateNews) {
		super();
		this.titleNews = titleNews;
		this.briefNews = briefNews;
		this.contentNews = contentNews;
		this.dateNews = dateNews;
	}


	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public String getTitleNews() {
		return titleNews;
	}

	public void setTitleNews(String titleNews) {
		this.titleNews = titleNews;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public String getContentNews() {
		return contentNews;
	}

	public void setContentNews(String contentNews) {
		this.contentNews = contentNews;
	}

	public Date getDateNews() {
		return dateNews;
	}

	public void setDateNews(Date dateNews) {
		this.dateNews = dateNews;
	}

	@Override
	public String toString() {
		return "News [idNews=" + idNews + ", titleNews=" + titleNews + ", briefNews=" + briefNews + ", contentNews="
				+ contentNews + ", dateNews=" + dateNews + "]";
	}

			
}


