package by.htp.it.controller.impl;

import java.io.IOException;
import java.util.List;

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

public class ReadAllNews implements Command {
	
	private static ReadAllNews instance = new ReadAllNews();

	public static final ServiсeProvider PROVIDER = ServiсeProvider.getInstance();
	public static final NewsServiсe NEWS_SERVISE = PROVIDER.getNewsServise();

	public static final String PATH_ALL_NEWS_PAGE = "/WEB-INF/jsp/allNews.jsp";
	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String ROLE_GUEST = "guest";
	public static final String PARAM_PAGE = "page";
	public static final String RESPONSE_ATTR_NEWS = "news";
	public static final String RESPONSE_ATTR_NO_OF_PAGES = "numberOfPages";
	public static final String RESPONSE_ATTR_CURRENT_PAGE = "currentPage";
	public static final int RECORDS_PER_PAGE = 5;
	public static final int DEFAULT_PAGE_NUMBER = 1;
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_PATH_COMMAND = "read_All_News";
	public static final String SESSION_ATTRIBUTE_LIMIT_NEWS = "limitNews";
	
	private ReadAllNews() {}

	public static ReadAllNews getInstance() {
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("начало execute");

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(
					"Controller?command=Go_To_Authorization_Page&messageErrorNoSessionNoUser=Please, log in.");
			return;
		}
		
		System.out.println("после сесии");

		User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);

		if (ROLE_GUEST.equals(user.getRole())) {
			response.sendRedirect(
					"Controller?command=Go_To_Authorization_Page&messageErrorNoSessionNoUser=Please, log in.");
			return;
		}
		
		System.out.println("проверка роли");

		try {

			List<News> listOfLimitAmountOfNews;

			int page = DEFAULT_PAGE_NUMBER;

			if (request.getParameter(PARAM_PAGE) != null) {
				page = Integer.parseInt(request.getParameter(PARAM_PAGE));
			}
			
			System.out.println(request.getParameter(PARAM_PAGE));

			listOfLimitAmountOfNews = NEWS_SERVISE.readAllNews((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
			
			System.out.println("после listOfLimitAmountOfNews");

			int numberOfRecords = NEWS_SERVISE.getNumberOfRecords(); // получить количесво всех записей
			
			System.out.println("после numberOfRecords");

			int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / RECORDS_PER_PAGE);// рассчитать количество
																							// страниц
			System.out.println("после numberOfPages");

			request.getSession(true).setAttribute(SESSION_ATTRIBUTE_LIMIT_NEWS, listOfLimitAmountOfNews);

			response.sendRedirect(
					"Controller?command=Go_To_All_News_Page&numberOfPages=" + numberOfPages + "&currentPage=" + page);

		} catch (ServiceException e) {
			// log

			e.printStackTrace();
			response.sendRedirect(
					"Controller?command=Go_To_main_Page&responseCommandServiceException=Something went wrong...Try again later.");

		}

	}

}
