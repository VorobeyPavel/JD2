package by.myproject.news.servise;



import by.myproject.news.bean.RegistrationInfo;
import by.myproject.news.bean.User;
import by.myproject.news.servise.exception.ServiseException;

public interface UserServise {

	User registration(RegistrationInfo info) throws ServiseException;

	String validation(RegistrationInfo info) throws ServiseException;

	User authorization(RegistrationInfo info) throws ServiseException;

}
