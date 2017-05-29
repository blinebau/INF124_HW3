//Form Controller servlet is responsible for retrieving the data from the form and sending it to the database
//Servlet then FORWARDS to the order details page

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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = DBConnect.getInstance();
		
		try {
			Statement statement = connection.createStatement();

			//ID = UUID_SHORT()
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String zip = request.getParameter("zip");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String shipping = request.getParameter("shipping");
			String card = request.getParameter("card");
			
			System.out.println(firstName + " " + lastName + " " + quantity + " " + shipping);
			
			String sql = "INSERT INTO orders (ID, FirstName, LastName, Address, ZipCode, City, State, PhoneNumber, Email, CreditCard, Quantity, ShippingMethod) "+
						"VALUES (UUID_SHORT(), '" + firstName + "', '" + lastName + "', '" + address + "', '" + zip + "', '" + city + "', '" + state + "', '" + phone + "', '" + email + "', '" + card + "', " + quantity + ", '" + shipping + "')";
			System.out.println(sql);
			statement.executeUpdate(sql);
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/orderdetails").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}