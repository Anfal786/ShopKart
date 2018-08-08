package com.shopkart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shopkart.Product;
import com.shopkart.ShopKartDAO;

import oracle.net.aso.q;

/**
 * Servlet implementation class AddToKart
 */
@WebServlet("/AddToKart")
public class AddToKart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToKart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("productName");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String sql = "select * from Product where productName='"+productName+"'";
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ShopKartDAO sp = (ShopKartDAO) context.getBean("eDao");
		ArrayList<Product> list = sp.getProucts(sql);
		int currentQuantity=list.get(0).getQuantity();
		if(quantity>currentQuantity) {
			request.setAttribute("msg", "We Only have "+currentQuantity+" "+productName+" Available!!!");
			request.getRequestDispatcher("CustomerHome.jsp").forward(request, response);
		}	
		sql = "update product set quantity="+(currentQuantity-quantity)+" where productName='"+productName+"'";
		sp.updateTable(sql);
		list = sp.getProucts(sql);
		if(list.size()>0) {
			//update
			int currentTransQuantity = list.get(0).getQuantity();
			sql = "update Transaction set quantity="+(currentTransQuantity+quantity)+" where productName='"+productName+"'";
			sp.updateTable(sql);
		}
		{
			//insert
			
			sql = "insert * from Transaction values();
			sp.updateTable(sql);
		}
		}
	}

}
