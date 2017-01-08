package com.tz.bms.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**Hibernate工具类*/
public class HibernateUtil {

	private static SessionFactory sf;

	static {
		Configuration cfg = new Configuration().configure();

		ServiceRegistry sr = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).build();
		sf = cfg.buildSessionFactory(sr);
	}

	/**
	 * 获取Session的方法
	 */
	public static Session getSession() {
		return sf == null ? null : sf.openSession();
	}

	/**
	 * 关闭Session的方法
	 */
	public static void close(Session ses) {
		if (ses != null) {
			ses.close();
		}
	}
}
