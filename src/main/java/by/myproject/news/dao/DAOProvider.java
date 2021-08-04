package by.myproject.news.dao;

import by.myproject.news.dao.impl.SQLNewsDAO;
import by.myproject.news.dao.impl.SQLUserDAO;

public class DAOProvider {
private static final DAOProvider INSTANCE = new DAOProvider();
	
	private UserDAO userDAO = new SQLUserDAO();
	private NewsDAO newsDAO = new SQLNewsDAO();
	
	private DAOProvider() {
		
	}
	
	public static DAOProvider getInstance() {
		return INSTANCE;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}
}
