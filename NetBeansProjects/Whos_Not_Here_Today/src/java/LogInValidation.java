/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ahmed
 */
@WebServlet(urlPatterns = {"/LogInValidation"})
public class LogInValidation extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        try (PrintWriter out = response.getWriter()) {
            String uname = request.getParameter("un");
            String password = request.getParameter("password");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogInValidation</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
            String dbURL = "jdbc:derby://localhost:1527/teachers;";
            String user = "root";
            String pwd = "root";
            Connection conn = DriverManager.getConnection(dbURL, user, pwd);
            if(conn!=null){
                System.out.println("Connected to DB");
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM ROOT.CREDENTIALS WHERE USERNAME = '"+uname+"' AND PASSWORD='"+password+"'");
                if(result.next())
                {
                RequestDispatcher ds = request.getRequestDispatcher("Dashboard.html");
                ds.include(request, response);
                }
                else
                {
                     out.println("User does not exist");
                }
                result.close();
                statement.close();
            }
        }
        catch(SQLException e){
            out.println(e.toString());
            
        }
            out.println("</body>");
            out.println("</html>");
        }
        
    }

}
