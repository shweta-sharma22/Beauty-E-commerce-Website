
<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleResultSetMetaData"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="oracle.jdbc.OracleResultSet"%>
<%@page import="oracle.jdbc.OracleStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Displaying all user Details</title>
        <%!
                OracleConnection oconn;
                OracleStatement ost;
                OraclePreparedStatement opst;
                OracleResultSet ors;
                OracleResultSetMetaData orsmd;
                String q;
                int counter, reccounter;
            %>
            <style>
                table, tr, td
                {
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
                    font-family: monospace;
                }
            </style>
             <script>
                function funDelete()
                {
                    if(confirm("Are you sure you want to delete this user?") === true)
                        return true;
                    else 
                        return false;
                }
             </script>

    </head>
    <body style="background-color: #d25380">
        <div>
            <h1 style="font-weight: bold; font-size: 3rem; font-family: monospace; text-align: center;">User Details</h1>
            <span>
                <form method="POST" action="Registration.jsp">
                    <input type="text" placeholder="&#128269;Enter username" name="tSearch" style="padding: 5px 10px;" required>
                <button type="submit" name="bSearch" style="background-color:#feff86; border-color:#feff86; padding: 5px; ">Search</button>
                </form>
        </span>
    </div>
    <br/>
    <br/>
    <br/> 

    <%
    // STEP 1: REGISTERING THE JSP WITH ORACLE
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    //STEP 2: CREATING THE CONNECTION OBJECT
            oconn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-OAM93US4:1521:ORCL","SHWETA","SHARMA");
            if(request.getParameter("bSearch")!=null)
            {
                String vSearchUser = request.getParameter("tSearch");
        
                //STEP 4: CREATING THE QUERY
                q = "select * from REGISTRATION where username=?";
       
                //STEP 3: CREATING THE STATEMENT
                opst = (OraclePreparedStatement)oconn.prepareStatement(q);
        
                //STEP 5: FILLING UP THE PARAMETERS
                opst.setString(1, vSearchUser);
        
                //STEP 5: Executing the query and populating the resultset
                ors = (OracleResultSet)opst.executeQuery();
                
                //STEP 6: GETTING SYSTEM INFORMATION ABOUT THE FETCHED TABLE
                orsmd = (OracleResultSetMetaData)ors.getMetaData();
                %>    
                <form method="POST" action="Registration.jsp">
                    <button type="submit">Display all Users</button>
                    </form>
                    <%
            }
            else
            {
                           
            //STEP 3: CREATING THE STATEMENT
            ost = (OracleStatement)oconn.createStatement();
           
            //STEP 4: CREATING THE QUERY
            q = "select * from REGISTRATION order by 1";
            
            //STEP 5: Executing the query and populating the resultset
            ors = (OracleResultSet)ost.executeQuery(q);
            
            
            }
            //STEP 6: Getting the Headings
            orsmd = (OracleResultSetMetaData)ors.getMetaData();
        %>
        <table>
            <thead>
                <tr>
                <%
                for(counter = 1; counter <= orsmd.getColumnCount(); counter ++)
                    {
                    %>
                    <th><%=orsmd.getColumnName(counter)%></th>
                    <%
                        }
                        %>
                    <th colspan="9">ACTIONS</th>
                </tr>
            </thead>
            <tbody>
                <%
                    reccounter = 0;
                    while(ors.next())
                    {
                        reccounter++;
                    %>
                <tr>
                    <%
                    for(int i = 1; i <= 10; i ++)
                    {
                %>
                <td><%=ors.getString(i)%></td>
                    <%
                        }
                     %>
                     <!--<td><button type="submit">Update</button></td>-->
            <form method="POST" action="http://localhost:8080/Beauty%20Blush/DeleteUser?tbUserName=<%=ors.getString(1)%>" onsubmit=" return funDelete()">
                     <td><button type="submit">Remove</button></td>
            </form>
                </tr>
                <%
            }
//                  
            %>
    </tbody>
    <tfoot>
    </tfoot>
</table>
<%
   if (reccounter == 0)
            {
            %>
            <h2 style="font-family: monospace; color: yellow">No matching records exists. Try with a better match</h2>
            <%
            }
// ors.close();
//  ost.close();
   oconn.close();
   
 %> 
<form method="POST" action="AdminPanel.jsp">
        <button type="submit" style="margin-top: 15px; padding: 5px 7px; font-size: 13px">Go back</button>
    </form>
    </body>
</html>
