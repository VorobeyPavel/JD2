package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UnknowCommand implements Command {

	private static UnknowCommand instance = new UnknowCommand();

	public static final String SESSION_PATH = "path";
	public static final String PATH_COMMAND_ERR = "UNKNOWN_COMMAND";
	public static final String ERROR_PAGE = "/WEB-INF/jsp/Error.jsp";


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(SESSION_PATH, PATH_COMMAND_ERR);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
		requestDispatcher.forward(request, response);
	}

}
