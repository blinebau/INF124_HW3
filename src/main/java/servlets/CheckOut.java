package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBConnect;

public class CheckOut extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//String value = request.getParameter("pid");
		//request.setAttribute("pid", value);
		
		//request.getRequestDispatcher("/cartservlet").include(request, response);
		
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		
		printItemsAndCost(writer, request, session);
		userForm(writer);
		
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
		
		//Calls session tracking servlet to store item for last 5 items viewed feature
		//String value = request.getParameter("pid");
		//request.setAttribute("pid", value);

		//request.getRequestDispatcher("/sessiontracking").include(request, response);
	}
	
	private void printItemsAndCost(PrintWriter writer, HttpServletRequest request, HttpSession session) {
		Connection connection = DBConnect.getInstance();
		
		Queue<String> cartlist = new LinkedList<String>();
		//cartlist = (Queue<String>)request.getAttribute("cart");
		cartlist = new LinkedList<String>((Queue<String>)session.getAttribute("cartListKey"));
		//cartlist = new LinkedList<String>((Queue<String>)session.getAttribute("cart"));
		//cartlist = new LinkedList<String>((Queue<String>)request.getAttribute("cart"));
		//cartlist = new LinkedList<String>((Queue<String>)request.getAttribute("cartListKey"));
		
		if (cartlist == null) {
			System.out.println("Its null");
		}
		
		System.out.println("Checkout servlet called, items in cart:");
		if (cartlist.size() > 0) {
			for (String element : cartlist) {
				System.out.println(element);
			}
		}
		
		writer.println("\n\t\t\t\t<div class=\"detail-box\">");
		writer.println("\t\t\t\t\t<p>Item has been added to Cart</p>");
		writer.println("\n\t\t\t\t</div>");
	}
	
	private void userForm(PrintWriter writer) {
		
	}
	
}
