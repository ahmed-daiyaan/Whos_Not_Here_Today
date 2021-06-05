/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
@WebServlet(urlPatterns = {"/SetAttendance"})
public class SetAttendance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SetAttendance</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SetAttendance at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        Course course = null;
        int present =0;
        int absent =0;
        Database database = (Database) request.getSession().getAttribute("database");
        for(int i=0; i< database.teacher.courses.size();i++){
            if(database.teacher.active_class.course_id.equals(database.teacher.courses.get(i).course_id)){
                course = database.teacher.courses.get(i);
            }
        }
         for(int i=0;i<course.students.size();i++){
        System.out.println(request.getParameter(course.students.get(i).student_id));
        }
        for(int i=0;i<course.students.size();i++){
            String student_id = course.students.get(i).student_id;
            String course_id = course.course_id;
            String teacher_id = database.teacher.teacher_id;
            
            LocalDate date = (LocalDate) request.getSession().getAttribute("date");
            String status = request.getParameter(student_id);
            if(status.equals("Present"))
                present++;
            else
                absent++;
            database.markAttendance((i+1), student_id, course_id, teacher_id, date, status);
        }
        request.setAttribute("teacher_name", database.teacher.teacher_name);
        request.setAttribute("course_name", database.teacher.active_class.course_name );
        request.setAttribute("no_present",present );
        request.setAttribute("no_absent",absent );
        request.setAttribute("date",request.getSession().getAttribute("date") );
          RequestDispatcher ds = request.getRequestDispatcher("markdone.jsp");
        ds.include(request, response);
        ds.forward(request, response);
        
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
