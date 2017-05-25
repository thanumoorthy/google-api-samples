<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

session.setAttribute("userName",null);
session.setAttribute("email",null);
session.setAttribute("familyName",null);
session.setAttribute("gender",null);
session.setAttribute("googleplus",null);
session.setAttribute("picture",null);

session.invalidate();

response.sendRedirect("home.jsp");


%>    
    
   