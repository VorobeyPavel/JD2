package by.myproject.news.dao;

import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.dao.exception.DAOException;


public interface UserDAO {

	User registration(RegistrationInfo info) throws DAOException;

	User authorization(RegistrationInfo info) throws DAOException;

}
