package by.htp.it.servise.impl;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.servise.NewsServise;
import by.htp.it.servise.ServiceException;
import by.htp.it.servise.exception.ServiseException;

public class NewsServiseImpl implements NewsServise {
	
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final NewsDAO NEWS_DAO = PROVIDER.getNewsDAO();

	@Override
	public List<News> getNewses(int quantity) throws ServiseException {
		try {
			return NEWS_DAO.getNewses(quantity);
		} catch (DAOException e) {
			throw new ServiseException();
		}
		
	}
	
	@Override
	public News read(News news) throws ServiceException {
		News newsForReading;
		try {
			newsForReading = NEWS_DAO.read(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException();
		}
		return newsForReading;
	}

	@Override
	public void add(News news) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(News news) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(News news) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
