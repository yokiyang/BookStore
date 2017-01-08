package com.tz.bms.orderitem.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.tz.bms.entity.OrderItem;
import com.tz.bms.orderitem.dao.IOrderItemDao;
import com.tz.bms.util.HibernateCallback;
import com.tz.bms.util.HibernateTemplate;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:58
 */
@SuppressWarnings("unchecked")
public class OrderItemDaoImpl implements IOrderItemDao{

	@Override
	public boolean saveOrderItem(final OrderItem item) {
		
		return (boolean) HibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doHibernate(Session ses) throws HibernateException {
				if(item!=null){
					ses.save(item);
					return true;
				}else{
					return false;
				}
			}
		});
	}

	
	@Override
	public List<OrderItem> getAllItem() {
		
		return (List<OrderItem>) HibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doHibernate(Session ses) throws HibernateException {
				
				return ses.createQuery("from OrderItem").list();
			}
		});
	}
}
