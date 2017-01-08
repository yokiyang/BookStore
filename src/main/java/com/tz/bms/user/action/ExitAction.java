package com.tz.bms.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:18
 */
@SuppressWarnings("serial")
@WebServlet(name="ExitAction",urlPatterns="/user/exit")
public class ExitAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除session中的用户信息
		request.getSession().removeAttribute("user");
		
		response.sendRedirect(request.getContextPath()+"/book/IndexAction");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
