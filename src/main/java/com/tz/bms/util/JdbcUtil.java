/**
 * 此代码归 xxx 版本所有，未经同意，严禁复制和转发
 */
package com.tz.bms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description JDBC的工具类
 * @author teacher
 * @version 1.0
 * @createDate 2016年11月14日 上午11:22:23
 * @since jdk6.0
 * @project JDBC_Teacher
 *
 */
public class JdbcUtil {
	/*****
	 * 释放JDBC相关的资源
	 * @param conn
	 * @param stmt
	 */
	public static void release(Connection conn, Statement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/*****
	 * 释放JDBC相关的资源
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public static void release(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(conn, stmt);
	}
}
