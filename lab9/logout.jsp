<%-- 
    Document   : logout
    Created on : 19-Jan-2024, 12:33:24 pm
    Author     : himanshugulechha
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.Timestamp"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ page import="jakarta.servlet.ServletException, jakarta.servlet.annotation.WebServlet, jakarta.servlet.http.HttpServlet, jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse, jakarta.servlet.http.HttpSession"%>

<%
    // Check if the user is logged in
    HttpSession session1 = request.getSession(false);
    if (session1 != null && session1.getAttribute("username") != null) {
        String username = (String) session1.getAttribute("username");
        Date loginTime = (Date) session1.getAttribute("loginTime");
        Timestamp formattedLoginTime = new java.sql.Timestamp(loginTime.getTime());
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String formattedLoginTime = sdf.format(loginTime);
        // Update logout time in the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Login", "root", "");
         
            String query = "UPDATE user_sessions SET logout_time = ? WHERE username = ? AND login_time=?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                pst.setString(2, username);
                pst.setTimestamp(3, formattedLoginTime);
                pst.executeUpdate();
                System.out.println("Logout"+new Timestamp(System.currentTimeMillis()));
                
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Invalidate the session
        session1.invalidate();
    }
    
    // Redirect to the login page
    response.sendRedirect("index.html"); // Change to the appropriate page
%>