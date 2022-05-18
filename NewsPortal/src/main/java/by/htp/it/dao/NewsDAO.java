package by.htp.it.dao;

import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.dao.exception.DAOException;

public interface NewsDAO {
	
	List<News> getNewses() throws DAOException;
	
	News read(News news) throws DAOException;
	
	void add(News news) throws DAOException;
	
	void update(News news) throws DAOException;
	
	void delete(News news) throws DAOException;
	
	void addToFavorites(int idNews, int idUser) throws DAOException;
	
	void deleteFromFavorites(int idNews, int idUser) throws DAOException;
	
	boolean isFavorite(int idNews, int idUser) throws DAOException;
	
	List<Comment> getCommentsForNews(int idNews) throws DAOException;
	
	void addComment(Comment comment) throws DAOException;
	
	List<News> viewFavoriteNews( User user) throws DAOException;
	
	List<News> viewMyOfferedNews(User user) throws DAOException;
	
	List<News> viewOfferedNews() throws DAOException;
	
	List<News> readAllNews(int startNews, int endNews) throws DAOException;
	
	int getNumberOfRecords() throws DAOException;
	
	void doNotPublish(News newsDenyToPublish) throws DAOException;

	News checkOfferedNews(News news) throws DAOException;

	void goToPublish(News newsApprovePublication) throws DAOException;
	
	void offerNews(News offeredNews) throws DAOException;
}
