package com.tz.bms;

import java.util.List;

import org.junit.Test;

import com.tz.bms.book.dao.IBookDao;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.OrderItem;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.dao.IOrderDao;
import com.tz.bms.orderitem.dao.IOrderItemDao;
import com.tz.bms.user.dao.IUserDao;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:34
 */
public class TestOrderItemDaoImpl {
	IOrderItemDao itemDao=(IOrderItemDao) Beanfactory.getBean("itemDao");
	IBookDao bookDao=(IBookDao) Beanfactory.getBean("bookDao");
	IOrderDao orderDao=(IOrderDao) Beanfactory.getBean("orderDao");
	IUserDao userDao=(IUserDao) Beanfactory.getBean("userDao");
	@Test
	public void testsaveOrderItem(){
		OrderItem item=new OrderItem();
		Book book=bookDao.selectBookById(1L);
		User user=userDao.selectUserByName("admin");
		item.setBook(book);
		item.setAllPrice(44.0);
		item.setCount(2);
		Pageing pageing=orderDao.selectOrderByPage(user, 1, 1);
		List<Order> orders=pageing.getOrders();
		item.setOrder(orders.get(0));
		boolean bool=itemDao.saveOrderItem(item);
		System.out.println(bool);
	}
}
