//OrderDetails servlet is responsible for displaying the user's order information after submission

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

import db.DBConnect;

public class OrderDetails extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String value = request.getParameter("pid");
		//request.setAttribute("pid", value);

		String firstName = (String)request.getAttribute("firstkey");
		String lastName = (String)request.getAttribute("lastkey");
		String address = (String)request.getAttribute("addresskey");
		String zip = (String)request.getAttribute("zipkey");
		String city = (String)request.getAttribute("citykey");
		String state = (String)request.getAttribute("statekey");
		String phone = (String)request.getAttribute("phonekey");
		String email = (String)request.getAttribute("emailkey");
		int quantity = (Integer)request.getAttribute("quantitykey");
		String shipping = (String)request.getAttribute("shippingkey");
		String card = (String)request.getAttribute("cardkey");
		
		System.out.println(firstName + " " + lastName + " " + address);
		
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		
		writer.println("\n\t\t\t\t\t<div class=\"content-top\">");
		writer.println("\t\t\t\t\t\t<div class=\"content-top-text\">");
		writer.println("\t\t\t\t\t\t\t<h3>Thank You</h3>");
		writer.println("<p>Thank you for shopping at Weaves & Crafts. We hope to see you again soon!</p>");
		writer.println("\t\t\t\t\t\t\t<h3>Order Details</h3>");
		writer.println("\t\t\t\t\t\t\t<div class=\"orderDetails\">");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">First Name:</label><label style=\"text-align:right\">" + firstName + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">Last Name:</label> <label style=\"text-align:right\">" + lastName + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">Address:</label> <label style=\"text-align:right\">" + address + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">Zip Code:</label> <label style=\"text-align:right\">" + zip + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">City:</label> <label style=\"text-align:right\">" + city + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">State:</label> <label style=\"text-align:right\">" + state + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">Phone Number:</label> <label style=\"text-align:right\">" + phone + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">Email:</label> <label style=\"text-align:right\">" + email + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">Credit Card:</label> <label style=\"text-align:right\">" + card + "</label></p>");
		writer.println("\t\t\t\t\t\t\t\t<p><label style=\"text-align:left\">Shipping Method:</label> <label style=\"text-align:right\">" + shipping + "</label></p>");
		writer.println("\t\t\t\t\t\t\t</div>");
		writer.println("\t\t\t\t\t\t</div>");
		
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
		
		//Calls session tracking servlet to store item for last 5 items viewed feature
		//String value = request.getParameter("pid");
		//request.setAttribute("pid", value);

		//request.getRequestDispatcher("/sessiontracking").include(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}