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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tz.bms.entity.User;

/**权限验证过滤器*/
@WebFilter(filterName = "PermissionFilter", urlPatterns = "/permission/*", initParams = { @WebInitParam(name = "errorPage", value = "/jsp/user/login.jsp?info=2") })
public class PermissionFilter implements Filter {

	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (hasPrivilege(request)) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath()
					+ config.getInitParameter("errorPage"));
		}
	}

	private boolean hasPrivilege(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		return true;
	}

	@Override
	public void destroy() {

	}
}

