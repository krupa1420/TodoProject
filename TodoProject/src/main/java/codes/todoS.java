package codes;

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


public class todoS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		
		out.println("<body style='background-color:powderblue;'>");
        out.println("<div align='center'>");
        out.println("<h1 style='color:Orange;'>ToDo</h1><br><br>");
		out.println("You are successfully logged in!");
		
		Cookie ck[]=request.getCookies();
		String n=ck[0].getValue();
		int id= Integer.parseInt(ck[1].getValue());
		 out.print("<br> Welcome "+n);//printing name  
		 out.println("<a href='./logout' > LogOut</a>");
		
        try 
        {  
        	Class.forName("com.mysql.jdbc.Driver");
       	 	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","");
       	 	PreparedStatement ps = con.prepareStatement("select * from todo where uid=?");
       	 	ps.setInt(1,id);
       	 	
       	 	ResultSet rs= ps.executeQuery();
            
            out.println("<br><br>");  
            out.println("<table border=1 cellspacing=10 cellpadding=5 style='border: 1px solid black;'>");  
            out.println("<tr><th>Id</th><th>Task</th><th>Date</th><th>Status</th><tr>");  
            int i=1;
            while (rs.next()) 
            {      
                String date = rs.getString("date");  
                String task = rs.getString("task");
                out.println("<tr><td>" + i + "</td><td>" + task + "</td><td>" + date + "</td>"
                		+ "<td><a href='./done?uid=" +id+ "&task=" +task+ "'>Done</a></td></tr>  ");
                i=i+1;
            }  
            out.println("</table>");  
            out.println("</body>");  
            con.close();  
          }  
          catch (Exception e) 
          {  
            out.println("error");  
          } 
        out.print("<br><br>");  
        out.print("<form action='./insertS' method=post>");
        out.print("Task: <input type='text' name='task'><br><br>"); 
        out.print("Date: <input type='date' name='date'><br><br>");
        out.print("Your Id: <input type='text' name='id'><br><br>");  
	    out.print("<input type='submit' value='ToDo'>");  
	    out.print("</form>");          
	}

	

}
