package com.tz.bms.order.service.impl;

import java.util.List;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.dao.IOrderDao;
import com.tz.bms.order.service.IOrderService;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:25
 */
public class OrderServiceImpl implements IOrderService{
	
	IOrderDao orderDao=(IOrderDao) Beanfactory.getBean("orderDao");
	@Override
	public Pageing queryAllAddressByUser(User user, int size, int now) {
		return orderDao.selectAddressByPage(user, size, now);
	}

	@Override
	public boolean saveAddress(Address a) {
		return orderDao.insertAddress(a);
	}

	@Override
	public boolean saveOrder(Order o) {
		return orderDao.insertOrder(o);
	}

	@Override
	public Pageing queryOrderByPage(User user, int size, int now) {
		return orderDao.selectOrderByPage(user, size, now);
	}

	@Override
	public Address queryAddressById(long id) {
		return orderDao.selectAddressById(id);
	}

	@Override
	public Pageing quetyAddressByPageDefault(User user, int size, int now) {
		return orderDao.selectAddressByPageDefault(user, size, now);
	}

	@Override
	public void updateaddress(Address a) {
		if(a!=null){
			orderDao.updateaddress(a);
		}
	}

	@Override
	public List<Address> getAddressByUser(User user) {
		if(user != null) {
			Long userId = user.getUserId();
			if(userId != null) {
				return orderDao.findByUserId(userId);
			}
		}
		return null;
	}
}
