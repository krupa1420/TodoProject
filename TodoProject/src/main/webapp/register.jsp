<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*,java.util.*"%>
    <%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.PreparedStatement"%>
	<%@page import="java.sql.DriverManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	String pass=request.getParameter("pass");
	Integer id=Integer.parseInt(request.getParameter("id"));
	try
	{
         	Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");
           Statement st=conn.createStatement();
           int i=st.executeUpdate("insert into user(id,name,email,password) values('"+id+"','"+name+"','"+email+"','"+pass+"')");
           if(i>0)  
           {
        	   RequestDispatcher rd = request.getRequestDispatcher("index.html");
        	    rd.forward(request, response);
           }
     }
     catch(Exception e)
     {
        System.out.print(e);
        e.printStackTrace();
     }
 %>
</body>
</html>