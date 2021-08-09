package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.servise.ServiseProvider;
import by.htp.it.servise.UserServise;
import by.htp.it.servise.exception.ServiseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {

	private static final RegistrationNewUser instance = new RegistrationNewUser();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
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

		RegistrationInfo info = new RegistrationInfo(request);
		
		String path = PART_PATH + PATH_COMMAND_REG;
		request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_REG);
		
		try {
		
			String valid = USER_SERVISE.validation(info);
			
			if (!valid.equals("") || !(info.getEnterPassword().equals(info.getRepeatPassword()))) {
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
					
			request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_AUT);
			path = PART_PATH + PATH_COMMAND_AUT + "&registration_message=Congratulations on registering, please log in";

		} catch (ServiseException e) {
			e.printStackTrace();
			request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_ERR);
			path = PART_PATH + PATH_COMMAND_ERR;
		}
		response.sendRedirect(path);
	}
}
