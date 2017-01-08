/**
 * 此代码归 xxx 版本所有，未经同意，严禁复制和转发
 */
package com.tz.bms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @description 此接口是为JDBC模板类提供的回调接口，它由用户实现
 * @author teacher
 * @version 1.0
 * @createDate 2016年11月16日 上午9:22:49
 * @since jdk6.0
 * @project JDBC_Teacher
 *
 */
public interface PreparedStatementCreator {

	/*******
	 * 此方法在JDBC模板中回调，根据模板方法传递进来的连接和用户指定的sql
	 * 	创建PreparedStatement对象，并绑定参数
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	PreparedStatement create(Connection conn) throws SQLException;
}
