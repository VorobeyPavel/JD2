package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenProfile implements Command {
	
	private static OpenProfile instance = new OpenProfile();

	public static final String PATH_PROFILE_PAGE = "/WEB-INF/jsp/profile.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "Open_profile";
	
	private OpenProfile() {}

	public static OpenProfile getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_PROFILE_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
