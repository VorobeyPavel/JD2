package by.myproject.news.controller.impl;

import java.io.IOException;

import by.myproject.news.bean.User;
import by.myproject.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AfterAuthorization implements Command{
	
	private static AfterAuthorization instance = new AfterAuthorization();
	
	public static final String SESSION_PATH = "path";
	public static final String PART_PATH = "Controller?command=";
	public static final String AUTHORIZATION_PAGE = "AUTHORIZATION_PAGE";
	public static final String SESSION_PATH_COMMAND = "AFTER_AUTHORIZATION";
	public static final String AFTER_AUTHORIZATION_PAGE = "/WEB-INF/jsp/AfterAuthorization.jsp";
	

	private AfterAuthorization() {}

	public static AfterAuthorization getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
				
		if(session == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect(PART_PATH + AUTHORIZATION_PAGE);
			return;
		}
		request.getSession(true).setAttribute(SESSION_PATH, SESSION_PATH_COMMAND);
		request.setAttribute("role", user.getRole());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(AFTER_AUTHORIZATION_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
