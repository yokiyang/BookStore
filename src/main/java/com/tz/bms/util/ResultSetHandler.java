/**
 * 此代码归 xxx 版本所有，未经同意，严禁复制和转发
 */
package com.tz.bms.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description 此类是用在JDBC模板类中的结果集处理器接口
 * @author teacher
 * @version 1.0
 * @createDate 2016年11月16日 上午9:24:49
 * @since jdk6.0
 * @project JDBC_Teacher
 *
 */
public interface ResultSetHandler {

	/**************
	 * 由JDBC模板方法回调，把传入的结果集进行封装成目标对象，由使用模板方法的人实现
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	Object extract(ResultSet rs) throws SQLException;
}
