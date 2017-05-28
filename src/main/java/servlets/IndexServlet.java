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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{ 
		PrintWriter writer = response.getWriter();
		RequestDispatcher header = request.getRequestDispatcher("header.html");
		header.include(request, response);
		printMainIntro(writer);
		printTracker(writer);
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
					writer.println("\t\t\t\t\t\t\t\t\t<a href=\"product?" + "pid=" + result.getString(1)+ "\">");
					//writer.println("\t\t\t\t\t\t\t\t\t<a href=\"product?" + "pid=" + result.getString(1)+ " onclick=\"sessiontracking?param=1234\">");
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
	
	private void printTracker(PrintWriter writer) {
		writer.println("\n\t\t\t\t\t<div class=\"last-5-items\">");
		writer.println("\t\t\t\t\t\t<p>" +
				"\n\t\t\t\t\t\t\tLast Viewed Items" + "</p>");
		writer.println("\t\t\t\t\t</div>");
	}
}
