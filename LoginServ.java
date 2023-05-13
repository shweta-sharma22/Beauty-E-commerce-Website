/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import javax.servlet.http.HttpSession;


public class LoginServ extends HttpServlet {
    
    String vUserName,vPassword;
    OracleConnection oconn;
    OraclePreparedStatement ost;
    
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
 out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HELLO USER</title>");
            out.println("</head>");
            out.println("<body>");

    vUserName = request.getParameter("txtusername");
    vPassword = request.getParameter("txtpassword");
    
    
    if (isValidUser(vUserName, vPassword)) {
      // If the user is valid, redirect to the home page
      response.sendRedirect("http://localhost:8080/Beauty%20Blush/bootstrap-3.3.7-dist/homepage.html");
    } else {
      // If the user is invalid, show an error message
//        request.setAttribute("error","Invalid Username or Password. Please try again." );
//        request.getRequestDispatcher("http://localhost:8080/Beauty%20Blush/PageServ/LoginError.jsp").include(request, response);
     
response.sendRedirect("http://localhost:8080/Beauty%20Blush/bootstrap-3.3.7-dist/LoginError.html");
    }
  }
  
  private boolean isValidUser(String username, String password) {
    boolean isValid = false;
    try {
     DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
     oconn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-OAM93US4:1521:ORCL","SHWETA","SHARMA");
     String q = "SELECT * FROM REGISTRATION WHERE USERNAME = ? AND PASSWORD = ?";
     ost =(OraclePreparedStatement) oconn.prepareStatement(q);
      ost.setString(1, vUserName);
      ost.setString(2, vPassword);

      java.sql.ResultSet result = ost.executeQuery();
      
      isValid = result.next();

      result.close();
      ost.close();
      oconn.close();
    } catch (SQLException ex) {
       System.out.println(ex.toString());
       Logger.getLogger(LoginServ.class.getName()).log(Level.SEVERE, null, ex);
    }
    return isValid;
  }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}