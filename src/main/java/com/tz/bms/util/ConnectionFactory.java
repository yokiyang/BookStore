/**
 * 此代码归 xxx 版本所有，未经同意，严禁复制和转发
 */
package com.tz.bms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @description JDBC连接的工厂类，此类主要是获取数据库连接对象
 * @author teacher
 * @version 1.0
 * @createDate 2016年11月11日 下午3:50:58
 * @since jdk6.0
 * @project JDBC_Teacher
 *
 */
public class ConnectionFactory {

	private static final String URL;
	private static final String USER;
	private static final String PWD;
	private static final String DRIVER;

	static {
		//初始化这些静态常量属性
		URL = DBConfig.getValue("url");
		USER = DBConfig.getValue("user");
		PWD = DBConfig.getValue("pwd");
		//为了向JDK5.0版本兼容
		DRIVER = DBConfig.getValue("driver");
	}

	/*****
	 * 获取JDBC连接
	 * @return
	 */
	public static Connection getConnection() throws SQLException {
		//兼容JDBC3.0
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			//重新抛出异常
			throw new SQLException("手动注册驱动失败", e);
		}
		//
		return DriverManager.getConnection(URL, USER, PWD);
	}
}
