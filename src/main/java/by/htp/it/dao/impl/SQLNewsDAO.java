package by.htp.it.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.Comment;
import by.htp.it.bean.News;
import by.htp.it.bean.NewsStatus;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.cp.ConnectionPool;
import by.htp.it.dao.exception.ConnectionPoolException;
import by.htp.it.dao.exception.DAOException;

public class SQLNewsDAO implements NewsDAO {
	
	private static final ConnectionPool CONN_POOL = ConnectionPool.getInstance();
	
	public static final String ADD_NEWS_INSERT_INTO = "INSERT INTO news_my(title,brief,content,date,id_user,status) VALUES(?,?,?,?,?,?)";
	public static final String UPDATE_NEWS = "UPDATE news_my SET title = ?, brief = ?, content = ? WHERE id = ?";
	public static final String DELETE_NEWS_ID = "DELETE FROM news_my WHERE id = ?";
	public static final String SELECT_FROM_NEWS = "SELECT * FROM news_my";
	public static final String READ_NEWS_SELECT = "SELECT * FROM news_my WHERE id = ?";
	public static final String EMPTY_CONTENT = "";
	public static final String ADD_NEWS_TO_FAVOURITES_INSERT_INTO = "INSERT INTO favourite_news (idfavourite_news,id_user) VALUES(?,?)";
	public static final String DELETE_FROM_FAVOURITES = "DELETE FROM favourite_news WHERE idfavourite_news = ? AND id_user = ?";
	public static final String IS_FAVOURITE_NEWS = "SELECT * FROM favourite_news WHERE idfavourite_news=? AND id_user=?";
	public static final String COMMENTS_FOR_NEWS = "SELECT * FROM comments WHERE id_news = ? ORDER BY date DESC LIMIT 7";
	public static final String ADD_COMMENT_INSERT_INTO = "INSERT INTO comments(id_news,id_user,comment,date) VALUES(?,?,?,?)";
	public static final String DENY_TO_PUBLISH = "UPDATE news_my SET status = 'denied_publication' WHERE id = ?";
	public static final String APPROVE_PUBLICATION = "UPDATE news_my SET status = 'published' WHERE id = ?";
	public static final String LIST_OF_FAVORITE_NEWS = "SELECT * FROM news, favourite_news WHERE favourite_news.id_user = ? AND news.id=favourite_news.idfavourite_news";
	public static final String LIST_OF_OFFERED_NEWS = "SELECT * FROM news_my WHERE status IN('on_approval','ON_APPROVAL')";
	public static final String LIST_OF_USER_OFFERED_NEWS = "SELECT * FROM news WHERE id_user = ?";
	public static final String LIST_OF_ALL_NEWS_SELECT = "SELECT * FROM news_my WHERE status = 'published' LIMIT ";
	public static final String COUNT_AMOUNT_OF_ALL_NEWS = "SELECT * FROM news_my WHERE status = 'published'";
	public static final String COMMA = " ,";
	
	static List<News> newses = new ArrayList<News>();
	
	@Override
	public List<News> getNewses(int quantity) throws DAOException {
		try {

			List<News> requestedNews = new ArrayList<News>();

			tableNewses();

			int totalNews = newses.size();
			
			if (quantity > totalNews) {
				quantity = totalNews;
			}
			
			for (int i = 0; i < quantity; i++) {
				requestedNews.add(newses.get(i));
			}
			return requestedNews;

		} catch (DAOException e) {
			throw new DAOException(e);
		}
	}
	
	
	private static void tableNewses() throws DAOException {
				
		Statement st=null;
		ResultSet rs=null;

		try (Connection con = CONN_POOL.takeConnection()) {
			st = con.createStatement();
			rs = st.executeQuery(SELECT_FROM_NEWS);

			while (rs.next()) {
				newses.add(new News(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3)));
			}
			//rs.close();
			//st.close();

		} catch (SQLException e) {
			throw new DAOException();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}

			} catch (SQLException e) {
				// log
				throw new DAOException(e);
			}
		}

	}


	@Override
	public News read(News news) throws DAOException {
		News newsForReading = null;

		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(READ_NEWS_SELECT)) {

			ps.setInt(1, news.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int idNews = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);
				String content = rs.getString(4);
				Date date = rs.getDate(5);
				newsForReading = new News(idNews, title, brief, content, date);

			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new DAOException(e);
		}

		return newsForReading;
	}


	@Override
	public void add(News news) throws DAOException {
		
		try (Connection con = CONN_POOL.takeConnection();
				PreparedStatement ps = con.prepareStatement(ADD_NEWS_INSERT_INTO)) {

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.setInt(5, news.getIdUser());
			ps.setString(6, "published");
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}
		
	}


	@Override
	public void update(News news) throws DAOException {
		
		try (Connection con = CONN_POOL.takeConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_NEWS)) {

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setInt(4, news.getId());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}

		
	}


	@Override
	public void delete(News news) throws DAOException {
		
		try (Connection con = CONN_POOL.takeConnection(); PreparedStatement ps = con.prepareStatement(DELETE_NEWS_ID)) {

			ps.setInt(1, news.getId());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}
		
	}


	@Override
	public void addToFavorites(int idNews, int idUser) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		System.out.println("SQLNewsDAO перед con = CONN_POOL.takeConnection();");

		try {

			con = CONN_POOL.takeConnection();

			ps = con.prepareStatement(ADD_NEWS_TO_FAVOURITES_INSERT_INTO);
			ps.setInt(1, idNews);
			ps.setInt(2, idUser);
			ps.executeUpdate();

			System.out.println("SQLNewsDAO после ADD_NEWS_TO_FAVOURITES_INSERT_INTO");
			
		} catch (SQLException e) {
			// log
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		} finally {
			try {

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// log
				throw new DAOException(e);

			}
		}
		
	}


	@Override
	public void deleteFromFavorites(int idNews, int idUser) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = CONN_POOL.takeConnection();

			ps = con.prepareStatement(DELETE_FROM_FAVOURITES);
			ps.setInt(1, idNews);
			ps.setInt(2, idUser);
			ps.executeUpdate();

		} catch (SQLException e) {
			// log
			throw new DAOException(e);

		} catch (ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		} finally {
			try {

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// log
				throw new DAOException(e);
			}
		}
	}


	@Override
	public boolean isFavorite(int idNews, int idUser) throws DAOException {
		boolean isFavourite = false;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = CONN_POOL.takeConnection();

			ps = con.prepareStatement(IS_FAVOURITE_NEWS);
			ps.setInt(1, idNews);
			ps.setInt(2, idUser);
			rs = ps.executeQuery();

			while (rs.next()) {
				isFavourite = true;
			}
		} catch (SQLException e) {
			// log
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// log
				throw new DAOException(e);

			}
		}

		return isFavourite;
	}


	@Override
	public List<Comment> getCommentsForNews(int idNews) throws DAOException {

		List<Comment> commentsForNews = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = CONN_POOL.takeConnection();
			ps = con.prepareStatement(COMMENTS_FOR_NEWS);
			ps.setInt(1, idNews);
			rs = ps.executeQuery();

			while (rs.next()) {

				String content = rs.getString(4);
				Date date = rs.getDate(5);

				Comment comment = new Comment(content, date);

				commentsForNews.add(comment);

			}
		} catch (SQLException e) {
			// log
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			// log
			throw new DAOException(e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				throw new DAOException(e);

			}
		}

		return commentsForNews;
	}


	@Override
	public void addComment(Comment comment) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = CONN_POOL.takeConnection();

			ps = con.prepareStatement(ADD_COMMENT_INSERT_INTO);
			ps.setInt(1, comment.getId_news());
			ps.setInt(2, comment.getId_user());
			ps.setString(3, comment.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.executeUpdate();

		} catch (SQLException e) {
			// log
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		} finally {
			try {

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// log
				throw new DAOException(e);

			}
		}
	}


	@Override
	public List<News> viewFavoriteNews(int idUser) throws DAOException {
		List<News> listOfFavoriteNews = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = CONN_POOL.takeConnection();
			ps = con.prepareStatement(LIST_OF_FAVORITE_NEWS);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String brief = rs.getString(3);

				News news = new News(id, title, brief);
				listOfFavoriteNews.add(news);

			}
		} catch (SQLException e) {
			// log
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			// log
			throw new DAOException(e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				throw new DAOException(e);

			}
		}

		return listOfFavoriteNews;
	}


	@Override
	public List<News> viewMyOfferedNews(int idUser) throws DAOException {
		List<News> listOfOfferedNews = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = CONN_POOL.takeConnection();
			ps = con.prepareStatement(LIST_OF_USER_OFFERED_NEWS);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();

			while (rs.next()) {

				int idOfferedNews = rs.getInt(1);
				String title = rs.getString(3);
				String brief = rs.getString(4);
				String status = rs.getString(7);

				status = status.toUpperCase();
				News news = new News(idOfferedNews, title, brief, NewsStatus.valueOf(status));

				listOfOfferedNews.add(news);

			}
		} catch (SQLException e) {
			// log
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			// log
			throw new DAOException(e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				throw new DAOException(e);

			}
		}

		return listOfOfferedNews;
	}
	
	
	@Override
	public List<News> viewOfferedNews() throws DAOException {
		List<News> listOfOfferedNews = new ArrayList<>();

		try (Connection con = CONN_POOL.takeConnection()) {

			try (PreparedStatement ps = con.prepareStatement(LIST_OF_OFFERED_NEWS)) {

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {

						int idOfferedNews = rs.getInt(1);
						String title = rs.getString(2);
						String brief = rs.getString(3);
						String status = rs.getString(7);

						status = status.toUpperCase();
						News news = new News(idOfferedNews, title, brief, NewsStatus.valueOf(status));

						listOfOfferedNews.add(news);
					}
				}
			}

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);

		}
		return listOfOfferedNews;
	}


	@Override
	public List<News> readAllNews(int startFrom, int limitRecords) throws DAOException {
		List<News> listOfLimitAmountOfNews = new ArrayList<>();
		
		System.out.println("DAO начало");

		String query = LIST_OF_ALL_NEWS_SELECT + startFrom + COMMA + limitRecords;

		try (Connection con = CONN_POOL.takeConnection()) {

			try (PreparedStatement ps = con.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {

						int id = rs.getInt(1);
						String title = rs.getString(2);
						String brief = rs.getString(3);

						News news = new News(id, title, brief);
						listOfLimitAmountOfNews.add(news);

					}
				}
			}

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}

		return listOfLimitAmountOfNews;
	}


	@Override
	public int getNumberOfRecords() throws DAOException {
		int numberOfRecords = 0;

		try (Connection con = CONN_POOL.takeConnection()) {

			try (PreparedStatement ps = con.prepareStatement(COUNT_AMOUNT_OF_ALL_NEWS)) {

				try (ResultSet rs = ps.executeQuery()) {

					rs.last();
					numberOfRecords = rs.getRow();

				}
			}

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);
		}

		return numberOfRecords;
	}


	@Override
	public void doNotPublish(News newsDenyToPublish) throws DAOException {
		
		try (Connection con = CONN_POOL.takeConnection()) {

			try (PreparedStatement ps = con.prepareStatement(DENY_TO_PUBLISH)) {

				ps.setInt(1, newsDenyToPublish.getId());
				ps.executeUpdate();

			}

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);

		}
		
	}


	@Override
	public News checkOfferedNews(News news) throws DAOException {
		News newsForChecking = null;

		try (Connection con = CONN_POOL.takeConnection()) {

			try (PreparedStatement ps = con.prepareStatement(READ_NEWS_SELECT)) {

				ps.setInt(1, news.getId());

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {

						String title = rs.getString(2);
						String brief = rs.getString(3);
						String content = rs.getString(4);

						newsForChecking = new News(news.getId(), title, brief, content);
					}
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);

		}

		return newsForChecking;
	}


	@Override
	public void goToPublish(News newsApprovePublication) throws DAOException {
		
		try (Connection con = CONN_POOL.takeConnection()) {

			try (PreparedStatement ps = con.prepareStatement(APPROVE_PUBLICATION)) {

				ps.setInt(1, newsApprovePublication.getId());

				ps.executeUpdate();

			}

		} catch (SQLException | ConnectionPoolException e) {
			// log
			throw new DAOException(e);

		}
		
	}

		
}
