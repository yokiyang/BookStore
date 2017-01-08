package com.tz.bms.book.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Book;
import com.tz.bms.util.Beanfactory;
/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:46:10
 */
@SuppressWarnings("serial")
@WebServlet(name="DetailBookAction",urlPatterns="/book/detailBook")
public class DetailBookAction extends HttpServlet {
	//通过bean工厂,获得service接口对象
	IBookService bookService=(IBookService) Beanfactory.getBean("bookService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bookId");
		
		//根据商品的标识,查询此商品信息
		Book book=bookService.queryBookById(Long.parseLong(bid));
		request.setAttribute("book", book);
		//转发到视图显示
		request.getRequestDispatcher("/jsp/book/detail.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
