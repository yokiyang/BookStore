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
import com.tz.bms.entity.OrderItem;
import com.tz.bms.util.Beanfactory;
/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:47:30
 */
@SuppressWarnings("serial")
@WebServlet(name="AddCartAction",urlPatterns="/cart/addCart")
public class AddCartAction extends HttpServlet {

	IBookService bookService=(IBookService) Beanfactory.getBean("bookService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
			//拿到图书id
			String id=request.getParameter("bookId");
			
			//根据Id查找图书
			Book book=bookService.queryBookById(Long.parseLong(id));
			
			//利用商品对象封装一个订单的明细对象
			OrderItem item = new OrderItem();
			//订单数量
			int count = 1;
			item.setItemId(Long.parseLong(id));
			item.setBook(book);
			item.setCount(count);
			item.setAllPrice(book.getPrice());
			//获得购物车
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(null == cart){
				cart = new Cart();
			}
			//把订单明细添加到购物车
			cart.addCart(item);
			//把购物车放到session作用域中
			request.getSession().setAttribute("cart", cart);
			//商品总数量
			int allCount = cart.getAllcount();
			//商品总价
			double allPrice = cart.getAllPrice();
			out.print("1:"+allCount+":"+allPrice+"");
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
