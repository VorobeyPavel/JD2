package by.htp.it.serviсe;



import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.serviсe.exception.ServiceException;
import by.htp.it.serviсe.exception.ServiceExceptionInvalidPassword;
import by.htp.it.serviсe.exception.ServiceExceptionValidationPassword;

public interface UserServiсe {

	User registration(RegistrationInfo info) throws ServiceException;

	String validationReg(RegistrationInfo info) throws ServiceException;
	
	String validationAut(RegistrationInfo info) throws ServiceException;

	User authorization(RegistrationInfo info) throws ServiceException;
	
	void changePassword(User user, RegistrationInfo info) throws ServiceException, ServiceExceptionInvalidPassword, ServiceExceptionValidationPassword;

}
