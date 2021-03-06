package by.htp.it.controller.impl;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import by.htp.it.controller.impl.GoToMainPage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command{
	
	private static GoToMainPage instance = new GoToMainPage();
	
	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	
	private static final Logger log = LogManager.getLogger(GoToMainPage.class);

	public static final String SESSION_PATH = "path";
	public static final String PATH_COMMAND_MAIN = "go_to_main_page";
	public static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	public static final String ERROR_PAGE = "Controller?command=UNKNOWN_COMMAND";
	public static final String SESSION_ATTRIBUTE_ADMIN = "admin";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String SESSION_ATTRIBUTE_FIRST_USER_ROLE = "guest";
	
	private GoToMainPage() {
	}
	
	public static GoToMainPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(true);
					
		try {
			
			session.setAttribute("newses", NEWS_SERVISE.getNewses());
			//request.setAttribute("newses", NEWS_SERVISE.getNewses(10));
		} catch (ServiceException e) {
			log.error("Database error during geting the latest news.", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
			requestDispatcher.forward(request, response);
		}
		
		
		User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);

		if (user == null) {
			user = new User();
			user.setRole(SESSION_ATTRIBUTE_FIRST_USER_ROLE);
		}
		
		/*
		 * if(user!=null) { if(user.getRole()!="admin") { user.setRole("user"); } }
		 */
		
		System.out.println(user.toString()+"GoToMainPage");

		session.setAttribute(SESSION_ATTRIBUTE_USER, user);
		
		
		request.getSession().setAttribute(SESSION_PATH, PATH_COMMAND_MAIN);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PAGE);
		requestDispatcher.forward(request, response);
		
	}
}
