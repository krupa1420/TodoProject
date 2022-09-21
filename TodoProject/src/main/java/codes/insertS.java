package codes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class insertS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String task = request.getParameter("task");  
        String date = request.getParameter("date");  
        int uid = Integer.parseInt(request.getParameter("id"));
        
        
        try
        {  
        	 Class.forName("com.mysql.jdbc.Driver");
        	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","");
             PreparedStatement ps=con.prepareStatement("insert into todo(task,date,uid) values(?,?,?)");  
             
             ps.setString(1, task);
             ps.setString(2,date);  
             ps.setInt(3,uid);  
            
             int i = ps.executeUpdate();  
             if(i>0)  
            	 response.sendRedirect("./todoS");

        }
        catch (Exception ex)
        {
             ex.printStackTrace();
        }  
        out.close();  
	}

}
