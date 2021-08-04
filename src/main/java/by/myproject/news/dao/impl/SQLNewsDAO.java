package by.myproject.news.dao.impl;

import java.util.ArrayList;
import java.util.List;


import by.myproject.news.bean.News;
import by.myproject.news.dao.NewsDAO;
import by.myproject.news.dao.exception.DAOException;

public class SQLNewsDAO implements NewsDAO {
	List<News> newses = new ArrayList<News>();

	@Override
	public List<News> addNewses(int quantity) throws DAOException {
		try {
		List<News> requestedNews = new ArrayList<News>();
		//Имитация базы данных
		newses.add(new News("News №1", "We managed to bring out news No. 1"));
		newses.add(new News("News №2", "We managed to bring out news No. 2"));
		newses.add(new News("News №3", "We managed to bring out news No. 3"));
		newses.add(new News("News №4", "We managed to bring out news No. 4"));
		newses.add(new News("News №5", "We managed to bring out news No. 5"));
		newses.add(new News("News №6", "We managed to bring out news No. 6"));
		newses.add(new News("News №7", "We managed to bring out news No. 7"));
		newses.add(new News("News №8", "We managed to bring out news No. 8"));
		newses.add(new News("News №9", "We managed to bring out news No. 9"));
		newses.add(new News("News №10", "We managed to bring out news No. 10"));
		int totalNews = newses.size();
		// Если запрошено новостей больше чем есть в базе возвращаем сколько имеется
		if(quantity > totalNews ) {
			quantity = totalNews;
		}
		// Закидываем нужное колличество новостей в list
		for(int i = 0; i < quantity; i++) {
			requestedNews.add(newses.get(i));
		}

		return requestedNews;
		
		}catch (Exception e) {
			throw new DAOException();
		}
	}

}
