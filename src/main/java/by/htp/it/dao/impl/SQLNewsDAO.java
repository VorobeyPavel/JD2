package by.htp.it.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.exception.DAOException;

public class SQLNewsDAO implements NewsDAO {
	List<News> newses = new ArrayList<News>();

	@Override
	public List<News> addNewses(int quantity) throws DAOException {
		try {
		List<News> requestedNews = new ArrayList<News>();
		//
		newses.add(new News("News 1", "news No. 1"));
		
		int totalNews = newses.size();
		// 
		if(quantity > totalNews ) {
			quantity = totalNews;
		}
		//
		for(int i = 0; i < quantity; i++) {
			requestedNews.add(newses.get(i));
		}

		return requestedNews;
		
		}catch (Exception e) {
			throw new DAOException();
		}
	}

}
