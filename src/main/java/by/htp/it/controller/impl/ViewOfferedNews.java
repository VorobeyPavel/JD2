package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewOfferedNews implements Command {
	
	private static ViewOfferedNews instance = new ViewOfferedNews();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	
	private static final Logger log = LogManager.getLogger(ViewOfferedNews.class);

	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "view_offered_news";
	public static final String RESPONSE_ATTR_NEWS = "offeredNews";
	public static final String PATH_OFFERED_NEWS_PAGE = "/WEB-INF/jsp/offeredNews.jsp";
	public static final String PATH_AFTER_COMMAND_ERROR = "Controller?command=Go_To_Main_Page&responseCommand=Connection error. Try later.";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_GUEST = "guest";
	
	private ViewOfferedNews() {}

	public static ViewOfferedNews getInstance() {
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
		
		System.out.println("ViewOfferedNews после преверки роли" );
		
		try {

			List<News> listOfOfferedNews;

			listOfOfferedNews = NEWS_SERVISE.viewOfferedNews();

			request.setAttribute(RESPONSE_ATTR_NEWS, listOfOfferedNews);
			
			request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_OFFERED_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error("Database error during getting the list of offered news.", e);
			response.sendRedirect(PATH_AFTER_COMMAND_ERROR);
		}
	}
}
