package com.tz.bms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**字符集过滤器*/
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*", initParams = { @WebInitParam(name = "charset", value = "UTF-8") })
public class CharacterEncodingFilter implements Filter {

	public static final String DEFAULT_CHARSET = "UTF-8";
	public String charset;

	@Override
	public void init(FilterConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
