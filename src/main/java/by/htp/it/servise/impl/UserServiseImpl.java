package by.htp.it.servise.impl;

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
	
	public static final String PATTERN_NAME = "^[a-zA-Z][a-zA-Z]{2,20}$";
	public static final String PATTERN_SURNAME = "^[a-zA-Z][a-zA-Z]{2,20}$";
	public static final String PATTERN_LOGIN = "^[a-zA-Z]{6,20}$";
	
	public static final String PATTERN_EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	public static final String PATTERN_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
	
	@Override
	public String validationReg(RegistrationInfo info) throws ServiseException {
		
		String validData="true";
		
		String name = info.getName();
		String email = info.getEmail();
		String enter_password = info.getEnterPassword();
		String repeat_password = info.getRepeatPassword();
		String surname = info.getSurname();
		
		if (!name.matches(PATTERN_NAME)) {
			validData = "false";
		}
		if (!email.matches(PATTERN_EMAIL)) {
			validData = "false";
		}
		if (!enter_password.matches(PATTERN_PASSWORD)) {
			validData = "false";
		}
		if (!enter_password.equals(repeat_password)) {
			validData = "false";
		}
		if (!surname.matches(PATTERN_SURNAME)) {
			validData = "false";
		}
		return validData;
	}
	
	
	@Override
	public String validationAut(RegistrationInfo info) throws ServiseException {
		
		String validData="true";
		String email = info.getEmail();
		String enter_password = info.getEnterPassword();
				
		if (!email.matches(PATTERN_EMAIL)) {
			validData = "false";
		}
		if (!enter_password.matches(PATTERN_PASSWORD)) {
			validData = "false";
		}
		return validData;
	}
	

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

}
