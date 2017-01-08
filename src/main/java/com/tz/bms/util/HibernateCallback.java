package com.tz.bms.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * 制定接口,提供回调方法
 * 与HibernateTemplate模板类配合使用
 */
public interface HibernateCallback {

	Object doHibernate(Session ses) throws HibernateException;

}
