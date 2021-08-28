package by.htp.it.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
	static List<News> newses = new ArrayList<News>();

	@Override
	public List<News> addNewses(int quantity) throws DAOException {
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
	    	newses.add(new News(rs.getString(2), rs.getString(3))); 
	    }
	    	rs.close();
		    st.close();
		    	    
	    } catch (SQLException e) {
	    	throw new DAOException();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}
			    
	}
		
}
