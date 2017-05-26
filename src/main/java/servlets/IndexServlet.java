package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnect;

public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{ 
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		printProducts(writer);
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
	} 
	
	private void printProducts(PrintWriter writer) {
		Connection connection = DBConnect.getInstance();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM product");
			writer.println("\n\t\t\t\t\t<div class=\"item-box\">");
			for(int i = 1; i <= 3; i++) {
				writer.println("\t\t\t\t\t\t<div class=\"item-row" + i + "\">");
				for(int j = 1; j <= 4; j++) {
					result.next();
					writer.println("\t\t\t\t\t\t\t<div class=\"item-individual\">");
					writer.println("\t\t\t\t\t\t\t\t<h4>" + result.getString(2) + "</h4>");
					writer.println("\t\t\t\t\t\t\t\t<div class=\"item-image-container\">");
					//writer.print("<a href=\"product.html#product1\">");
					writer.println("\t\t\t\t\t\t\t\t\t<img class =\"item-pic\" src=\"" + result.getString(8) + "\">");
					writer.println("\t\t\t\t\t\t\t\t</div>");
					writer.println("\t\t\t\t\t\t\t\t<p>" + "Price: $" + result.getInt(4) + "</p>");
					writer.println("\t\t\t\t\t\t\t</div>");
				}
				writer.println("\t\t\t\t\t\t</div>");
			}
			writer.println("\t\t\t\t\t</div>");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
