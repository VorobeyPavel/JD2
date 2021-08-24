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
import by.htp.it.dao.exception.DAOException;

public class SQLNewsDAO implements NewsDAO {
	
	public static final String URL = "jdbc:mysql://127.0.0.1/my_news?useSSL=false";
	public static final String LOGIN = "root";
	public static final String PASSWORD = "Pbg129634629937";
	public static final String DRIVER_PATH = "org.gjt.mm.mysql.Driver";
	
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
		
		try {
			Class.forName(DRIVER_PATH);
		} catch (ClassNotFoundException e) {
			throw new DAOException();
		}
		
		System.out.println("NEWSDAO Class.forName(DRIVER_PATH);");
	    
		try (Connection con = DriverManager.getConnection(URL, LOGIN, PASSWORD)){
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("SELECT * FROM news");
	    
	    System.out.println("NEWSDAO while");

	    while (rs.next()) {
	    	newses.add(new News(rs.getString(2), rs.getString(3))); 
	    }

	    	rs.close();
		    st.close();
		    	    
	    } catch (SQLException e) {
	    	throw new DAOException();
		}
			    
	}
		
}
