package com.tz.bms.user.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.entity.User;
import com.tz.bms.user.service.IUserService;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:24
 */
@SuppressWarnings("serial")
@WebServlet(name="LoginAction",urlPatterns="/user/login")
public class LoginAction extends HttpServlet {
	IUserService userService=(IUserService) Beanfactory.getBean("userService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String noLogin = request.getParameter("noLogin");
		Cookie cookie = null;
		if (noLogin != null) {
			//说明要七天免登陆
			cookie = new Cookie("userInfo", URLEncoder.encode(username + ":"
					+ password, "UTF-8"));
			cookie.setMaxAge(7 * 24 * 60 * 60);
			//设置Cookie作用域
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		User user=userService.loginUser(username);
		if(user!=null){
			if(user.getPassword().equals(password)){
				//登录成功,把用户对象存入session
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/book/IndexAction");
			}else{
				response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp?info=1");//密码错误
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp?info=0");//用户名错误
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
