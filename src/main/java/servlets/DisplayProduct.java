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

public class DisplayProduct extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("pid");
		request.setAttribute("pid", value);
		request.getRequestDispatcher("/sessiontracking").include(request, response);
		
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		
		printTracker(writer, request);
		
		printProduct(writer, request.getParameterMap());
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
	}
	
	private void printProduct(PrintWriter writer, Map<String, String[]> parameters) {
		DBConnect dbConnect = new DBConnect();
		String pid = parameters.get("pid")[0];
		
		try {
			Connection connection = dbConnect.getInstance();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM product WHERE pid=\"" + pid + "\"");
			result.next();
			writer.println("\n\t\t\t\t<div class=\"detail-box\">");
			writer.println("\t\t\t\t\t<h3>" + result.getString(2) + "</h3>");
			writer.println("\t\t\t\t\t<div class =\"big-pic-container\">");
			writer.println("\t\t\t\t\t\t<img id=\"bigImage\" class=\"item-pic\" src=\"" + result.getString(8) + "\">");
			writer.println("\t\t\t\t\t</div>");
			writer.println("\t\t\t\t\t<div class=\"item-description\">");
			writer.println("\t\t\t\t\t\t<p>" + result.getString(3) + 
					"<br><br><b>Price</b>: $" + result.getInt(4) +
					"<br><br><b>Product ID</b>: " + result.getString(1) +
					"<br><b>Size</b>: " + result.getString(5) + 
					"<br><b>Composition</b>: " + result.getString(6) + 
					"<br><b>Washing Instructions</b>: " + result.getString(7) + "</p>");
			writer.println("\t\t\t\t\t</div>");
			writer.println("\t\t\t\t\t\t<button onclick=\"location.href='addedtocart?pid=" + pid + "'\">Add to cart</button>");
			writer.println("\t\t\t\t\t<div class=\"preview-row\">");
			writer.println("\t\t\t\t\t\t<div class=\"preview-box\">");
			writer.println("\t\t\t\t\t\t\t<img class=\"small-pic\" onmouseover=\"document.getElementById('bigImage').src='" + result.getString(8) +
					"'\" src=\"" + result.getString(8) + "\">");
			writer.println("\t\t\t\t\t\t</div>");
			writer.println("\t\t\t\t\t\t<div class=\"preview-box\">");
			writer.println("\t\t\t\t\t\t\t<img class=\"small-pic\" onmouseover=\"document.getElementById('bigImage').src='" + result.getString(9) +
					"'\" src=\"" + result.getString(9) + "\">");
			writer.println("\t\t\t\t\t\t</div>");
			writer.println("\t\t\t\t\t\t<div class=\"preview-box\">");
			writer.println("\t\t\t\t\t\t\t<img class=\"small-pic\" onmouseover=\"document.getElementById('bigImage').src='" + result.getString(10) +
					"'\" src=\"" + result.getString(10) + "\">");
			writer.println("\t\t\t\t\t\t</div>");
			writer.println("\t\t\t\t\t\t<div class=\"preview-box\">");
			writer.println("\t\t\t\t\t\t\t<img class=\"small-pic\" onmouseover=\"document.getElementById('bigImage').src='" + result.getString(11) +
					"'\" src=\"" + result.getString(11) + "\">");
			writer.println("\t\t\t\t\t\t</div>");
			writer.println("\t\t\t\t\t</div>");
			writer.println("\t\t\t\t</div>");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void printTracker(PrintWriter writer, HttpServletRequest request) {
		DBConnect dbConnect = new DBConnect();
		Queue<String> trackinglist = new LinkedList<String>();
		
		trackinglist = (Queue<String>)request.getAttribute("lastfive");

		writer.println("\n\t\t\t\t\t<div class=\"last-5-items\">");
		writer.println("\t\t\t\t\t\t<p>" +
				"\n\t\t\t\t\t\t\tLast Viewed Items" + "</p>");
		if (trackinglist.size() > 0) {
			try {
				Connection connection = dbConnect.getInstance();
				for (String element : trackinglist) {
					Statement statement = connection.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM product WHERE pid=\"" + element + "\"");
					result.next();
					writer.println("\n\t\t\t\t\t\t<img class =\"track-pic\" src=\"" + result.getString(8) + "\">");
					
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		writer.println("\t\t\t\t\t</div>");
	}
}
