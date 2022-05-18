package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Exit implements Command{
	
	public static final String MAIN_PAGE_REDIRECT = "Controller?command=GO_TO_MAIN_PAGE";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	
	private Exit() {
	}
	
	private static Exit instance = new Exit();
	
	public static Exit getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.removeAttribute(SESSION_ATTRIBUTE_USER);
		response.sendRedirect(MAIN_PAGE_REDIRECT);

	}

}
