package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.UserServiсe;
import by.htp.it.serviсe.exception.ServiceException;
import by.htp.it.controller.impl.RegistrationNewUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {

	private static final RegistrationNewUser instance = new RegistrationNewUser();
	
	private static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	private static final UserServiсe USER_SERVISE = PROVIDER.getUserServise();
	public static final String SESSION_PATH = "path";
	public static final String PART_PATH = "Controller?command=";
	public static final String PATH_COMMAND_REG = "REGISTRATION_PAGE";
	public static final String PATH_COMMAND_ERR = "UNKNOWN_COMMAND";
	public static final String PATH_COMMAND_AUT = "AUTHORIZATION_PAGE";

	private RegistrationNewUser() {
	}

	public static RegistrationNewUser getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = PART_PATH + PATH_COMMAND_REG;
		request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_REG);
		
		try {
			RegistrationInfo info = new RegistrationInfo(request);
			
			System.out.println("RegistrationInfo" + info.toString());
			
			String valid = USER_SERVISE.validationReg(info);
									
			if (valid.equals("false")) {
				path =  PART_PATH + PATH_COMMAND_REG + "&incorrect_data_message=Incorrect data was entered";
				response.sendRedirect(path);
				return;
			}
						
			User user = USER_SERVISE.registration(info);
						
			if(user == null) {
				path =  PART_PATH + PATH_COMMAND_REG + "&email_is_busy=The user with this Email is already registered";
				response.sendRedirect(path);
				return;
			}
			
			System.out.println("RegistrationNewUser  после USER_SERVISE.registration(info);" + user.toString());
			
			request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_AUT);
			path = PART_PATH + PATH_COMMAND_AUT + "&registration_message=Congratulations on registering, please log in";

		} catch (ServiceException e) {
			e.printStackTrace();
			request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_ERR);
			path = PART_PATH + PATH_COMMAND_ERR;
		}
		response.sendRedirect(path);
	}
}
