package by.myproject.news.servise.impl;

import java.util.List;

import by.myproject.news.bean.News;
import by.myproject.news.dao.DAOProvider;
import by.myproject.news.dao.NewsDAO;
import by.myproject.news.dao.exception.DAOException;
import by.myproject.news.servise.NewsServise;
import by.myproject.news.servise.exception.ServiseException;

public class NewsServiseImpl implements NewsServise {
	
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final NewsDAO NEWS_DAO = PROVIDER.getNewsDAO();

	@Override
	public List<News> addNewses(int quantity) throws ServiseException {
		try {
			return NEWS_DAO.addNewses(quantity);
		} catch (DAOException e) {
			throw new ServiseException();
		}
		
	}

}
