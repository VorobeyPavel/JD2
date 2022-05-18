package by.htp.it.controller.impl;

import java.io.IOException;

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

public class GoToEditNewsPage implements Command {
	
	private static final GoToEditNewsPage instance = new GoToEditNewsPage();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	
	private static final Logger log = LogManager.getLogger(GoToEditNewsPage.class);

	public static final String SESSION_ATTR_PATH_COMMAND = "go_to_edit_news_page&idNews=";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String PATH_EDIT_NEWS_PAGE = "/WEB-INF/jsp/editNews.jsp";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_GUEST = "guest";
	public static final String REQUEST_PARAM_ID_NEWS = "idNews";
	public static final String REQUEST_ATTR_TITLE = "title";
	public static final String REQUEST_ATTR_BRIEF = "brief";
	public static final String REQUEST_ATTR_CONTENT = "content";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";
	
	public static GoToEditNewsPage getInstance() {
		return instance;
	}
	
	private GoToEditNewsPage() {}

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
		
		System.out.println("GoToEditNewsPage после проверки роли на админа");

		int idNews;
		News news;

		idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID_NEWS));
		news = new News(idNews);
		
		System.out.println("номер новости для редактирования" + idNews );

		try {

			News newsForUpdating;
			newsForUpdating = NEWS_SERVISE.read(news);

			request.setAttribute(REQUEST_PARAM_ID_NEWS, newsForUpdating.getId());
			request.setAttribute(REQUEST_ATTR_TITLE, newsForUpdating.getTitle());
			request.setAttribute(REQUEST_ATTR_BRIEF, newsForUpdating.getBrief());
			request.setAttribute(REQUEST_ATTR_CONTENT, newsForUpdating.getContent());

			session.setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND + idNews);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_EDIT_NEWS_PAGE);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			log.error("Database error during editing a news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);
		}

	}

}
