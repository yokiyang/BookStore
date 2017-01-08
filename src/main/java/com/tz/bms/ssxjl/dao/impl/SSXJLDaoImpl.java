package com.tz.bms.ssxjl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.ssxjl.dao.ISSXJLDao;
import com.tz.bms.util.JdbcTemplate;
import com.tz.bms.util.PreparedStatementCreator;
import com.tz.bms.util.ResultSetHandler;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:52:41
 */
public class SSXJLDaoImpl implements ISSXJLDao{
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@SuppressWarnings("unchecked")
	@Override
	public List<Province> selectAllProvinces() {
		return (List<Province>) jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement create(Connection conn) throws SQLException {
				String sql="select id,code,name from province";
				return conn.prepareStatement(sql);
			}
		}, new ResultSetHandler() {
			
			@Override
			public Object extract(ResultSet rs) throws SQLException {
				List<Province> listPro=new ArrayList<>();
				while(rs.next()){
					Province pro=new Province();
					int id=rs.getInt("id");
					String code=rs.getString("code");
					String name=rs.getString("name");
					pro.setProvinceId(id);
					pro.setProvinceCode(code);
					pro.setProvinceName(name);
					listPro.add(pro);
				}
				return listPro;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> selectCityByProvinceCode(final String proviceCode) {
		return (List<City>) jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement create(Connection conn) throws SQLException {
				String sql="select id,code,name from city where provinceid="+"'"+proviceCode+"'";
				return conn.prepareStatement(sql);
			}
		}, new ResultSetHandler() {
			
			@Override
			public Object extract(ResultSet rs) throws SQLException {
				List<City> listCity=new ArrayList<City>();
				while(rs.next()){
					City city=new City();
					int id=rs.getInt("id");
					String code=rs.getString("code");
					String name=rs.getString("name");
					city.setCityId(id);
					city.setCityCode(code);
					city.setCityName(name);
					listCity.add(city);
				}
				return listCity;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> selectAreaByCityCode(final String cityCode) {
return (List<Area>) jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement create(Connection conn) throws SQLException {
				String sql="select id,code,name from area where cityid="+"'"+cityCode+"'";
				return conn.prepareStatement(sql);
			}
		}, new ResultSetHandler() {
			
			@Override
			public Object extract(ResultSet rs) throws SQLException {
				List<Area> listArea=new ArrayList<Area>();
				while(rs.next()){
					Area area=new Area();
					int id=rs.getInt("id");
					String code=rs.getString("code");
					String name=rs.getString("name");
					area.setAreaId(id);
					area.setAreaCode(code);
					area.setAreaName(name);
					listArea.add(area);
				}
				return listArea;
			}
		});
	}

	@Override
	public Province selectProvinceByCode(final String provinceCode) {
		return (Province) jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement create(Connection conn) throws SQLException {
				String sql="select id,code,name from province where code="+"'"+provinceCode+"'";
				return conn.prepareStatement(sql);
			}
		}, new ResultSetHandler() {
			
			@Override
			public Object extract(ResultSet rs) throws SQLException {
				Province pro=new Province();
				if(rs.next()){
					int id=rs.getInt("id");
					String code=rs.getString("code");
					String name=rs.getString("name");
					pro.setProvinceId(id);
					pro.setProvinceCode(code);
					pro.setProvinceName(name);
				}
				return pro;
			}
		});
	}

	@Override
	public City selectCityByCode(final String cityCode) {
		return (City) jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement create(Connection conn) throws SQLException {
				String sql="select id,code,name from city where code="+"'"+cityCode+"'";
				return conn.prepareStatement(sql);
			}
		}, new ResultSetHandler() {
			
			@Override
			public Object extract(ResultSet rs) throws SQLException {
				City city=new City();
				if(rs.next()){
					int id=rs.getInt("id");
					String code=rs.getString("code");
					String name=rs.getString("name");
					city.setCityId(id);
					city.setCityCode(code);
					city.setCityName(name);
				}
				return city;
			}
		});
	}

	@Override
	public Area selectAreaByCode(final String areaCode) {
		return (Area) jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement create(Connection conn) throws SQLException {
				String sql="select id,code,name from area where code="+"'"+areaCode+"'";
				return conn.prepareStatement(sql);
			}
		}, new ResultSetHandler() {
			
			@Override
			public Object extract(ResultSet rs) throws SQLException {
				Area area=new Area();
				if(rs.next()){
					int id=rs.getInt("id");
					String code=rs.getString("code");
					String name=rs.getString("name");
					area.setAreaId(id);
					area.setAreaCode(code);
					area.setAreaName(name);
				}
				return area;
			}
		});
	}

}
