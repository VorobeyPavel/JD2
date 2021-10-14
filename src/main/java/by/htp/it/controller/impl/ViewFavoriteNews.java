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

public class ViewFavoriteNews implements Command {
	
	private static ViewFavoriteNews instance = new ViewFavoriteNews();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();

	//public static final String PATH_FAVORITE_NEWS_PAGE = "/WEB-INF/jsp/favoriteNews.jsp";
	//public static final String RESPONSE_ATTR_NEWS = "news";
	//public static final String SESSION_ATTR_PATH = "path";
	//public static final String SESSION_ATTR_PATH_COMMAND = "view_favorite_news";
	
	public static final String RESPONSE_ATTR_NEWS = "favoriteNews";
	public static final String SESSION_ATTRIBUTE_USER = "user";

	public static final String SESSION_ATTR_PATH_COMMAND = "view_favorite_news";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String PATH_FAVORITE_NEWS_PAGE = "/WEB-INF/jsp/favoriteNews.jsp";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";
	
	private ViewFavoriteNews() {}

	public static ViewFavoriteNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		User user;

		List<News> listOfFavoriteNews;

		user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER);
		
		System.out.println("проверка юзера");
		System.out.println(user.toString()+"ViewFavoriteNews");

		try {
			
			System.out.println("перед NEWS_SERVISE.viewFavoriteNews(user)");

			listOfFavoriteNews = NEWS_SERVISE.viewFavoriteNews(user);
			
			System.out.println(listOfFavoriteNews.toString());
			System.out.println("после NEWS_SERVISE.viewFavoriteNews(user)");

			request.setAttribute(RESPONSE_ATTR_NEWS, listOfFavoriteNews);

			request.getSession().setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_FAVORITE_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {

			//log.error("Database error during getting the list of favorite news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

		}
		
		
		
		/*
		 * try {
		 * 
		 * int idUser; List<News> listOfFavoriteNews;
		 * 
		 * idUser = (int) request.getSession().getAttribute("idUser");
		 * 
		 * listOfFavoriteNews = NEWS_SERVISE.viewFavoriteNews(idUser);
		 * 
		 * request.setAttribute(RESPONSE_ATTR_NEWS, listOfFavoriteNews);
		 * 
		 * request.getSession(true).setAttribute(SESSION_ATTR_PATH,
		 * SESSION_ATTR_PATH_COMMAND); RequestDispatcher requestDispatcher =
		 * request.getRequestDispatcher(PATH_FAVORITE_NEWS_PAGE);
		 * requestDispatcher.forward(request, response);
		 * 
		 * } catch (ServiceException e) { // log проблемы с доступом к БД // перевести
		 * на страницу ошибок, где есть ссылка на главную страницу
		 * 
		 * e.printStackTrace(); }
		 */

	}

}
