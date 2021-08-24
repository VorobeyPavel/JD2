package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.servise.ServiseProvider;
import by.htp.it.servise.UserServise;
import by.htp.it.servise.exception.ServiseException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AutorizationUser implements Command {

	private static AutorizationUser instance = new AutorizationUser();
	private static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	private static final UserServise USER_SERVISE = PROVIDER.getUserServise();
	public static final String SESSION_PATH = "path";
	public static final String PART_PATH = "Controller?command=";
	public static final String PATH_COMMAND_AUT = "AUTHORIZATION_PAGE";
	public static final String PATH_COMMAND_ERR = "UNKNOWN_COMMAND";
	public static final String PATH_COMMAND_AFT_AUT = "AFTER_AUTHORIZATION";
	
	private AutorizationUser() {
	}
	
	public static AutorizationUser getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = PART_PATH + PATH_COMMAND_AUT;
		RegistrationInfo info = new RegistrationInfo(request);
		request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_AUT);	
		
		try {
			
			String valid = USER_SERVISE.validationAut(info);
			  
			if(valid.equals("false")) { path = PART_PATH + PATH_COMMAND_AUT + "&incorrect_data_message=Incorrect data was entered:" + valid;
			response.sendRedirect(path); return; }
			 

			User user = USER_SERVISE.authorization(info);
			if (user == null) {
				path =  PART_PATH + PATH_COMMAND_AUT + "&user_not_found=There is no such user";
			} else {
							
				request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_AFT_AUT);
				request.getSession(true).setAttribute("user", user);
				path = PART_PATH + PATH_COMMAND_AFT_AUT;
				
			}
		} catch (ServiseException e) {
			// log
			request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_ERR);
			path = PART_PATH + PATH_COMMAND_ERR;
			
		} 
			
		response.sendRedirect(path);
	}
}
