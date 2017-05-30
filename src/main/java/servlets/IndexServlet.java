//Index Servlet 

package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnect;

public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("/sessiontracking").include(request, response);
		
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		printMainIntro(writer);
		printTracker(writer, request);
		printProducts(writer);
		RequestDispatcher footer = request.getRequestDispatcher("footer.html");
		footer.include(request, response);
		
		
	} 
	
	private void printProducts(PrintWriter writer) {
		DBConnect dbConnect = new DBConnect();
		try {
			Connection connection = dbConnect.getInstance();
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
					writer.println("\t\t\t\t\t\t\t\t\t<a href=\"product?" + "pid=" + result.getString(1)+ "\">");
					writer.println("\t\t\t\t\t\t\t\t\t\t<img class =\"item-pic\" src=\"" + result.getString(8) + "\">");
					writer.println("\t\t\t\t\t\t\t\t\t</a>");
					writer.println("\t\t\t\t\t\t\t\t</div>");
					writer.println("\t\t\t\t\t\t\t\t<p>" + "Price: $" + result.getInt(4) + "</p>");
					writer.println("\t\t\t\t\t\t\t</div>");
				}
				writer.println("\t\t\t\t\t\t</div>");
			}
			writer.println("\t\t\t\t\t</div>");
		} catch(SQLException e) {
			writer.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void printMainIntro(PrintWriter writer) {
		writer.println("\n\t\t\t\t\t<div class=\"content-top\">");
		writer.println("\t\t\t\t\t\t<div class=\"content-top-text\">");
		writer.println("\t\t\t\t\t\t\t<h2>The Definitive One Stop Shop For All Your Fashion Needs</h2>");
		writer.println("\t\t\t\t\t\t\t<p>" + 
				"\n\t\t\t\t\t\t\t\tBy collaborating with artisanal weavers from all around the world, we bring the best designer clothing here at Weaves & Crafts. "
				+ "\n\t\t\t\t\t\t\t\tFind all the latest styles that stay in style regardless of the season. Shop for some of the most sought after designer clothing"
				+ "\n\t\t\t\t\t\t\t\tand give your wardrobe a makeover. Pick something striking from our large catalog below, you are bound to find something stylish"
				+ "\n\t\t\t\t\t\t\t\tfor your next outing." + "</p>");
		writer.println("\t\t\t\t\t\t</div>");
		writer.println("\t\t\t\t\t<div class=\"bar\"></div>");
		writer.println("\t\t\t\t\t<br>");
		writer.println("\t\t\t\t\t</div>");
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
				writer.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		writer.println("\t\t\t\t\t</div>");
	}
}
