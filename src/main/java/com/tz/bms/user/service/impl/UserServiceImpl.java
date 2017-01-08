package com.tz.bms.user.service.impl;

import com.tz.bms.entity.User;
import com.tz.bms.user.dao.IUserDao;
import com.tz.bms.user.service.IUserService;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:56
 */
public class UserServiceImpl implements IUserService{

	IUserDao userDao=(IUserDao) Beanfactory.getBean("userDao");
	@Override
	public User loginUser(String name) {
		return userDao.selectUserByName(name);
	}

	@Override
	public boolean registerUser(User user) {
		return userDao.insertUser(user);
		
	}

}
