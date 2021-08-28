package by.htp.it.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.UserDAO;
import by.htp.it.dao.cp.ConnectionPool;
import by.htp.it.dao.exception.ConnectionPoolException;
import by.htp.it.dao.exception.DAOException;

public class SQLUserDAO implements UserDAO {
	
	/*
	 * public static final String URL =
	 * "jdbc:mysql://127.0.0.1/my_news?useSSL=false"; public static final String
	 * LOGIN = "root"; public static final String PASSWORD = "Pbg129634629937";
	 * public static final String DRIVER_PATH = "org.gjt.mm.mysql.Driver";
	 */
	
	private static final ConnectionPool CONN_POOL = ConnectionPool.getInstance();
	public static final String INSERT_INTO_USERS = "INSERT INTO users(name,surname,email,password,Date) VALUES(?,?,?,?,?)";
	public static final String SELECT_FROM_USER = "SELECT * FROM users";
	
	
	static Map <Integer, User> tableUsersMap = new HashMap<Integer, User>();

	@Override
	public User registration(RegistrationInfo info) throws DAOException {
		
		tableUsers();		
		System.out.println("регистрация после tableUsers()");		
		// 
		for(Map.Entry<Integer, User> mEntry : tableUsersMap.entrySet()) {
			if(info.getEmail().equals(mEntry.getValue().getEmail())) {
				return null;
			}
		}
				
		try(Connection con = CONN_POOL.takeConnection()) {
			
			String sql = INSERT_INTO_USERS;
			PreparedStatement ps = con.prepareStatement(sql);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			ps.setString(1, info.getName());
			ps.setString(2, info.getSurname());
			ps.setString(3, info.getEmail());
			ps.setString(4, info.getEnterPassword());
			ps.setString(5, sdf.format(date));	
			
			ps.executeUpdate();
			return new User(info.getEmail(),info.getEnterPassword());
			
		} catch (SQLException e1) {
			throw new DAOException();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}		
	}
	

	@Override
	public User authorization(RegistrationInfo info) throws DAOException {
		
		tableUsers();
		try {
			// 
			for(Map.Entry<Integer, User> mEntry :tableUsersMap.entrySet()) {
				if(info.getEmail().equals(mEntry.getValue().getEmail()) && info.getEnterPassword().equals(mEntry.getValue().getPassword())) {
					return mEntry.getValue();						
				}
			}
		} catch (Exception e) {
			throw new DAOException();
		}

		return null;
	}

	
	private static void tableUsers() throws DAOException{						
	    
		try (Connection con = CONN_POOL.takeConnection()){
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(SELECT_FROM_USER);	    

	    while (rs.next()) {
	    	tableUsersMap.put(rs.getInt(1), new User(rs.getString(2), rs.getString(3) ,rs.getString(4), rs.getString(5), rs.getString(6)));
	    }
	    	rs.close();
		    st.close();
		    		    	    
	    } catch (SQLException e) {
	    	throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} 
				    
	}

}
