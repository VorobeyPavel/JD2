package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import by.htp.it.bean.News;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import by.htp.it.serviсe.exception.ServiceExceptionValidationNews;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditNews implements Command {
	
	private static EditNews instance = new EditNews();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	
	private static final Logger log = LogManager.getLogger(EditNews.class);

	public static final String REQUEST_PARAM_ID = "idNews";
	public static final String REQUEST_PARAM_TITLE = "title";
	public static final String REQUEST_PARAM_BRIEF = "brief";
	public static final String REQUEST_PARAM_CONTENT = "content";

	public static final String PATH_EMPTY_PARAMETER = "Controller?command=go_to_edit_news_page&messageErrorEmpty=Fill all fields!!!&idNews=";
	public static final String PATH_AFTER_EDIT_NEWS = "Controller?command=AFTER_AUTHORIZATION&responseCommandNewsEditOk=News was edited.";
	public static final String PATH_AFTER_VALIDATION_EXCEPTION = "Controller?command=Go_To_Main_Page&messageErrorValidationEditNews=News was not edited. There objectionable words for publication.";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";
	
	private EditNews() {}

	public static EditNews getInstance() {
		return instance;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		News news;

		String title;
		String brief;
		String content;

		title = request.getParameter(REQUEST_PARAM_TITLE);
		brief = request.getParameter(REQUEST_PARAM_BRIEF);
		content = request.getParameter(REQUEST_PARAM_CONTENT);
		idNews = Integer.parseInt((String) request.getParameter(REQUEST_PARAM_ID));

		if (nullEmptyValidate(title, brief, content)) {
			response.sendRedirect(PATH_EMPTY_PARAMETER + idNews);
			return;
		}

		news = new News(idNews, title, brief, content);

		try {

			NEWS_SERVISE.update(news);
			response.sendRedirect(PATH_AFTER_EDIT_NEWS);

		} catch (ServiceExceptionValidationNews e) {

			log.error("There are objectionable words in the news.", e);
			response.sendRedirect(PATH_AFTER_VALIDATION_EXCEPTION);

		} catch (ServiceException e) {
			log.error("Database error during editing a news.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);

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
