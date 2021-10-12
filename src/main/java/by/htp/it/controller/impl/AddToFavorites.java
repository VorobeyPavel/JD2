package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddToFavorites implements Command {
	
	private static AddToFavorites instance = new AddToFavorites();

	public static final String PATH_COMMAND = "Controller?command=Read_NEWS&responseCommand=News was added to favourites.&idNews=";
	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	
	private AddToFavorites() {}

	public static AddToFavorites getInstance() {
		return instance;
	}

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("AddToFavorites начало");

		int idNews;
		int idUser;

		idNews = Integer.parseInt(request.getParameter("idNews"));
		idUser = (int) request.getSession().getAttribute("idUser");

		try {
			
			System.out.println("AddToFavorites перед NEWS_SERVISE.addToFavorites(idNews, idUser);");

			NEWS_SERVISE.addToFavorites(idNews, idUser);
			
			System.out.println("AddToFavorites после NEWS_SERVISE.addToFavorites(idNews, idUser);");

		} catch (ServiceException e) {
			// log проблемы с доступом к БД
			// перевести на страницу ошибок, где есть ссылка на главную страницу

			e.printStackTrace();
		}

//+ idNews, чтобы вернуться на страницу
		
		System.out.println("AddToFavorites перед response.sendRedirect(PATH_COMMAND + idNews);");

		response.sendRedirect(PATH_COMMAND + idNews);
	}

}
