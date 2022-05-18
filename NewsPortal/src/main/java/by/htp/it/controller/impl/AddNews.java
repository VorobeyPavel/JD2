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
import by.htp.it.serviсe.exception.ServiceExceptionValidationNews;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command{
	
	private static AddNews instance = new AddNews();
	
	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	public static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";		
	
	private static final Logger log = LogManager.getRootLogger();
	
	public static final String REQUEST_PARAM_TITLE = "title";
	public static final String REQUEST_PARAM_BRIEF = "brief";
	public static final String REQUEST_PARAM_CONTENT = "content";
	public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_GUEST = "guest";
	public static final String SESSION_PATH = "path";
	public static final String PATH_COMMAND_ADD_NEWS = "add_news";
	
	private AddNews() {
	}
	
	public static AddNews getInstance() {
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

		String title = (String) request.getParameter(REQUEST_PARAM_TITLE);
		String brief = (String) request.getParameter(REQUEST_PARAM_BRIEF);
		String content = (String) request.getParameter(REQUEST_PARAM_CONTENT);

		int idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);

		if (nullEmptyValidate(title, brief, content)) {
			response.sendRedirect("Controller?command=Go_To_add_news_Page&messageErrorEmpty=Fill all fields!!!");
			return;
		}

		News news = new News(title, brief, content, idUser);

		try {

			NEWS_SERVISE.add(news);
			request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_ADD_NEWS);
			response.sendRedirect("Controller?command=AFTER_AUTHORIZATION&responseCommandAddNews=News was added.");

		} catch (ServiceExceptionValidationNews e) {
			log.error("There are objectionable words in the news.", e);
			response.sendRedirect(
					"Controller?command=AFTER_AUTHORIZATION&responseCommandAddNewsErrorValidation=News was not added. There objectionable words for publication.");
		} catch (ServiceException e) {
			log.error( "Database error during adding the news.", e);
			response.sendRedirect(
					"Controller?command=AFTER_AUTHORIZATION&responseCommandServiceException=Something went wrong... Try again later.");

		}

	}

	private static boolean nullEmptyValidate(String title, String brief, String content) {

		if (title == null || title.isEmpty()) {
			return true;
		}

		if (brief == null || brief.isEmpty()) {
			return true;
		}

		if (content == null || content.isEmpty()) {
			return true;
		}

		return false;

	}
	
}
