package servlets;

import java.io.IOException; 
import java.io.PrintWriter; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

public class ServletDemo extends HttpServlet {    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{         
		PrintWriter out = response.getWriter();         
		out.println("<html>");         
		out.println("<body>");         
		out.println("<h1>Hello Servlet from WAR file - Maven</h1>");         
		out.println("</body>");         
		out.println("</html>");     
	} 
}