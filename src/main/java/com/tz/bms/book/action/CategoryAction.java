package com.tz.bms.book.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Pageing;
import com.tz.bms.util.Beanfactory;
/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:43:37
 */
@SuppressWarnings("serial")
@WebServlet(name = "CategoryAction", urlPatterns = "/book/cateBook")
public class CategoryAction extends HttpServlet {
	IBookService bookService = (IBookService) Beanfactory
			.getBean("bookService");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接受当前页和每页显示的条目
		String pageNow = request.getParameter("pageNow");
		System.out.println(pageNow);
		String pageSize = request.getParameter("pageSize");
		//如果没有接收到,给予默认
		pageNow = pageNow == null ? "1" : pageNow;
		pageSize = pageSize == null ? "8" : pageSize;

		Pageing pageing = bookService.queryBookByCondition(
				Integer.parseInt(pageNow), Integer.parseInt(pageSize),
				null);
		System.out.println(pageing.getPageCount());
		request.setAttribute("pageing", pageing);
		request.getRequestDispatcher("/jsp/book/category.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
