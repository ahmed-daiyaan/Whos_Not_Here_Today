/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = (Database) request.getSession().getAttribute("database");
        request.setAttribute("teacher_name", database.teacher.teacher_name);
        boolean activeClass = database.teacher.checkforActiveClass();
        if (activeClass) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            request.getSession().setAttribute("active_class","true");
            request.setAttribute("course_id", database.teacher.active_class.course_id);
            request.setAttribute("course_name", database.teacher.active_class.course_name);
            request.setAttribute("start_time", database.teacher.active_class.start_time.format(formatter));
            request.setAttribute("end_time", database.teacher.active_class.end_time.format(formatter));
            request.setAttribute("day", database.teacher.active_class.day);
            request.setAttribute("mark_tag", "Mark");
        } else {
            request.getSession().setAttribute("active_class","false");
            request.setAttribute("mark_tag", "Mark for other class");
        }
        RequestDispatcher ds = request.getRequestDispatcher("dashboard.jsp");
                ds.include(request, response);
                ds.forward(request, response);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Database database = new Database();
        database.connect();
        try (PrintWriter out = response.getWriter()) {
            String uname = request.getParameter("un");
            String password = request.getParameter("password");

            boolean userExists = database.validateLogIn(uname, password);
            if (userExists) {
                database.getTeacherDetails();
                setRequestAttributes(database, request);

                RequestDispatcher ds = request.getRequestDispatcher("dashboard.jsp");
                ds.include(request, response);
                ds.forward(request, response);

            } else {
                request.setAttribute("message", "Oops... your credentials don't match, try again");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.include(request, response);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogInValidation</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("</body>");
            out.println("</html>");
        }

    }

    private void setRequestAttributes(Database database, HttpServletRequest request) {
        request.setAttribute("teacher_name", database.teacher.teacher_name);
        boolean activeClass = database.teacher.checkforActiveClass();
        if (activeClass) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            request.getSession().setAttribute("active_class","true");
            request.setAttribute("course_id", database.teacher.active_class.course_id);
            request.setAttribute("course_name", database.teacher.active_class.course_name);
            request.setAttribute("start_time", database.teacher.active_class.start_time.format(formatter));
            request.setAttribute("end_time", database.teacher.active_class.end_time.format(formatter));
            request.setAttribute("day", database.teacher.active_class.day);
            request.getSession().setAttribute("mark_tag", "Mark");
        } else {
            request.getSession().setAttribute("active_class","false");
            request.getSession().setAttribute("mark_tag", "Mark for other class");
        }
        request.getSession().setAttribute("database", database);
    }

}
