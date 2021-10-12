package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.News;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToPublish implements Command {
	
	private static GoToPublish instance = new GoToPublish();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();

	public static final String REQUEST_PARAM_ID = "idNews";
	public static final String PATH_AFTER_APPROVAL = "Controller?command=view_offered_news";
	public static final String PATH_AFTER_COMMAND_ERROR = "Controller?command=Go_To_Main_Page&responseCommand=Connection error. Try later.";
	
	private GoToPublish() {}

	public static GoToPublish getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews = Integer.parseInt(request.getParameter(REQUEST_PARAM_ID));
		News newsApprovePublication = new News(idNews);
		
		System.out.println(idNews);
		System.out.println(newsApprovePublication.toString());
		
		try {

			NEWS_SERVISE.goToPublish(newsApprovePublication);
			response.sendRedirect(PATH_AFTER_APPROVAL);

		} catch (ServiceException e) {
			// log проблемы с доступом к БД
			// перевести на страницу ошибок, где есть ссылка на главную страницу
			response.sendRedirect(PATH_AFTER_COMMAND_ERROR);
			e.printStackTrace();
		}

	}

}
