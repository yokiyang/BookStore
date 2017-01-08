package com.tz.bms.order.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.entity.Cart;
import com.tz.bms.entity.OrderItem;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.service.IOrderService;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:50:53
 */
@SuppressWarnings("serial")
@WebServlet(name="BalanceOrderAction",urlPatterns="/permission/order/balance")
public class BalanceOrderAction extends HttpServlet {
	IOrderService orderService=(IOrderService) Beanfactory.getBean("orderService");
	@SuppressWarnings({ "unchecked" })
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		String pageNow = request.getParameter("pageNow");
	    String pageSize = request.getParameter("pageSize");
	    pageNow = pageNow == null ? "1" : pageNow;
        pageSize = pageSize == null ? "3" : pageSize;
        Pageing addressPageingDefault=orderService.quetyAddressByPageDefault(user, 1+Integer.parseInt(pageSize)-3, 1);
		Pageing addressPageing=orderService.queryAllAddressByUser(user, Integer.parseInt(pageSize),Integer.parseInt(pageNow));
		
		String ids=null;
		ids = request.getParameter("ids");
		if(ids==null||ids.trim().length()==0){
			ids=(String) request.getSession().getAttribute("ids");
		}else{
			request.getSession().setAttribute("ids", ids);
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Set<OrderItem> itemsset = cart.getItems();
		List<OrderItem> items=new ArrayList<OrderItem>(itemsset);
		List<OrderItem> newItems = (List<OrderItem>) request.getSession().getAttribute("newItems");
		double itemAllPrice=0.0;
		if(null==newItems){
			newItems=new ArrayList<>();
			String[] idsarr=ids.split(":");
			for(int i=items.size()-1;i>=0;i--){
				for(String s:idsarr){
					if(s.equals(String.valueOf(items.get(i).getBook().getBookId()))){
						OrderItem item=new OrderItem();
						item.setBook(items.get(i).getBook());
						item.setCount(items.get(i).getCount());
						item.setAllPrice(items.get(i).getAllPrice());
						newItems.add(item);
						double price = items.get(i).getAllPrice();
						itemAllPrice +=price;
						break;
					}
				}
			}
		}else{
			request.getSession().removeAttribute("newItems");
		}
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("addressPageingDefault", addressPageingDefault);
		request.setAttribute("addressPageing", addressPageing);
		request.getSession().setAttribute("newItems", newItems);
		if(itemAllPrice!=0.0){
			request.getSession().setAttribute("itemAllPrice", itemAllPrice);
		}
		request.getRequestDispatcher("/jsp/order/confirm_order.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
