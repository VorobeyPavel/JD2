package by.htp.it.serviсe.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.exception.ServiceException;

public class NewsServiсeImpl implements NewsServiсe {
	
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final NewsDAO NEWS_DAO = PROVIDER.getNewsDAO();

	@Override
	public List<News> getNewses(int quantity) throws ServiceException {
		try {
			return NEWS_DAO.getNewses(quantity);
		} catch (DAOException e) {
			throw new ServiceException();
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
		try {
			NEWS_DAO.add(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void update(News news) throws ServiceException {
		try {
			NEWS_DAO.update(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void delete(News news) throws ServiceException {
		try {
			NEWS_DAO.delete(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void addToFavorites(int idNews, int idUser) throws ServiceException {
		try {
			
			System.out.println("NewsServiseImpl перед NEWS_DAO.addToFavorites(idNews, idUser);");
			
			NEWS_DAO.addToFavorites(idNews, idUser);
		} catch (DAOException e) {
			// log
			throw new ServiceException();
		}
	}

	@Override
	public void deleteFromFavorites(int idNews, int idUser) throws ServiceException {
		
		try {
			NEWS_DAO.deleteFromFavorites(idNews, idUser);
		} catch (DAOException e) {
			// log
			throw new ServiceException();
		}
		
	}

	@Override
	public boolean isFavorite(int idNews, int idUser) throws ServiceException {
		boolean isFavourite;

		try {
			isFavourite = NEWS_DAO.isFavorite(idNews, idUser);
		} catch (DAOException e) {
			// log
			throw new ServiceException();
		}
		return isFavourite;
	}

	@Override
	public List<Comment> getCommentsForNews(int idNews) throws ServiceException {
		List<Comment> commentsForNews = new ArrayList<>();

		try {
			commentsForNews = NEWS_DAO.getCommentsForNews(idNews);
		} catch (DAOException e) {
			// log
			throw new ServiceException();
		}
		return commentsForNews;
	}

	@Override
	public void addComment(Comment comment) throws ServiceException {
		// валидация
				try {
					NEWS_DAO.addComment(comment);
				} catch (DAOException e) {
					// log
					throw new ServiceException();
				}
		
	}

	@Override
	public List<News> viewFavoriteNews(int idUser) throws ServiceException {
		List<News> listOfFavoriteNews = new ArrayList<>();
		try {
			listOfFavoriteNews = NEWS_DAO.viewFavoriteNews(idUser);
		} catch (DAOException e) {
			// log
			throw new ServiceException();
		}

		return listOfFavoriteNews;
	}

	@Override
	public List<News> viewMyOfferedNews(int idUser) throws ServiceException {
		List<News> listOfOfferedNews = new ArrayList<>();
		try {
			listOfOfferedNews = NEWS_DAO.viewMyOfferedNews(idUser);
		} catch (DAOException e) {
			// log
			throw new ServiceException();
		}

		return listOfOfferedNews;
	}
	
	@Override
	public List<News> viewOfferedNews() throws ServiceException {
		
		List<News> listOfOfferedNews = new ArrayList<>();
		
		try {
			listOfOfferedNews = NEWS_DAO.viewOfferedNews();
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

		return listOfOfferedNews;
	}

	@Override
	public List<News> readAllNews(int startFrom, int limitRecords) throws ServiceException {
		List<News> listOfLimitAmountOfNews = new ArrayList<>();

		try {
			listOfLimitAmountOfNews = NEWS_DAO.readAllNews(startFrom, limitRecords);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		return listOfLimitAmountOfNews;
	}

	@Override
	public int getNumberOfRecords() throws ServiceException {
		int numberOfRecords;

		try {
			numberOfRecords = NEWS_DAO.getNumberOfRecords();
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}

		return numberOfRecords;
	}

	@Override
	public void doNotPublish(News newsDenyToPublish) throws ServiceException {
		try {
			NEWS_DAO.doNotPublish(newsDenyToPublish);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		
	}

	@Override
	public News checkOfferedNews(News news) throws ServiceException {
		
		News newsForChecking;
		
		try {
			newsForChecking = NEWS_DAO.checkOfferedNews(news);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		return newsForChecking;
	}

	@Override
	public void goToPublish(News newsApprovePublication) throws ServiceException {
		
		try {
			NEWS_DAO.goToPublish(newsApprovePublication);
		} catch (DAOException e) {
			// log
			throw new ServiceException(e);
		}
		
	}

}
