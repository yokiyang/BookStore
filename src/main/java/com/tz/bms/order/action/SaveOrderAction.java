package com.tz.bms.order.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Cart;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.OrderItem;
import com.tz.bms.entity.User;
import com.tz.bms.order.service.IOrderService;
import com.tz.bms.orderitem.service.IOrderItemService;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:06
 */
@SuppressWarnings("serial")
@WebServlet(name="SaveOrderAction",urlPatterns="/order/save")
public class SaveOrderAction extends HttpServlet {
	IOrderService orderService=(IOrderService) Beanfactory.getBean("orderService");
	IOrderItemService itemService=(IOrderItemService) Beanfactory.getBean("itemService");
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String addressId=request.getParameter("addressId");
		String itemAllPrice=request.getParameter("itemAllPrice");
		Address address=orderService.queryAddressById(Long.parseLong(addressId));
		Order order=new Order();
		User user=(User) request.getSession().getAttribute("user");
		List<OrderItem> newItemslist=(List<OrderItem>) request.getSession().getAttribute("newItems");
		for(OrderItem o:newItemslist){
			System.out.println(o);
		}
		Set<OrderItem> newItems=new HashSet<OrderItem>(newItemslist);
		order.setOrderNum("TZ"+new Date().getTime());
		order.setCreateDate(new Date());
		order.setItems(newItems);
		order.setUser(user);
		order.setAddress(address);
		order.setOrderStatus("交易成功");
		boolean b=orderService.saveOrder(order);
		
		String ids="";
		for(OrderItem item:newItems){
			item.setOrder(order);
			itemService.saveOrderItem(item);
			ids+=item.getBook().getBookId()+":";
		}
		
		request.getSession().removeAttribute("newItems");
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.removeCart(ids);
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("itemAllPrice", itemAllPrice);
		if(b){
			response.sendRedirect(request.getContextPath()+"/permission/order/orderList");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
