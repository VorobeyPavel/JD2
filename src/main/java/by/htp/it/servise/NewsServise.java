package by.htp.it.servise;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.servise.exception.ServiseException;

public interface NewsServise {

	List<News> getNewses(int quantity) throws ServiseException;
	
	void add(News news) throws ServiceException;

	void update(News news) throws ServiceException;
	
	void delete (News news) throws ServiceException;
	
	News read (News news) throws ServiceException;
}
