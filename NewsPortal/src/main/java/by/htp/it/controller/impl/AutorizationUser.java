package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.RegistrationInfo;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.UserServiсe;
import by.htp.it.serviсe.exception.ServiceException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AutorizationUser implements Command {

	private static AutorizationUser instance = new AutorizationUser();
	
	private static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	private static final UserServiсe USER_SERVISE = PROVIDER.getUserServise();
	
	private static final Logger log = LogManager.getLogger(AutorizationUser.class);

	public static final String SESSION_PATH = "path";
	public static final String PART_PATH = "Controller?command=";
	public static final String PATH_COMMAND_AUT = "AUTHORIZATION_PAGE";
	public static final String PATH_COMMAND_ERR = "UNKNOWN_COMMAND";
	public static final String PATH_COMMAND_AFT_AUT = "AFTER_AUTHORIZATION";
	
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String SESSION_ATTRIBUTE_USER_ID = "idUser";
	
	private AutorizationUser() {
	}
	
	public static AutorizationUser getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = PART_PATH + PATH_COMMAND_AUT;
		RegistrationInfo info = new RegistrationInfo(request);
		request.getSession().setAttribute(SESSION_PATH, PATH_COMMAND_AUT);	
		
		try {
			
			String valid = USER_SERVISE.validationAut(info);
			  
			if(valid.equals("false")) { 
				path = PART_PATH + PATH_COMMAND_AUT + "&incorrect_data_message=Incorrect data was entered:" + valid;
				response.sendRedirect(path); 
				return; 
			}
			
			System.out.println("AutorizationUser"+ valid);
			
			User user = USER_SERVISE.authorization(info);
					
			if (user == null) {
				path =  PART_PATH + PATH_COMMAND_AUT + "&user_not_found=There is no such user";
			} else {	
				
				HttpSession session = request.getSession(true);
				session.setAttribute(SESSION_ATTRIBUTE_USER, user);
				session.setAttribute(SESSION_ATTRIBUTE_USER_ID, user.getId());
				session.setAttribute(SESSION_PATH, PATH_COMMAND_AFT_AUT);
				path = PART_PATH + PATH_COMMAND_AFT_AUT;
				
			}
		} catch (ServiceException e) {
			log.error("Database error during authorization.", e);
			request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_ERR);
			//path = PART_PATH + PATH_COMMAND_ERR;
			
		} 
			
		response.sendRedirect(path);
	}
}
