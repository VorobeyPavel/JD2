package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToChangePasswordPage implements Command {
	
	private static GoToChangePasswordPage instance = new GoToChangePasswordPage();

	public static final String CHANGE_PASSWORD_PAGE = "/WEB-INF/jsp/changePassword.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "Go_To_Change_Password_Page";
	
	private GoToChangePasswordPage() {}

	public static GoToChangePasswordPage getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_PATH_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CHANGE_PASSWORD_PAGE);
		requestDispatcher.forward(request, response);

	}

}
