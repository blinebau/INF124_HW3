//CheckOut servlet is responsible for displaying the items in the cart and the grand total of all items
//Servlet also displays the form for the user to enter their personal information

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
		
		System.out.println("Checkout servlet called, items in cart:");
		if (cartlist.size() > 0) {
			for (String element : cartlist) {
				System.out.println(element);
			}
		}
		
		writer.println("\n\t\t\t\t\t<div class=\"content-top\">");
		writer.println("\t\t\t\t\t\t<div class=\"content-top-text\">");
		writer.println("\t\t\t\t\t\t\t<h3>Check Out</h3>");
		writer.println("\t\t\t\t\t\t\t<div class=\"orderDetails\">");
		
		
		if (cartlist.size() > 0) {
			int total = 0;
			try {
				
				for (String element : cartlist) {
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM product WHERE pid=\"" + element + "\"");
					result.next();
					//writer.println("\n\t\t\t\t\t\t\t\t<img class =\"track-pic\" src=\"" + result.getString(8) + "\">");
					writer.println("\n\t\t\t\t\t\t\t\t<p>");
					writer.println("\n\t\t\t\t\t\t\t\t\t<label style=\"text-align:left\">" + result.getString(2) + "</label><label style=\"text-align:right\">$" + result.getInt(4) + "</label>");
					writer.println("\t\t\t\t\t\t\t\t</p>");
					total += result.getInt(4);
				}
				writer.println("<br>");
				writer.println("\n\t\t\t\t\t\t\t\t<p>");
				writer.println("\t\t\t\t\t\t\t\t<label style=\"text-align:left\">Grand Total</label><label style=\"text-align:right\">$" + total + "</label>");
				writer.println("\t\t\t\t\t\t\t\t</p>");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			writer.println("\n\t\t\t\t\t\t\t\t<p>Your Cart is empty</p>");
		}
		
		writer.println("\t\t\t\t\t\t\t</div>");
	}
	
	private void userForm(PrintWriter writer) {
		writer.println("<h3>Order Form</h3>");
		writer.println("<div class=\"form-content\">");
		writer.println("<form name=\"billingForm\" action=\"formcontroller\" onsubmit=\"return validateForm(this)\" method=\"post\"><br>");
		writer.println("<p><label>First Name:</label> <input type=\"text\" name=\"firstName\"> <label>Last Name:</label> <input type=\"text\" name=\"lastName\"></p><br>");
		writer.println("<p><label>Address:</label> <input type=\"text\" name=\"address\"> <label>Zip Code:</label> <input type=\"text\" name=\"zip\"></p><br>");
		writer.println("<p><label>City:</label> <input type=\"text\" name=\"city\" id=\"suggestCity\"><label>State: </label> <input type=\"text\" name=\"state\"></p><br>");
		writer.println("<p><label>Phone Number:</label> <input type=\"text\" name=\"phone\"> <label>Email:</label> <input type=\"text\" name=\"email\"></p><br>");
		writer.println("<p><label>Quantity:</label> <input type=\"text\" name=\"quantity\" value=\"1\">");
		writer.println("<label>&emsp;Shipping Method:</label><select name=\"shipping\"><option value=\"2 Day\">2 Day</option><option value=\"Overnight\">Overnight</option><option value=\"6 Day Ground\">6 Day Ground</option></select></p><br>");
		writer.println("<p><label>Credit Card:</label> <input type=\"text\" name=\"card\"></p><br><br>");
		writer.println("<p><label></label><div style=\"display: table-cell\" class=\"formButton\"><input type=\"submit\" value=\"Submit\"></div></p></form></div>");
		writer.println("</div></div>");

	}
	

	
}
