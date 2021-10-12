package by.htp.it.controller.impl;

import java.io.IOException;

import by.htp.it.bean.Comment;
import by.htp.it.controller.Command;
import by.htp.it.serviсe.NewsServiсe;
import by.htp.it.serviсe.ServiсeProvider;
import by.htp.it.serviсe.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddComment implements Command {
	
	private static AddComment instance = new AddComment();

	public static final String PATH_COMMAND = "Controller?command=Read_NEWS&responseCommand=Comment was added.&idNews=";
	public static final String REQUEST_PARAM_COMMENT = "comment";

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();
	
	private AddComment() {}

	public static AddComment getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idNews;
		int idUser;
		String contentComment;

		idNews = Integer.parseInt(request.getParameter("idNews"));
		idUser = (int) request.getSession().getAttribute("idUser");
		contentComment = (String) request.getParameter(REQUEST_PARAM_COMMENT);
		
		Comment comment = new Comment(idNews, idUser, contentComment);
		
		try {

			NEWS_SERVISE.addComment(comment);

		} catch (ServiceException e) {
			// log проблемы с доступом к БД
			// перевести на страницу ошибок, где есть ссылка на главную страницу

			e.printStackTrace();
		}

		response.sendRedirect(PATH_COMMAND + idNews);

	}

}
