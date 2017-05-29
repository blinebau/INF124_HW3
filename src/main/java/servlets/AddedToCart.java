//AddedToCart servlet is responsible for displaying the message that the item has been added to the cart

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

public class AddedToCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("pid");
		request.setAttribute("pid", value);
		System.out.println("AddedToCart Servlet called..");
		request.getRequestDispatcher("/cartservlet").include(request, response);
		
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		
		printAddedtoCart(writer);
		
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
		
		//Calls session tracking servlet to store item for last 5 items viewed feature
		//String value = request.getParameter("pid");
		//request.setAttribute("pid", value);

		//request.getRequestDispatcher("/sessiontracking").include(request, response);
	}
	
	private void printAddedtoCart(PrintWriter writer) {
		writer.println("\n\t\t\t\t<div class=\"detail-box\">");
		writer.println("\t\t\t\t\t<p>Item has been added to Cart</p>");
		writer.println("\n\t\t\t\t</div>");
	}
	
}
