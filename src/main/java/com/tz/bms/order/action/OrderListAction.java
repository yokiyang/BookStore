package com.tz.bms.order.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.service.IOrderService;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:50:57
 */
@SuppressWarnings("serial")
@WebServlet(name="OrderListAction",urlPatterns="/permission/order/orderList")
public class OrderListAction extends HttpServlet {
	IOrderService orderService=(IOrderService) Beanfactory.getBean("orderService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		String pageNow=request.getParameter("pageNow");
		String pageSize=request.getParameter("pageSize");
		
		pageNow=pageNow==null?"1":pageNow;
		pageSize=pageSize==null?"8":pageSize;
		
		Pageing paging=orderService.queryOrderByPage(user, Integer.parseInt(pageSize), Integer.parseInt(pageNow));
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("/jsp/order/orderlist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
