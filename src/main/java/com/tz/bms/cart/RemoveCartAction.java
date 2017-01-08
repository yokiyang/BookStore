package com.tz.bms.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.entity.Cart;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:47:46
 */
@SuppressWarnings("serial")
@WebServlet(name="RemoveCartAction",urlPatterns="/cart/removecart")
public class RemoveCartAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
			String ids=request.getParameter("ids");
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			
			cart.removeCart(ids);
			request.getSession().setAttribute("cart", cart);
			//商品总数量
			int allCount = cart.getAllcount();
			//商品总价
			double allPrice = cart.getAllPrice();
			//该商品总价
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
