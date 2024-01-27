<%-- 
    Document   : Welcome
    Created on : 19-Jan-2024, 12:31:51 pm
    Author     : himanshugulechha
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <%
        HttpSession session1 = request.getSession(false);

        // Check if the user is logged in
        if (session1 != null && session1.getAttribute("username") != null) {
            String username = (String) session1.getAttribute("username");
            Date loginTime = (Date) session1.getAttribute("loginTime");
            String img= String.valueOf(session1.getAttribute("image"));
            // Format the login time using SimpleDateFormat
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedLoginTime = sdf.format(loginTime);

            // Display user information
    %>
            <h2>Welcome, <%= username %></h2>
             <img src="data:image/jpeg;base64,${img}" />
            <p>Login Time: <%= formattedLoginTime %></p>
            <!-- Add your content for the welcome page here -->
            <a href="logout.jsp">Logout</a>
    <%
        } else {
            // Redirect to login page if the user is not logged in
            response.sendRedirect("index.html"); // Change to the appropriate page
        }
    %>
</body>
</html>