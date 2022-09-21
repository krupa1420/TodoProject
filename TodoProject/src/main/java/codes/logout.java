package codes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;


public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");  
	        
		 Cookie ck1=new Cookie("uname","");//deleting value of cookie  
		 ck1.setMaxAge(0);//changing the maximum age to 0 seconds  
		 response.addCookie(ck1);//adding cookie in the response  
		    
		 Cookie ck2=new Cookie("uid",""); 
		 ck1.setMaxAge(0);
		 response.addCookie(ck2); 
		 response.sendRedirect("index.html");
	}

	
}
