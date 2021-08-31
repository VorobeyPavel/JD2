package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import by.htp.it.servise.NewsServise;
import by.htp.it.servise.ServiseProvider;
import by.htp.it.servise.exception.ServiseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddNews implements Command{
	
	public static final ServiseProvider PROVIDER = ServiseProvider.getInstance();
	public static final NewsServise NEWS_SERVISE = PROVIDER.getNewsServise();
	public static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
		
	private AddNews() {
	}
	
	private static AddNews instance = new AddNews();
	
	public static AddNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * try { request.setAttribute("newses", NEWS_SERVISE.getNewses(10)); } catch
		 * (ServiseException e) { System.out.println(e.getMessage());
		 * e.printStackTrace(); RequestDispatcher requestDispatcher =
		 * request.getRequestDispatcher(ERROR_PAGE); requestDispatcher.forward(request,
		 * response); }
		 */
				
	}
	
}
