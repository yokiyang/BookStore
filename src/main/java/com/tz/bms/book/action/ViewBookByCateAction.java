package com.tz.bms.book.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Category;
import com.tz.bms.entity.Pageing;
import com.tz.bms.util.Beanfactory;
/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:46:24
 */
@SuppressWarnings("serial")
@WebServlet(name="ViewBookByCateAction",urlPatterns="/book/viewBookByCate")
public class ViewBookByCateAction extends HttpServlet {

	//通过bean工厂,获得service接口对象
	IBookService bookService=(IBookService)Beanfactory.getBean("bookService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收图书类型
		String cateId=request.getParameter("cateId");
		Category category=bookService.querytById(Long.parseLong(cateId));
		//接收分页条件
		String pageNow=request.getParameter("pageNow");
		String pageSize=request.getParameter("pageSize");
		
		//如果没有接收到,给予默认
		pageNow=pageNow==null?"1":pageNow;
		pageSize=pageSize==null?"8":pageSize;
		
		Pageing pageing=bookService.queryBookByCondition(Integer.parseInt(pageNow), Integer.parseInt(pageSize), category.getCateName());
		request.getSession().setAttribute("pageing", pageing);
		request.setAttribute("cateName", category.getCateName());
		request.getRequestDispatcher("/jsp/book/specials.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
