//Form Controller servlet is responsible for retrieving the data from the form and sending it to the database
//Servlet then FORWARDS to the order details page

package servlets;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBConnect;

public class FormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Queue<String> cart = (Queue<String>)session.getAttribute("cartListKey");
		cart.clear();
		
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
		
		try {
			Connection connection = DBConnect.getInstance();
			Statement statement = connection.createStatement();
			
			String sql = "INSERT INTO orders (ID, FirstName, LastName, Address, ZipCode, City, State, PhoneNumber, Email, CreditCard, Quantity, ShippingMethod) "+
						"VALUES (UUID_SHORT(), '" + firstName + "', '" + lastName + "', '" + address + "', '" + zip + "', '" + city + "', '" + state + "', '" + phone + "', '" + email + "', '" + card + "', " + quantity + ", '" + shipping + "')";
			System.out.println(sql);
			statement.executeUpdate(sql);

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("firstkey", firstName);
		request.setAttribute("lastkey", lastName);
		request.setAttribute("addresskey", address);
		request.setAttribute("zipkey", zip);
		request.setAttribute("citykey", city);
		request.setAttribute("statekey", state);
		request.setAttribute("phonekey", phone);
		request.setAttribute("emailkey", email);
		request.setAttribute("quantitykey", quantity);
		request.setAttribute("shippingkey", shipping);
		request.setAttribute("cardkey", card);
		
		request.getRequestDispatcher("/orderdetails").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
