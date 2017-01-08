package com.tz.bms.ssxjl.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.ssxjl.service.ISSXJLService;
import com.tz.bms.util.Beanfactory;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:52:33
 */
@SuppressWarnings("serial")
@WebServlet(name="SSXJLAction",urlPatterns="/ssxjl/*")
public class SSXJLAction extends HttpServlet {
	
	ISSXJLService SSXJLService=(ISSXJLService) Beanfactory.getBean("SSXJLService");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String pathInfo=request.getPathInfo();
		String jsonStr=null;
		if("/province".equals(pathInfo)){
			List<Province> provinces=SSXJLService.findAllProvinces();
			jsonStr=JSON.toJSONString(provinces);
		}
		
		if("/city".equals(pathInfo)){
			String province=request.getParameter("province");
			List<City> cities=SSXJLService.findCityByProvinceCode(province);
			jsonStr=JSON.toJSONString(cities);
		}

		if("/area".equals(pathInfo)){
			String city=request.getParameter("city");
			List<Area> areas=SSXJLService.findAreaByCityCode(city);
			jsonStr=JSON.toJSONString(areas);
		}
		out.println(jsonStr);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
