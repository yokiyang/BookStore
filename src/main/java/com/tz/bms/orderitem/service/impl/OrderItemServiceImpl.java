package com.tz.bms.orderitem.service.impl;

import java.util.List;

import com.tz.bms.entity.OrderItem;
import com.tz.bms.orderitem.dao.IOrderItemDao;
import com.tz.bms.orderitem.service.IOrderItemService;
import com.tz.bms.util.Beanfactory;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:52:12
 */
public class OrderItemServiceImpl implements IOrderItemService{
	
	IOrderItemDao itemDao= (IOrderItemDao) Beanfactory.getBean("itemDao");

	@Override
	public boolean saveOrderItem(OrderItem item) {
		return itemDao.saveOrderItem(item);
		
	}

	@Override
	public List<OrderItem> getAllItem() {
		return itemDao.getAllItem();
	}

}
