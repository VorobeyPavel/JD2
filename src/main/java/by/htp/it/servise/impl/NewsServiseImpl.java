package by.htp.it.servise.impl;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.servise.NewsServise;
import by.htp.it.servise.exception.ServiseException;

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
