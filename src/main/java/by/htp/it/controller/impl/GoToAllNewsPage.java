package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAllNewsPage implements Command {
	
	private static GoToAllNewsPage instance = new GoToAllNewsPage();

	public static final String PATH_ALL_NEWS_PAGE = "/WEB-INF/jsp/allNews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "Go_To_All_News_Page";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String ROLE_GUEST = "guest";
	
	private GoToAllNewsPage() {}

	public static GoToAllNewsPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(
					"Controller?command=Go_To_Authorization_Page&messageErrorNoSessionNoUser=Please, log in.");
			return;
		}

		User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);

		if (ROLE_GUEST.equals(user.getRole())) {
			response.sendRedirect(
					"Controller?command=Go_To_Authorization_Page&messageErrorNoSessionNoUser=Please, log in.");
			return;
		}

		request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_ALL_NEWS_PAGE);
		requestDispatcher.forward(request, response);

	}

}