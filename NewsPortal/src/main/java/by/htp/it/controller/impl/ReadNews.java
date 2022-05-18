package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.Comment;
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

public class ReadNews implements Command {
	
	//private static ReadNews instance = new ReadNews();
	
	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	
	private static final Logger log = LogManager.getLogger(ReadNews.class);

	public static final String READ_NEWS_PAGE = "/WEB-INF/jsp/readNews.jsp";
	public static final String PATH_AFTER_ERROR_NO_SESSION_NO_USER_NO_RIGHTS = "Controller?command=Go_To_Authorization_Page&messageErrorNews=Please, log in.";
	public static final String SESSION_ATTR_USER = "user";
	public static final String PARAM_GUEST = "guest";
	public static final String REQUEST_PARAM_ID = "idNews";
	public static final String REQUEST_ATTR_ID = "idNews";
	public static final String REQUEST_ATTR_TITLE = "title";
	public static final String REQUEST_ATTR_CONTENT = "content";
	public static final String SESSION_ATTRIBUTE_USER_ID = "idUser";
	public static final String REQUEST_ATTR_COMMENTS = "comments";
	public static final String IS_FAVOURITE = "isFavourite";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";
	
	/*
	 * private ReadNews() {}
	 * 
	 * public static ReadNews getInstance() { return instance; }
	 */

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

		int idNews;
		News news;

		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID));
		news = new News(idNews);

		try {

			News newsForReading;
			List<Comment> comments;
			boolean isFavourite;
			int idUser;

			idUser = (int) session.getAttribute(SESSION_ATTRIBUTE_USER_ID);
			System.out.println(idUser);
			newsForReading = NEWS_SERVISE.read(news);
			isFavourite = NEWS_SERVISE.isFavorite(idNews, idUser);
			comments = NEWS_SERVISE.getCommentsForNews(idNews);
						
			request.setAttribute(REQUEST_ATTR_ID, idNews);
			request.setAttribute(REQUEST_ATTR_TITLE, request.getParameter(REQUEST_ATTR_TITLE));
			
			//request.setAttribute(REQUEST_ATTR_TITLE, newsForReading.getTitle());
			request.setAttribute(REQUEST_ATTR_CONTENT, newsForReading.getContent());
			request.setAttribute(REQUEST_ATTR_COMMENTS, comments);
			request.setAttribute(IS_FAVOURITE, isFavourite);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(READ_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error("Database error during getting the news for reading.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);
		}

	}

}


