package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAddNewsPage implements Command {
	
	private static GoToAddNewsPage instance = new GoToAddNewsPage();

	public static final String PATH_ADD_NEWS_PAGE = "/WEB-INF/jsp/addNews.jsp";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_GUEST = "guest";
	public static final String SESSION_ATTR_PATH_COMMAND = "Go_To_Add_News_Page";
	public static final String SESSION_ATTR_PATH = "path";
	
	private GoToAddNewsPage() {}

	public static GoToAddNewsPage getInstance() {
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

		if (!ROLE_ADMIN.equals(user.getRole())) {
			response.sendRedirect(
					"Controller?command=Go_To_main_Page&messageErrorNoRights=You do not have access rights to perform this operation.");
			return;
		}
		
		request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_ADD_NEWS_PAGE);
		requestDispatcher.forward(request, response);

	}
}
