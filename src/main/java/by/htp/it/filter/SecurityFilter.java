package by.htp.it.filter;

import java.io.IOException;

import by.htp.it.bean.User;
import by.htp.it.controller.CommandName;
import by.htp.it.controller.impl.ReadNews;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

	public static final String SESSION_ATTR_USER = "user";
	public static final String ROLE_GUEST = "guest";
	public static final String ROLE_ADMIN = "admin";
	public static final String COMMAND_REQUEST_PARAM = "command";
	public static final String PATH_SESSION_NULL = "Controller?command=Go_To_Authorization_Page&messageErrorNoSessionNoUser=Please, log in.";
	public static final String PATH_USER_GUEST = "Controller?command=Go_To_Authorization_Page&messageErrorNoSessionNoUser=Please, log in.";
	public static final String PATH_USER_NOT_ADMIN = "Controller?command=Go_To_main_Page&messageErrorNoRights=You do not have access rights to perform this operation.";

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session;

		User user;

		CommandName commandName;

		session = request.getSession();
		user = (User) request.getSession().getAttribute(SESSION_ATTR_USER);

		if (user == null) {
			user = new User();
			user.setRole(ROLE_GUEST);
		}
		session.setAttribute(SESSION_ATTR_USER, user);

		String name = request.getParameter(COMMAND_REQUEST_PARAM);

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			commandName = CommandName.UNKNOWN_COMMAND;
		}
		
		switch (commandName) {
		case READ_NEWS, ADD_TO_FAVORITES, DELETE_FROM_FAVORITES, READ_ALL_NEWS, OPEN_PROFILE, VIEW_FAVORITE_NEWS, ADD_COMMENT, OFFER_NEWS, GO_TO_OFFER_NEWS_PAGE, VIEW_MY_OFFERED_NEWS, GO_TO_CHANGE_PASSWORD_PAGE, CHANGE_PASSWORD: {


			if (ROLE_GUEST.equals(user.getRole())) {
				response.sendRedirect(PATH_USER_GUEST);
				return;
			}

			break;
		}
		case ADD_NEWS, GO_TO_ADD_NEWS_PAGE, DELETE_NEWS, EDIT_NEWS, GO_TO_EDIT_NEWS_PAGE, VIEW_OFFERED_NEWS, DO_NOT_PUBLISH, GO_TO_CHECK_OFFERED_NEWS_PAGE, GO_TO_PUBLISH:

			if (ROLE_GUEST.equals(user.getRole())) {
				response.sendRedirect(PATH_USER_GUEST);
				return;
			}

			if (!ROLE_ADMIN.equals(user.getRole())) {
				response.sendRedirect(PATH_USER_NOT_ADMIN);
				return;
			}

			break;

		default:

		{

		}

		}

		chain.doFilter(arg0, arg1);

	}

}
