package by.htp.it.controller.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.controller.Command;
import by.htp.it.servise.NewsServise;
import by.htp.it.servise.ServiseProvider;
import by.htp.it.servise.exception.ServiseException;
import by.htp.it.controller.impl.GoToMainPage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command{
	
	public static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	public static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	public static final String SESSION_PATH = "path";
	public static final String PATH_COMMAND_MAIN = "go_to_main_page";
	public static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	public static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	
	private GoToMainPage() {
	}
	
	private static GoToMainPage instance = new GoToMainPage();
	
	public static GoToMainPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//List<News> newses = new ArrayList<News>();
		HttpSession session = request.getSession(true);
					
		try {
			
			session.setAttribute("newses", NEWS_SERVISE.getNewses(10));
			//request.setAttribute("newses", NEWS_SERVISE.getNewses(10));
		} catch (ServiseException e) {
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}
		
		request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_MAIN);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PAGE);
		requestDispatcher.forward(request, response);
		
	}
}
