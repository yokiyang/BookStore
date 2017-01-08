/**
 * 此代码归 xxx 版本所有，未经同意，严禁复制和转发
 */
package com.tz.bms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description JDBC程序执行的模板，它封装了JDBC执行查询和非查询的操作流程，通过回调
 * 指定的接口来达到JDBC操作的简化的目的，隐藏JDBC操作中连接的获取、事务处理、异常的处理等
 * @see PreparedStatementCreator#create(java.sql.Connection)
 * @see ResultSetHandler#extract(java.sql.ResultSet)
 * @author teacher
 * @version 1.0
 * @createDate 2016年11月16日 上午9:35:22
 * @since jdk6.0
 * @project JDBC_Teacher
 *
 */
public class JdbcTemplate {

	/****************
	 * JDBC操作的查询模板方法，所有的查询操作都应该使用此模板方法
	 * 此方法封装了JDBC编程的流程，隐藏了连接的获取、事务的处理、异常的处理，用户调用此
	 * 方法时只关心它的sql语句以及如何处理这个结果集，而不用关心其它的操作，从而简化了
	 * 用户编写JDBC程序的步骤，提高了JDBC编程的效率。
	 * @param psc 获取PreparedStatement对象的回调接口
	 * @param handler 处理ResultSet对象的回调接口
	 * @return 执行成功，返回对象，失败，返回null
	 */
	public final Object query(PreparedStatementCreator psc,
			ResultSetHandler handler) {
		Object result = null;
		try (Connection conn = MyDataSource.getConnection();
		//回调接口，来得到PreparedStatement对象
				PreparedStatement pstmt = psc.create(conn);
				//执行 sql 命令
				ResultSet rs = pstmt.executeQuery();) {
			//处理结果
			result = handler.extract(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//返回
		return result;
	}

	/******
	 * JDBC操作的非查询语句执行模板方法，同上
	 * @param psc
	 * @return 如果执行成功，则返回受影响的记录数，执行失败，则返回-1
	 */
	public final int update(PreparedStatementCreator psc) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//JDBC
		try {
			conn = MyDataSource.getConnection();
			conn.setAutoCommit(false);
			//回调接口
			pstmt = psc.create(conn);
			//执行语句
			result = pstmt.executeUpdate();
			//提交事务
			conn.commit();
			//
		} catch (SQLException e) {
			//回滚事务
			if (conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, pstmt, null);
		}
		//返回
		return result;
	}
}
