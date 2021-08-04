package by.myproject.news.dao;

import java.util.List;


import by.myproject.news.bean.News;
import by.myproject.news.dao.exception.DAOException;

public interface NewsDAO {
	
	List<News> addNewses(int quantity) throws DAOException;
}
