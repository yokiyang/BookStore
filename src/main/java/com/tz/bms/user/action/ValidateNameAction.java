package com.tz.bms.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 *@2016-12-28 @下午1:53:34
 */
@SuppressWarnings("serial")
@WebServlet(name="ValidateNameAction",urlPatterns="/user/validateName")
public class ValidateNameAction extends HttpServlet {
	//通过bean工厂,获得service接口对象
	IUserService userService=(IUserService) Beanfactory.getBean("userService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("username");
		name=URLDecoder.decode(name, "UTF-8");
		System.out.println(name);
		//判断此用户是否存在
		User user=userService.loginUser(name);
		if (user == null) {
			out.println("0");
		} else {
			out.println("1");
		}
		out.flush();
		out.close();
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
