package com.tz.bms.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**Hibernate模板工具类,提供Hibernate标准流程代码*/
public class HibernateTemplate {
	/**
	 * 回调方法
	 */
	public static final Object execute(HibernateCallback hc) {
		Session ses = null;
		Transaction tx = null;
		Object obj = null;
		try {
			ses = HibernateUtil.getSession();
			tx = ses.beginTransaction();
			obj = hc.doHibernate(ses);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			//e.printStackTrace();
			throw e;
		} finally {
			HibernateUtil.close(ses);
		}
		return obj;
	}
}
