package com.tz.bms.book.action;

import java.io.IOException;
import java.util.List;

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
 * 本来用来演示 首页显示
 *@author 杨倩Yoki
 *@2016-12-22 @下午8:49:50
 */

@SuppressWarnings("serial")
@WebServlet(name="IndexAction",urlPatterns="/book/IndexAction")
public class IndexAction extends HttpServlet {
	//通过bean工厂,获得service接口对象
	IBookService bookService=(IBookService) Beanfactory.getBean("bookService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//查询出两个精选图书和三本新书和三本推荐图书
		Pageing bestBook=bookService.queryBookByCondition(1, 2, "精选图书");
		Pageing newBook=bookService.queryBookByCondition(1, 3, "新书");
		Pageing recommendBook=bookService.queryBookByCondition(1, 3, "推荐图书");
		//queryBookByCondition(now,size,condition)//分页
		List<Category> categorys=bookService.selectAllCategory();
		request.setAttribute("bestBook", bestBook);
		request.setAttribute("newBook", newBook);
		request.getSession().setAttribute("recommendBook", recommendBook);
		request.getSession().setAttribute("categorys", categorys);
		//转发到index.jsp去显示
		request.getRequestDispatcher("/jsp/book/index.jsp").forward(request, response);

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
