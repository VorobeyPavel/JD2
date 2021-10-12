package by.htp.main.service;

import java.util.List;

import by.htp.main.entity.News;


public interface NewsService {

	public List<News> getNewses();

	public void saveNews(News theNews);

	public News getNews(int theId);

	public void deleteNews(int theId);
	
}

