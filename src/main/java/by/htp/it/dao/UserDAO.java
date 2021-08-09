package by.htp.it.dao;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.dao.exception.DAOException;


public interface UserDAO {

	User registration(RegistrationInfo info) throws DAOException;

	User authorization(RegistrationInfo info) throws DAOException;

}
