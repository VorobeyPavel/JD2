package by.htp.it.serviсe;

import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.serviсe.exception.ServiceException;


public interface NewsServiсe {

	List<News> getNewses(int quantity) throws ServiceException;
	
	void add(News news) throws ServiceException;

	void update(News news) throws ServiceException;
	
	void delete (News news) throws ServiceException;
	
	News read (News news) throws ServiceException;
	
	void addToFavorites(int idNews, int idUser) throws ServiceException;
	
	void deleteFromFavorites(int idNews, int idUser) throws ServiceException;
	
	boolean isFavorite(int idNews, int idUser) throws ServiceException;
	
	List<Comment> getCommentsForNews(int idNews) throws ServiceException;
	
	void addComment(Comment comment) throws ServiceException;
	
	List<News> viewFavoriteNews(int idUser) throws ServiceException;
	
	List<News> viewMyOfferedNews(int idUser) throws ServiceException;
	
	List<News> viewOfferedNews() throws ServiceException;
	
	List<News> readAllNews(int startNews, int endNews) throws ServiceException;
	
	int getNumberOfRecords() throws ServiceException;
	
	void doNotPublish(News newsDenyToPublish) throws ServiceException;

	News checkOfferedNews(News news) throws ServiceException;

	void goToPublish(News newsApprovePublication) throws ServiceException;
}
