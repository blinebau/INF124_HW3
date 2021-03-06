package servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddedToCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("pid");
		request.setAttribute("pid", value);
		System.out.println("AddedToCart Servlet called.." + value);
		request.getRequestDispatcher("/cartservlet").include(request, response);
		
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		
		printAddedtoCart(writer);
		
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
		
		
		response.setHeader("Refresh", "2; /HW3/index");
	}
	
	private void printAddedtoCart(PrintWriter writer) {
		writer.println("\t\t\t\t\t<p>Item has been added to Cart</p>");
		writer.println("\t\t\t\t\t<p>Redirecting to Homepage...</p>");
	}
	
}
