//SessionTracking servlet is responsible for storing the items viewed for each unique user. Used to implement the last 5 items viewed feature

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

public class SessionTracking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = (String)request.getAttribute("pid");
		HttpSession session = request.getSession(true);
		
		Queue<String> trackinglist = new LinkedList<String>();
		Queue<String> cartlist = new LinkedList<String>();
		
		if (session.isNew()) {
			System.out.println("new track session");
			if (value != null) {
				trackinglist.add(value);
			}
			session.setAttribute("cartListKey", cartlist);
		}
		else {
			trackinglist = new LinkedList<String>((Queue<String>)session.getAttribute("trackingListKey"));
			if (value != null) {
				trackinglist.add(value);
			}
			
			while (trackinglist.size() > 5) {
				trackinglist.remove();
			}
		}
		
		//System.out.println("SessionTracker Servlet successful added: " + value);
		
		session.setAttribute("trackingListKey", trackinglist);
		
		System.out.println("Tracking list so far:");
		
		if (trackinglist.size() > 0) {
			for (String element : trackinglist) {
				System.out.println(element);
			}
		}
		
		
		request.setAttribute("lastfive", trackinglist);
	}

}
