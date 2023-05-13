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


public class SecurityQueServ extends HttpServlet {

    String vUserName,vQues_1,vQues_2,vQues_3;
    OracleConnection oconn;
    OraclePreparedStatement ost;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registration</title>");            
            out.println("</head>");
            out.println("<body>");
            vUserName = request.getParameter("tbUserName");
            vQues_1 = request.getParameter("tbQues1");
            vQues_2 = request.getParameter("tbQues2");
            vQues_3 = request.getParameter("tbQues3");
            
            out.println("<h1>Your Info:</h1>");
            out.println("<h2>Username: or Email &nbsp;&nbsp;&nbsp;&nbsp; "+vUserName+"</h2>");
            out.println("<h2>Ques1: &nbsp;&nbsp;&nbsp;&nbsp; "+vQues_1+"</h2>");
            out.println("<h2>Ques2: &nbsp;&nbsp;&nbsp;&nbsp; "+vQues_2+"</h2>");
            out.println("<h2>Ques3: &nbsp;&nbsp;&nbsp;&nbsp; "+vQues_3+"</h2>");
            
            out.println("<h2 style='color:orange'>Saving the data within database.........</h2>");
             //STEP 1 : REGISTERING OF THE REQUIRED DRIVER WITH THE JAVA PROGRAM
                //Class.forName("oracle.jdbc.OracleDriver");
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                
                 //STEP 2: INSTANTIATING THE CONNECTION OBJECT 
                oconn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-OAM93US4:1521:ORCL","SHWETA","SHARMA");
                
                //STEP 3: CREATING THE QUERY
                String q = "INSERT INTO SECURITY_QUESTIONS(USERNAME,QUESTION_1,QUESTION_2,QUESTION_3) values(?,?,?,?)";
            
                //STEP 4: INSTANTIATING STATEMENT OBJECT FOR EXECUTING SQL QUERIES
                ost =(OraclePreparedStatement) oconn.prepareStatement(q);
               
                
                //STEP 6: FILLING THE BLANK VALUES OF THE QUERY MARKED WITH ? 
                ost.setString(1,vUserName);
                ost.setString(2,vQues_1);
                ost.setString(3,vQues_2);
                ost.setString(4,vQues_3);
                
                //STEP 7: EXECUTING THE QUERY
                
                int ra = ost.executeUpdate();
                
                out.println("<h2>Rows affected is : " + ra + "</h2>");
                out.println("<h2 style='color:green'>Data saved successfully.........</h2>");
                
                ost.close();
                oconn.close();
            out.println("</body>");
            out.println("</html>");
            response.sendRedirect("http://localhost:8080/Beauty%20Blush/bootstrap-3.3.7-dist/homepage.html");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            Logger.getLogger(SecurityQueServ.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        catch (ParseException ex) {
//            Logger.getLogger(SecurityQueServ.class.getName()).log(Level.SEVERE, null, ex);
//        }
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