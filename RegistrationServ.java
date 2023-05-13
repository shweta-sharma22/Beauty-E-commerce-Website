package testpack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
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


public class RegistrationServ extends HttpServlet {

    String vUserName,vPassword,vConfirmPass,vEmail,vName,vAge,vDOB,vGender,vLocation,vPhone;
    OracleConnection oconn;
    OraclePreparedStatement ost;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registration</title>");            
            out.println("</head>");
            out.println("<body>");
            vUserName = request.getParameter("tbUserName");
            vPassword = request.getParameter("tbPassword");
            vConfirmPass = request.getParameter("tbConfirmPassword");
            vEmail = request.getParameter("tbEmail");
            vName = request.getParameter("tbName");
            vAge = request.getParameter("tbAge");
            vDOB = request.getParameter("tbDOB");
            vGender = request.getParameter("tbGender");
            vGender = (vGender.equals("true"))?(vGender = "M"):(vGender = "F");
            vLocation = request.getParameter("ddlCity");
            vPhone = request.getParameter("PhoneNumber");
            out.println("<h1>Your Info:</h1>");
            out.println("<h2>Username or Email &nbsp;&nbsp;&nbsp;&nbsp; "+vUserName+"</h2>");
            out.println("<h2>Password &nbsp;&nbsp;&nbsp;&nbsp; "+vPassword+"</h2>");
            out.println("<h2>Confirm Password &nbsp;&nbsp;&nbsp;&nbsp; "+vConfirmPass+"</h2>");
            out.println("<h2>Name &nbsp;&nbsp;&nbsp;&nbsp; "+vName+"</h2>");
            out.println("<h2>Age &nbsp;&nbsp;&nbsp;&nbsp; "+vAge+"</h2>");
            out.println("<h2>Date of Birth &nbsp;&nbsp;&nbsp;&nbsp; "+vDOB+"</h2>");
            out.println("<h2>Gender &nbsp;&nbsp;&nbsp;&nbsp; "+vGender+"</h2>");
            out.println("<h2>Location &nbsp;&nbsp;&nbsp;&nbsp; "+vLocation+"</h2>");
            out.println("<h2>Phone Number &nbsp;&nbsp;&nbsp;&nbsp; "+vPhone+"</h2>");
            out.println("<h2 style='color:orange'>Saving the data within database.........</h2>");
             //STEP 1 : REGISTERING OF THE REQUIRED DRIVER WITH THE JAVA PROGRAM
                //Class.forName("oracle.jdbc.OracleDriver");
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                
                 //STEP 2: INSTANTIATING THE CONNECTION OBJECT 
                oconn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-OAM93US4:1521:ORCL","SHWETA","SHARMA");
                
                //STEP 3: CREATING THE QUERY
                String q = "INSERT INTO REGISTRATION(USERNAME,PASSWORD,CONFIRM_PASSWORD,EMAIL,NAME,AGE,DOB,GENDER,LOCATION,PHONE) values(?,?,?,?,?,?,?,?,?,?)";
            
                //STEP 4: INSTANTIATING STATEMENT OBJECT FOR EXECUTING SQL QUERIES
                ost =(OraclePreparedStatement) oconn.prepareStatement(q);
                
                //STEP 5: CHANGING THE DATE FORMAT FROM JAVA DATE  TO ORACLE DATE
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dt.parse(vDOB);
                SimpleDateFormat dt1 = new SimpleDateFormat("dd-MMM-yyyy");
                vDOB = dt1.format(date);
                
                //STEP 6: FILLING THE BLANK VALUES OF THE QUERY MARKED WITH ? 
                ost.setString(1,vUserName);
                ost.setString(2,vPassword);
                ost.setString(3,vConfirmPass);
                ost.setString(4,vEmail);
                ost.setString(5,vName);
                ost.setString(6,vAge);
                ost.setString(7,vDOB);
                ost.setString(8,vGender);
                ost.setString(9,vLocation);
                ost.setString(10,vPhone);
                
                //STEP 7: EXECUTING THE QUERY
                
                int ra = ost.executeUpdate();
                
                out.println("<h2>Rows affected is : " + ra + "</h2>");
                out.println("<h2 style='color:green'>Data saved successfully.........</h2>");
                
                ost.close();
                oconn.close();
            out.println("</body>");
            out.println("</html>");
            response.sendRedirect("http://localhost:8080/Beauty%20Blush/bootstrap-3.3.7-dist/SecurityQuestions.html");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            Logger.getLogger(RegistrationServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RegistrationServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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