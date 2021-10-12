package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.News;
import by.htp.it.bean.User;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoNotPublish implements Command {

	private static DoNotPublish instance = new DoNotPublish();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();

	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_GUEST = "guest";
	public static final String REQUEST_PARAM_ID = "idNews";
	
	private DoNotPublish() {}

	public static DoNotPublish getInstance() {
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

		int idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID));
		News newsDenyToPublish = new News(idNews);

		try {

			NEWS_SERVISE.doNotPublish(newsDenyToPublish);
			response.sendRedirect("Controller?command=view_offered_news");

		} catch (ServiceException e) {
			// log проблемы с доступом к БД
			e.printStackTrace();
			response.sendRedirect(
					"Controller?command=Go_To_main_Page&responseCommandServiceException=Something went wrong... Try again later.");

		}

	}

}
