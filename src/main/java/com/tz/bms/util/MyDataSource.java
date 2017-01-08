/**
 * 此代码归 xxx 版本所有，未经同意，严禁复制和转发
 */
package com.tz.bms.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * @description 利用commons-dbcp组件来使用连接池
 * @author teacher
 * @version 1.0
 * @createDate 2016年11月15日 下午4:26:29
 * @since jdk6.0
 * @project JDBC_Teacher
 *
 */
public class MyDataSource {

	private static BasicDataSource bds; //dbcp组件中的连接池实现类

	static {
		//初始化bds
		bds = new BasicDataSource();
		//设置建立连接和连接池的属性
		bds.setDriverClassName(DBConfig.getValue("driver"));
		bds.setUrl(DBConfig.getValue("url"));
		bds.setUsername(DBConfig.getValue("user"));
		bds.setPassword(DBConfig.getValue("pwd"));
		//连接池
		bds.setInitialSize(Integer.parseInt(DBConfig.getValue("initialSize")));
		bds.setMaxActive(Integer.parseInt(DBConfig.getValue("maxActive")));
		bds.setMaxIdle(Integer.parseInt(DBConfig.getValue("maxIdle")));
		bds.setMinIdle(Integer.parseInt(DBConfig.getValue("minIdle")));
		bds.setMaxWait(Long.parseLong(DBConfig.getValue("maxWait")));
		//
	}

	//提供对外的方法
	public static Connection getConnection() throws SQLException {
		return bds.getConnection();
	}
}
