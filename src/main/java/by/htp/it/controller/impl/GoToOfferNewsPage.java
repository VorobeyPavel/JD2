package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToOfferNewsPage implements Command {
	
	private static GoToOfferNewsPage instance = new GoToOfferNewsPage();
	
	public static final String PATH_ADD_NEWS_PAGE = "/WEB-INF/jsp/addNews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "Go_To_Offer_News_Page";
	
	private GoToOfferNewsPage() {}

	public static GoToOfferNewsPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_ADD_NEWS_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
