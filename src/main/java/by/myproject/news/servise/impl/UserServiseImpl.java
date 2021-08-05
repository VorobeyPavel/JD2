package by.myproject.news.servise.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.dao.DAOProvider;
import by.myproject.news.dao.UserDAO;
import by.myproject.news.dao.exception.DAOException;
import by.myproject.news.servise.UserServise;
import by.myproject.news.servise.exception.ServiseException;


public class UserServiseImpl implements UserServise {
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final UserDAO USER_DAO = PROVIDER.getUserDAO();

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
		Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private static boolean checkPassword(String password) {
		Pattern pattern = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

}
