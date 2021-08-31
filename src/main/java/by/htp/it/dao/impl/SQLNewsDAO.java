package by.htp.it.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.htp.it.bean.News;
import by.htp.it.dao.NewsDAO;
import by.htp.it.dao.cp.ConnectionPool;
import by.htp.it.dao.exception.ConnectionPoolException;
import by.htp.it.dao.exception.DAOException;

public class SQLNewsDAO implements NewsDAO {
	
	/*
	 * public static final String URL =
	 * "jdbc:mysql://127.0.0.1/my_news?useSSL=false"; public static final String
	 * LOGIN = "root"; public static final String PASSWORD = "Pbg129634629937";
	 * public static final String DRIVER_PATH = "org.gjt.mm.mysql.Driver";
	 */
	
	private static final ConnectionPool CONN_POOL = ConnectionPool.getInstance();
	public static final String SELECT_FROM_NEWS = "SELECT * FROM news";
	public static final String READ_NEWS_SELECT = "SELECT * FROM news WHERE id = ?";
	public static final String EMPTY_CONTENT = "";
	
	static List<News> newses = new ArrayList<News>();

	@Override
	public List<News> getNewses(int quantity) throws DAOException {
		try {
		
		List<News> requestedNews = new ArrayList<News>();
		
		tableNewses();
		
		int totalNews = newses.size();
		// 
		if(quantity > totalNews ) {
			quantity = totalNews;
		}
		//
		for(int i = 0; i < quantity; i++) {
			requestedNews.add(newses.get(i));
		}
		return requestedNews;
				
		}catch (Exception e) {
			throw new DAOException();
		}
	}
	
	
	private static void tableNewses() throws DAOException {		
					    
		try (Connection con = CONN_POOL.takeConnection()){
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(SELECT_FROM_NEWS);
	    
	    while (rs.next()) {
	    	newses.add(new News(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3))); 
	    }
	    	rs.close();
		    st.close();
		    	    
	    } catch (SQLException e) {
	    	throw new DAOException();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}
			    
	}


	@Override
	public News read(News news) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = READ_NEWS_SELECT;
		String content = EMPTY_CONTENT;

		try {

			con = CONN_POOL.takeConnection();

			ps = con.prepareStatement(sql);
			ps.setInt(1, news.getId());

			rs = ps.executeQuery();

			while (rs.next()) {

				content = rs.getString(4);

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

		News newsForReading = new News(content);
		return newsForReading;
	}


	@Override
	public void add(News news) throws DAOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(News news) throws DAOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(News news) throws DAOException {
		// TODO Auto-generated method stub
		
	}
		
}
