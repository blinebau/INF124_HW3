//Servlet responsible for storing pid's when user presses add cart button

package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBConnect;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = (String)request.getAttribute("pid");

		System.out.println("Cart Servlet called, pid:" + value);
		HttpSession session = request.getSession(true);
		
		Queue<String> cartlist = new LinkedList<String>();
		
		if (session.isNew()) {
			if (value != null) {
				System.out.println("new session value:" + value);
				cartlist.add(value);
			}
		}
		else {
			cartlist = new LinkedList<String>((Queue<String>)session.getAttribute("cartListKey"));	
			if (value != null) {
				cartlist.add(value);
			}
			
		}
		
		System.out.println("Cart Tracker Servlet successful added: " + value);
		
		session.setAttribute("cartListKey", cartlist);
		
		System.out.println("Cart list so far:");
		
		if (cartlist.size() > 0) {
			for (String element : cartlist) {
				System.out.println(element);
			}
		}
		
		
		request.setAttribute("cart", cartlist);
	}

}
