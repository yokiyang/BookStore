package com.tz.bms.user.action;

import java.io.IOException;

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
 *@2016-12-28 @下午1:53:29
 */
@SuppressWarnings("serial")
@WebServlet(name="RegisterAction",urlPatterns="/user/register")
public class RegisterAction extends HttpServlet {
	IUserService userService=(IUserService) Beanfactory.getBean("userService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		User user1=userService.loginUser(username);
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		String comparate=request.getParameter("comparate");
		String address=request.getParameter("address");
		if(user1==null){
			if((username!=null&&username.trim().length()>0)&&(password!=null&&password.trim().length()>0)&&(email!=null&&email.trim().length()>0)&&(tel!=null&&tel.trim().length()>0)&&(comparate!=null&&comparate.trim().length()>0)&&(address!=null&&address.trim().length()>0)){
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setTel(tel);
				user.setComparate(comparate);
				user.setAddress(address);
				boolean bool=userService.registerUser(user);
				if(bool){
					response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp");
				}else{
					response.sendRedirect(request.getContextPath()+"/failure.html");
				}
			}else{		
				response.sendRedirect(request.getContextPath()+"/failure.html");
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/failure.html");
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
