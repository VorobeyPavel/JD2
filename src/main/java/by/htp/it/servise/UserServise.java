package by.htp.it.servise;



import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.servise.exception.ServiseException;

public interface UserServise {

	User registration(RegistrationInfo info) throws ServiseException;

	String validationReg(RegistrationInfo info) throws ServiseException;
	
	String validationAut(RegistrationInfo info) throws ServiseException;

	User authorization(RegistrationInfo info) throws ServiseException;

}
