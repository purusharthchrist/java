/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Base64;



/**
 *
 * @author himanshugulechha
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/lab8", "root", "");

            PreparedStatement pst = con.prepareStatement("SELECT * FROM log WHERE username=? AND password=?");
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("loginTime", new Date());
                
                
                byte[] imgData = rs.getBytes("image"); // blob field 
                String encode = Base64.getEncoder().encodeToString(imgData);
                session.setAttribute("image", encode);
                
                PreparedStatement pst1=con.prepareStatement("SELECT * FROM user_sessions WHERE username=? and logout_time=?");
                pst1.setString(1, username);
                pst1.setString(2, "2000-01-01 00:00:00");
                ResultSet rs1=pst1.executeQuery();
                System.out.println(rs1);
                if(!rs1.next()){
                insertLoginRecord(con, username);
                response.sendRedirect("welcome.jsp");}
                else{PrintWriter out = response.getWriter();
                    out.println("<meta http-equiv='refresh' content='3;URL=index.html'>");//redirects after 3 seconds
   out.println("<p style='color:red;'>User already logged in!</p>");}
                
            } else {
                // Login failed
                PrintWriter out = response.getWriter();
                out.println("Invalid username or password");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertLoginRecord(Connection con, String username) throws Exception {
        String query = "INSERT INTO user_sessions (username, login_time) VALUES (?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
        }
    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
}