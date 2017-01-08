package com.tz.bms.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Cart;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:47:40
 */
@SuppressWarnings("serial")
@WebServlet(name="ModifyCartAction",urlPatterns="/cart/modifyCart")
public class ModifyCartAction extends HttpServlet {
	
	IBookService bookService=(IBookService) Beanfactory.getBean("bookService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try{
			
			//拿到图书id			
			String id = request.getParameter("bookId");
			
			//拿到该图书
			Book b = bookService.queryBookById(Long.parseLong(id));
			
			//拿到图书数量
			String count = request.getParameter("count");
			
			//获得购物车
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			//修改购物车明细
			cart.modifyCart(id, count);
			//把购物车放到session作用域中
			request.getSession().setAttribute("cart", cart);
			
			//商品总数量
			int allCount = cart.getAllcount();
			//商品总价
			double allPrice = cart.getAllPrice();
			//该商品总价
			double onePrice = Integer.parseInt(count) * b.getPrice();
			out.print("1:"+allCount+":"+allPrice+":"+onePrice+"");		
		}catch(Exception e){
			out.print("0");
			e.printStackTrace();
		}
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
