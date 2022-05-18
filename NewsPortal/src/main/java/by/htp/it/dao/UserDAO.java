package by.htp.it.dao;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.exception.DAOException;
import by.htp.it.dao.exception.DAOExceptionInvalidPassword;


public interface UserDAO {

	User registration(RegistrationInfo info) throws DAOException;

	User authorization(RegistrationInfo info) throws DAOException;
	
	void changePassword(User user, RegistrationInfo info) throws DAOException, DAOExceptionInvalidPassword;

}
