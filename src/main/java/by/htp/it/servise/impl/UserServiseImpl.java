package by.htp.it.servise.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.UserDAO;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.servise.UserServise;
import by.htp.it.servise.exception.ServiseException;


public class UserServiseImpl implements UserServise {
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final UserDAO USER_DAO = PROVIDER.getUserDAO();
	
	public static final String PATTERN_EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	public static final String PATTERN_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

	@Override
	public User registration(RegistrationInfo info) throws ServiseException {
		try {
			return USER_DAO.registration(info);
		} catch (DAOException e) {
			throw new ServiseException();
		}

	}

	@Override
	public User authorization(RegistrationInfo info) throws ServiseException {
	
		try {
			return USER_DAO.authorization(info);
		} catch (DAOException e) {
			throw new ServiseException();
		}
		

	}

	@Override
	public String validation(RegistrationInfo info) throws ServiseException {
		List<String> notValidList = new ArrayList<String>();
		
		if (!checkPassword(info.getEnterPassword())) {
			notValidList.add("password");
		}
		
		if (!checkEmail(info.getEmail())) {
			notValidList.add("email");
		}
		
		StringBuilder notValidMessage = new StringBuilder();
		if(!notValidList.isEmpty()) {
			
			for(String mes : notValidList) {
				notValidMessage.append(mes);
				notValidMessage.append(" ");
			}
		}
		
		return notValidMessage.toString();

	}

	private static boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private static boolean checkPassword(String password) {
		Pattern pattern = Pattern.compile(PATTERN_PASSWORD);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

}
