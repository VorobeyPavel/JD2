package by.htp.it.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteFromFavorites implements Command {
	
	private static DeleteFromFavorites instance = new DeleteFromFavorites();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();

	private static final Logger log = LogManager.getLogger(DeleteFromFavorites.class);

	public static final String PATH_COMMAND = "Controller?command=Read_NEWS&responseCommand=News was deleted from favourites.&idNews=";
	public static final String PATH_AFTER_EXCEPTION = "Controller?command=Go_To_Main_Page&responseCommandServiceException=Something went wrong... Try again later.";
	
	private DeleteFromFavorites() {}

	public static DeleteFromFavorites getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		int idUser;

		idNews = Integer.parseInt(request.getParameter("idNews"));
		idUser = (int) request.getSession().getAttribute("idUser");

		try {

			NEWS_SERVISE.deleteFromFavorites(idNews, idUser);
			response.sendRedirect(PATH_COMMAND + idNews);

		} catch (ServiceException e) {
			// перевести на страницу ошибок, где есть ссылка на главную страницу
			log.error("Database error during deleting from favorites.", e);
			response.sendRedirect(PATH_AFTER_EXCEPTION);
		}
	
	}

}
