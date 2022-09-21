package codes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class done extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Integer id = Integer.parseInt(request.getParameter("uid"));
        String task = request.getParameter("task");
        try
        {
 
        	 Class.forName("com.mysql.jdbc.Driver");
        	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","");
        	 PreparedStatement ps=con.prepareStatement("delete from todo where uid =? and task =?");
        	 ps.setInt(1, id);
        	  ps.setString(2, task);
             int i=ps.executeUpdate();
             if(i>0)
             {
            	 response.sendRedirect("./todoS");
             }
        	
        }
        catch(Exception e) 
        {
        	e.printStackTrace();
        }
       
	}

	

}
