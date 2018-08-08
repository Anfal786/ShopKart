package com.shopkart;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BusinessObject {
	public static boolean isAdmin(String userName, String password) {
		if(userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
			return true;
		else
			return false;
	}
	public static ArrayList<Users> getUsers(String userName, String password) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ShopKartDAO uDao = (ShopKartDAO) context.getBean("eDao");
		return uDao.getUsers("select * from Users where userName='" + userName + "' and password='" + password + "'");
	}
	public static String getTransQuery(String productName, int Quantity) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ShopKartDAO pDao = (ShopKartDAO) context.getBean("eDao");
		ArrayList<Product> lst =  pDao.getProucts("select * from Product where productName='" + productName + "'");
		int price = lst.get(0).getPrice();
		int taxRate = lst.get(0).getTaxRate();
		int discountPerc = lst.get(0).getDiscountPerc();
		int discountAmt = price*discountPerc/100;
		int taxAmt = price*taxRate/100;
		int netAmt = price+taxAmt-discountAmt;
		String sql = "insert into";
	}
}
