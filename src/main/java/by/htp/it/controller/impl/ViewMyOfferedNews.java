package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

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

public class ViewMyOfferedNews implements Command {
	
	private static ViewMyOfferedNews instance = new ViewMyOfferedNews();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();

	//public static final String RESPONSE_ATTR_NEWS = "news";
	//public static final String PATH_OFFERED_NEWS_PAGE = "/WEB-INF/jsp/offeredNews.jsp";
	//public static final String PATH_AFTER_COMMAND_ERROR = "Controller?command=Go_To_Main_Page&responseCommand=Connection error. Try later.";
	
	public static final String SESSION_ATTRIBUTE_USER = "user";

	public static final String RESPONSE_ATTR_NEWS = "offeredNews";
	public static final String PATH_OFFERED_NEWS_PAGE = "/WEB-INF/jsp/offeredNews.jsp";
	public static final String PATH_AFTER_COMMAND_ERROR = "Controller?command=Go_To_Main_Page&responseCommand=Connection error. Try later.";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... "
			+ "Try again later.";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "view_my_offered_news";
	
	private ViewMyOfferedNews() {}

	public static ViewMyOfferedNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user;

		List<News> listOfOfferedNews;

		user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER);

		try {

			listOfOfferedNews = NEWS_SERVISE.viewMyOfferedNews(user);

			request.setAttribute(RESPONSE_ATTR_NEWS, listOfOfferedNews);
			
			request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_OFFERED_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			//.error("Database error during getting the list of users offered news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}
		
		
		

		/*
		 * try {
		 * 
		 * int idUser; List<News> listOfOfferedNews;
		 * 
		 * idUser = (int) request.getSession().getAttribute("idUser");
		 * 
		 * listOfOfferedNews = NEWS_SERVISE.viewMyOfferedNews(idUser);
		 * 
		 * request.setAttribute(RESPONSE_ATTR_NEWS, listOfOfferedNews);
		 * RequestDispatcher requestDispatcher =
		 * request.getRequestDispatcher(PATH_OFFERED_NEWS_PAGE);
		 * requestDispatcher.forward(request, response);
		 * 
		 * } catch (ServiceException e) { // log проблемы с доступом к БД // перевести
		 * на страницу ошибок, где есть ссылка на главную страницу
		 * response.sendRedirect(PATH_AFTER_COMMAND_ERROR); e.printStackTrace(); }
		 */

	}

}
