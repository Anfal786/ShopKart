<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.shopkart.Product"%>
<%@page import="com.shopkart.ShopKartDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table></table>
	<form action="<<AddtoCart>>" method="post">
	<%
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ShopKartDAO sp = (ShopKartDAO) context.getBean("eDao");
		ArrayList<Product> list = sp.getProucts("select * from Product");
	%>
	<select name="productName">
		<%
			for (Product p : list) {
		%>
		<option value="<% p.getProductName(); %>">
			<%
				out.print(p.getProductName());
			%>
		</option>
		<%
			}
		%>
	</select>
	Quantity:<input type="text" name="quantity" >
	<input type="submit" value="Add to Cart">
	</form>
	<input type="button" value="Cancel">
	<input type="button" value="Shop">
</body>
</html>