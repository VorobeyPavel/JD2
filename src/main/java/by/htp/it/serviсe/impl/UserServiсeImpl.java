package by.htp.it.serviсe.impl;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.DAOProvider;
import by.htp.it.dao.UserDAO;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.dao.exception.DAOExceptionInvalidPassword;
import by.htp.it.serviсe.UserServiсe;
import by.htp.it.serviсe.exception.ServiceException;
import by.htp.it.serviсe.exception.ServiceExceptionInvalidPassword;
import by.htp.it.serviсe.exception.ServiceExceptionValidationPassword;


public class UserServiсeImpl implements UserServiсe {
	private static final DAOProvider PROVIDER = DAOProvider.getInstance();
	private static final UserDAO USER_DAO = PROVIDER.getUserDAO();
	
	public static final String PATTERN_NAME = "^[a-zA-Z][a-zA-Z]{2,20}$";
	public static final String PATTERN_SURNAME = "^[a-zA-Z][a-zA-Z]{2,20}$";
	public static final String PATTERN_LOGIN = "^[a-zA-Z]{6,20}$";
	public static final String PATTERN_EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	public static final String PATTERN_PASSWORD = "((?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,})";
	
	// для имени/фамилии
		// латинскими от 2 до 20 символов

		// для логина
		// -от 6 до 20 (латинских символов)

		// пароль содержит
		// - хотя бы одно число;
		// - хотя бы один спецсимвол [!@#$%^&*];
		// - хотя бы одну латинскую букву в нижнем регистре;
		// - хотя бы одну латинскую букву в верхнем регистре;
		// - состоит не менее, чем из 8 вышеупомянутых символов.
	
	@Override
	public String validationReg(RegistrationInfo info) throws ServiceException {
		
		String validData="true";
		
		String name = info.getName();
		String email = info.getEmail();
		String enter_password = info.getEnter_password();
		String repeat_password = info.getRepeat_password();
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
	public String validationAut(RegistrationInfo info) throws ServiceException {
		
		String validData="true";
		String email = info.getEmail();
		String enter_password = info.getEnter_password();
				
		if (!email.matches(PATTERN_EMAIL)) {
			validData = "false";
		}
		if (!enter_password.matches(PATTERN_PASSWORD)) {
			validData = "false";
		}
		return validData;
	}
	

	@Override
	public User registration(RegistrationInfo info) throws ServiceException {
		try {
			return USER_DAO.registration(info);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	
	@Override
	public User authorization(RegistrationInfo info) throws ServiceException {
	
		try {
			return USER_DAO.authorization(info);
		} catch (DAOException e) {
			throw new ServiceException();
		}		
	}


	@Override
	public void changePassword(User user, RegistrationInfo info)
			throws ServiceException, ServiceExceptionInvalidPassword, ServiceExceptionValidationPassword {
		
		validateByChangingPassword(info);
		
		try {
			USER_DAO.changePassword(user, info);
		} catch (DAOExceptionInvalidPassword e) {

			throw new ServiceExceptionInvalidPassword(e);
		} catch (DAOException e) {

			throw new ServiceException(e);
		}
		
	}
	
	public void validateByChangingPassword(RegistrationInfo info) throws ServiceExceptionValidationPassword {

		if (!info.getNewPassword().matches(PATTERN_PASSWORD)) {
			throw new ServiceExceptionValidationPassword("New password does not meet security requirements.");
		}
		System.out.println("validateByChangingPassword");
		System.out.println(info.getNewPassword());

	}

}
