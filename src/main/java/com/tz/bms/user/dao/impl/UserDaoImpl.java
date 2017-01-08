package com.tz.bms.user.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.tz.bms.entity.User;
import com.tz.bms.user.dao.IUserDao;
import com.tz.bms.util.HibernateCallback;
import com.tz.bms.util.HibernateTemplate;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:45
 */
public class UserDaoImpl implements IUserDao{

	@Override
	public User selectUserByName(final String name) {
		return (User) HibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doHibernate(Session ses) throws HibernateException {
				return ses.createQuery("from User u where u.username=:username").setString("username", name).uniqueResult();
			}
		});
	}

	@Override
	public boolean insertUser(final User user) {
		try{
			return (boolean) HibernateTemplate.execute(new HibernateCallback() {
				
				@Override
				public Object doHibernate(Session ses) throws HibernateException {
					if(user!=null){
						ses.save(user);
						return true;
					}else{
						return false;
					}
				}
			});
		}catch(HibernateException e){
			return false;
		}
		
	}


}
