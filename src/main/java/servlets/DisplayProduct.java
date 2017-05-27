package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

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
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		printProduct(writer, request.getParameterMap());
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
	}
	
	private void printProduct(PrintWriter writer, Map<String, String[]> parameters) {
		
		String pid = parameters.get("pid")[0];
		Connection connection = DBConnect.getInstance();
		
		try {
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
			writer.println("\t\t\t\t\t\t<button>Add to cart</button>");
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
}
