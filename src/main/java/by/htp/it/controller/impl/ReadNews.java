package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.servise.NewsServise;
import by.htp.it.servise.ServiceException;
import by.htp.it.servise.ServiseProvider;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReadNews implements Command {
	
	private static ReadNews instance = new ReadNews();
	
	public static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	public static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	

	public static final String READ_NEWS_PAGE = "/WEB-INF/jsp/readNews.jsp";
	public static final String PATH_AFTER_ERROR_NO_SESSION_NO_USER_NO_RIGHTS = "Controller?command=Go_To_Authorization_Page&messageErrorNews=Please, log in.";
	public static final String SESSION_ATTR_USER = "user";
	public static final String PARAM_GUEST = "guest";
	public static final String REQUEST_PARAM_ID = "id";
	public static final String REQUEST_ATTR_TITLE = "title";
	public static final String REQUEST_ATTR_CONTENT = "content";
	
	private ReadNews() {}

	public static ReadNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(PATH_AFTER_ERROR_NO_SESSION_NO_USER_NO_RIGHTS);
			return;
		}

		User user = (User) session.getAttribute(SESSION_ATTR_USER);

		//  user создается при входе с ролью guest
		if (user == null) {
			response.sendRedirect(PATH_AFTER_ERROR_NO_SESSION_NO_USER_NO_RIGHTS);
			return;
		}

		if (PARAM_GUEST.equals(user.getRole())) {
			session.removeAttribute(SESSION_ATTR_USER);
			response.sendRedirect(PATH_AFTER_ERROR_NO_SESSION_NO_USER_NO_RIGHTS);
			return;
		}

		int id;
		News news;

		id = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID));
		news = new News(id);

		try {

			News newsForReading;

			newsForReading = NEWS_SERVISE.read(news);

			request.setAttribute(REQUEST_ATTR_TITLE, request.getParameter(REQUEST_ATTR_TITLE));
			request.setAttribute(REQUEST_ATTR_CONTENT, newsForReading.getContent());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(READ_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
