package by.htp.it.dao;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.dao.exception.DAOException;

public interface NewsDAO {
	
	List<News> addNewses(int quantity) throws DAOException;
}
