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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		
		printItemsAndCost(writer, request, session);
		userForm(writer);
		
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void printItemsAndCost(PrintWriter writer, HttpServletRequest request, HttpSession session) {
		Connection connection = DBConnect.getInstance();
		
		Queue<String> cartlist = new LinkedList<String>();
		cartlist = new LinkedList<String>((Queue<String>)session.getAttribute("cartListKey"));
		
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
		writer.println("<p><label>City:</label> <input type=\"text\" name=\"city\" id=\"suggestCity\"><label>State: </label> <select name=\"state\">");
		writer.println("<option value=\"AL\">Alabama</option>");
		writer.println("<option value=\"AK\">Alaska</option>");
		writer.println("<option value=\"AZ\">Arizona</option>");
		writer.println("<option value=\"AR\">Arkansas</option>");
		writer.println("<option value=\"CA\">California</option>");
		writer.println("<option value=\"CO\">Colorado</option>");
		writer.println("<option value=\"CT\">Connecticut</option>");
		writer.println("<option value=\"DE\">Delaware</option>");
		writer.println("<option value=\"DC\">District Of Columbia</option>");
		writer.println("<option value=\"FL\">Florida</option>");
		writer.println("<option value=\"GA\">Georgia</option>");
		writer.println("<option value=\"HI\">Hawaii</option>");
		writer.println("<option value=\"ID\">Idaho</option>");
		writer.println("<option value=\"IL\">Illinois</option>");
		writer.println("<option value=\"IN\">Indiana</option>");
		writer.println("<option value=\"IA\">Iowa</option>");
		writer.println("<option value=\"KS\">Kansas</option>");
		writer.println("<option value=\"KY\">Kentucky</option>");
		writer.println("<option value=\"LA\">Louisiana</option>");
		writer.println("<option value=\"ME\">Maine</option>");
		writer.println("<option value=\"MD\">Maryland</option>");
		writer.println("<option value=\"MA\">Massachusetts</option>");
		writer.println("<option value=\"MI\">Michigan</option>");
		writer.println("<option value=\"MN\">Minnesota</option>");
		writer.println("<option value=\"MS\">Mississippi</option>");
		writer.println("<option value=\"MO\">Missouri</option>");
		writer.println("<option value=\"MT\">Montana</option>");
		writer.println("<option value=\"NE\">Nebraska</option>");
		writer.println("<option value=\"NV\">Nevada</option>");
		writer.println("<option value=\"NH\">New Hampshire</option>");
		writer.println("<option value=\"NJ\">New Jersey</option>");
		writer.println("<option value=\"NM\">New Mexico</option>");
		writer.println("<option value=\"NY\">New York</option>");
		writer.println("<option value=\"NC\">North Carolina</option>");
		writer.println("<option value=\"ND\">North Dakota</option>");
		writer.println("<option value=\"OH\">Ohio</option>");
		writer.println("<option value=\"OK\">Oklahoma</option>");
		writer.println("<option value=\"OR\">Oregon</option>");
		writer.println("<option value=\"PA\">Pennsylvania</option>");
		writer.println("<option value=\"RI\">Rhode Island</option>");
		writer.println("<option value=\"SC\">South Carolina</option>");
		writer.println("<option value=\"SD\">South Dakota</option>");
		writer.println("<option value=\"TN\">Tennessee</option>");
		writer.println("<option value=\"TX\">Texas</option>");
		writer.println("<option value=\"UT\">Utah</option>");
		writer.println("<option value=\"VT\">Vermont</option>");
		writer.println("<option value=\"VA\">Virginia</option>");
		writer.println("<option value=\"WA\">Washington</option>");
		writer.println("<option value=\"WV\">West Virginia</option>");
		writer.println("<option value=\"WI\">Wisconsin</option>");
		writer.println("<option value=\"WY\">Wyoming</option>");
		writer.println("</select></p><br>");
		writer.println("<p><label>Phone Number:</label> <input type=\"text\" name=\"phone\"> <label>Email:</label> <input type=\"text\" name=\"email\"></p><br>");
		writer.println("<p><label>Quantity:</label> <input type=\"text\" name=\"quantity\" value=\"1\">");
		writer.println("<label>&emsp;Shipping Method:</label><select name=\"shipping\"><option value=\"2 Day\">2 Day</option><option value=\"Overnight\">Overnight</option><option value=\"6 Day Ground\">6 Day Ground</option></select></p><br>");
		writer.println("<p><label>Credit Card:</label> <input type=\"text\" name=\"card\"></p><br><br>");
		writer.println("<p><label></label><div class=\"formButton\"><input type=\"submit\" value=\"Submit\"></div></p></form></div>");
		writer.println("</div></div>");

	}
	

	
}
