package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.servise.NewsServise;
import by.htp.it.servise.ServiseProvider;
import by.htp.it.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AfterAuthorization implements Command{
	
	private static AfterAuthorization instance = new AfterAuthorization();
	
	public static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	public static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	public static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	
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
		
		try {
			request.setAttribute("newses", NEWS_SERVISE.addNewses(10));
		} catch (ServiseException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}
				
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
