package codes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class loginS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        try
        {
        	
        	 Class.forName("com.mysql.jdbc.Driver");
        	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","");
             PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
             ps.setString(1, email);
             ps.setString(2, pass);
             ResultSet rs= ps.executeQuery();
             if(rs.next())
             {
             	String name = rs.getString("name");
             	String uid = rs.getString("id");
             	Cookie ck1=new Cookie("uname",name);//creating cookie object  
             	response.addCookie(ck1);
             	Cookie ck2=new Cookie("uid",uid);//creating cookie object  
             	response.addCookie(ck2);   
                    
                     RequestDispatcher rrs = request.getRequestDispatcher("./todoS");
                     rrs.include(request, response);
                 
                    
             }
             else
             {
                out.println("<center>Username or Password incorrect</center>");
                RequestDispatcher rrs = request.getRequestDispatcher("index.html");
                rrs.include(request, response);
             }
            
             
        	
        }
        catch(Exception e) 
        {
        	e.printStackTrace();
        }
	}

	

}
