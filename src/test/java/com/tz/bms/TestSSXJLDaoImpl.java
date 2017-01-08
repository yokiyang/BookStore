package com.tz.bms;

import java.util.List;

import org.junit.Test;

import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.ssxjl.dao.ISSXJLDao;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:39
 */
public class TestSSXJLDaoImpl {
	ISSXJLDao SSXJLDao=(ISSXJLDao) Beanfactory.getBean("SSXJLDao");
	@Test
	public void testselectAllProvinces(){
		List<Province> provinces=SSXJLDao.selectAllProvinces();
		for(Province pro:provinces){
			System.out.println(pro);
		}
	}
	@Test
	public void testselectCityByProvincecode(){
		List<City> listCity=SSXJLDao.selectCityByProvinceCode("110000");
		for(City city:listCity){
			System.out.println(city);
		}
	}
	
	@Test
	public void testselectAreaBycitycode(){
		List<Area> listArea=SSXJLDao.selectAreaByCityCode("320600");
		for(Area area:listArea){
			System.out.println(area);
		}
	}
}
