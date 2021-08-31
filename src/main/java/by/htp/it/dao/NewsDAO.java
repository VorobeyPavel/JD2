package by.htp.it.dao;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.dao.exception.DAOException;

public interface NewsDAO {
	
	List<News> getNewses(int quantity) throws DAOException;
	
	News read(News news) throws DAOException;
	
	void add(News news) throws DAOException;
	
	void update(News news) throws DAOException;
	
	void delete(News news) throws DAOException;
}
