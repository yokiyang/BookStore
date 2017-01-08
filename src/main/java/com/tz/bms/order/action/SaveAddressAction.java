package com.tz.bms.order.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.entity.User;
import com.tz.bms.order.service.IOrderService;
import com.tz.bms.ssxjl.service.ISSXJLService;
import com.tz.bms.util.Beanfactory;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:01
 */
@SuppressWarnings("serial")
@WebServlet(name="SaveAddressAction",urlPatterns="/address/add")
public class SaveAddressAction extends HttpServlet {
	IOrderService orderService=(IOrderService) Beanfactory.getBean("orderService");
	ISSXJLService SSXJLService=(ISSXJLService) Beanfactory.getBean("SSXJLService");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String provinceCode=request.getParameter("province");
		String cityCode=request.getParameter("city");
		String areaCode=request.getParameter("area");
		Province pro=SSXJLService.selectProvinceByCode(provinceCode);
		City c=SSXJLService.selectCityByCode(cityCode);
		Area a=SSXJLService.selectAreaByCode(areaCode);
		String province=pro.getProvinceName();
		String city=c.getCityName();
		String area=a.getAreaName();
		String address_detail=request.getParameter("address_detail");
		String code=request.getParameter("code");
		String reciverName=request.getParameter("reciverName");
		String tel=request.getParameter("tel");
		String defaultAddress=request.getParameter("defaultAddress");
		defaultAddress=defaultAddress==null?"0":defaultAddress;
		User user=(User) request.getSession().getAttribute("user");
		List<Address> addrs = orderService.getAddressByUser(user);
		for(int i =0;i<=addrs.size()-1;i++){
			addrs.get(i).setIsDefault("0");
			orderService.updateaddress(addrs.get(i));
		}
		Address address=new Address();
		address.setArea(province+","+city+","+area);
		address.setDetailAddres(address_detail);
		address.setEmailCode(code);
		address.setIsDefault(defaultAddress);
		address.setReciver(reciverName);
		address.setTel(tel);
		address.setUser(user);
		boolean bool=orderService.saveAddress(address);
		if(bool){
			response.sendRedirect(request.getContextPath()+"/permission/order/balance");
		}else{
			response.sendRedirect(request.getContextPath()+"/jsp/cart/cart.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
