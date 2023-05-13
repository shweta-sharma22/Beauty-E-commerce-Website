<%@page import="oracle.jdbc.OracleResultSetMetaData"%>
<%@page import="oracle.jdbc.OracleStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="oracle.jdbc.OracleResultSet"%>
<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Displaying all Users Details</title>


<%!
    OracleConnection oconn;
    OracleStatement ost;
    OracleResultSet ors;
    String q;
    %>
    
 <style>
                table, tr, td
                {
                    margin:auto;
                    padding: 15px;
                    border: 5px solid;
                    border-color: #feff86;
                    border-collapse: collapse
                }
                th
                {
                    padding: 10px;
                    border: 2px solid;
                    border-color: #feff86;
                    border-collapse: collapse;
                    color: #feff86;
                }
                h1{
                    font-size: 2rem;
                    text-align:center;
                    font-family: monospace;
                }
                h4{
                    font-size: 1.5rem;
                    text-align:center;
                    font-family: monospace;
                }
                button{
                    padding: 5px 7px;
                    background-color: white;
                    font-size: 15px;
                }
                p{
                    text-align: center;
                } 
                a{
                    font-size: 20px;
                    text-decoration: none;
                    color:white;
                    font-family: monospace;
                    text-align: center;
                }
                a:hover{
                    text-decoration: underline;
                }
            </style>
        
    </style>
    </head>
    <body style=" background-color: #d25380">
        <h1>Hello Admin!</h1>
        <h4>Click the buttons below to take respective actions.</h4>
<%
                //STEP 1 : REGISTERING OF THE REQUIRED DRIVER WITH THE JAVA PROGRAM
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                
                //STEP 2: INSTANTIATING THE CONNECTION OBJECT 
                oconn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-OAM93US4:1521:ORCL","SHWETA","SHARMA");
                 //STEP 4: INSTANTIATING STATEMENT OBJECT FOR EXECUTING SQL QUERIES
                ost =(OracleStatement) oconn.createStatement();
                //STEP 3: CREATING THE QUERY
//                q = "select * from ADMIN";
                           
                //STEP 5: EXECUTING THE STATEMENT AND FILL UP THE RESULTSET
//                ors = (OracleResultSet)ost.executeQuery(q);
                
                //STEP 6: GETTING SYSTEM INFORMATION ABOUT THE FETCHED TABLE
//                orsmd = (OracleResultSetMetaData)ors.getMetaData();
                
            %>
 <table>
     <thead>
     </thead>
           <tbody>
               <tr>
                   <form method="POST" action="http://localhost:8080/Beauty%20Blush/PageServ/Registration.jsp">
                     <td><button type="submit">User Details</button></td>
            </form>
               
               
                   <form method="POST" action="http://localhost:8080/Beauty%20Blush/PageServ/SecurityQuestion.jsp">
                     <td><button type="submit">Security Questions</button></td>
            </form>
           
            <form method="POST" action="http://localhost:8080/Beauty%20Blush/PageServ/Feedback.jsp">
                     <td><button type="submit">Feedback Details</button></td>
               </tr>
            </tbody>
            <tfoot></tfoot>
 </table>   
                      <!%
                          oconn.close();
                          ost.close();
                          ors.close();
                          %>
                          <p><a href="http://localhost:8080/Beauty%20Blush/bootstrap-3.3.7-dist/Admin.html">Back to Admin Login</a></p>
    </body>
</html>