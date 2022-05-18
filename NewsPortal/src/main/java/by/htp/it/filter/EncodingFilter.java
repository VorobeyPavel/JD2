package by.htp.it.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	public static final String CONFIG_INIT_PARAM = "characterEncoding";
	private String encoding = "utf-8";

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter(CONFIG_INIT_PARAM);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;	
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
