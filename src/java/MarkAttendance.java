/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(urlPatterns = {"/MarkAttendance"})
public class MarkAttendance extends HttpServlet {

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

        boolean activeClass = setAttributes(request);
        if(activeClass)
        {
         RequestDispatcher ds = request.getRequestDispatcher("mark.jsp");
        ds.include(request, response);
        ds.forward(request, response);
        }
        else{
            RequestDispatcher ds = request.getRequestDispatcher("select_mark.jsp");
        ds.include(request, response);
        ds.forward(request, response);
        }
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MarkAttendance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MarkAttendance at " + request.getContextPath() + "</h1>");
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

    private boolean setAttributes(HttpServletRequest request) {
        Database database = (Database) request.getSession().getAttribute("database");
        String selectedClass = (String) request.getSession().getAttribute("active_class");
        if (selectedClass.equals("true")) {
            request.getSession().setAttribute("date", LocalDate.now());
            request.setAttribute("course_name", database.teacher.active_class.course_name);
            request.setAttribute("day", database.teacher.active_class.day);
            request.setAttribute("start_time", database.teacher.active_class.start_time);
            request.setAttribute("end_time", database.teacher.active_class.start_time);
            Course markActiveClass = null;
            for (int i = 0; i < database.teacher.courses.size(); i++) {
                if (database.teacher.courses.get(i).course_id.equals(database.teacher.active_class.course_id)) {
                    markActiveClass = database.teacher.courses.get(i);
                }
            }
            if (markActiveClass != null) {
                String studentNames[] = new String[markActiveClass.students.size()];
                String studentIds[] = new String[markActiveClass.students.size()];
                for (int i = 0; i < markActiveClass.students.size(); i++) {
                    studentNames[i] = markActiveClass.students.get(i).student_name;
                    studentIds[i] = markActiveClass.students.get(i).student_id;
                }
                request.setAttribute("studentNames", studentNames);
                request.setAttribute("studentIds", studentIds);

            }
            return true;
        } else {
            HashMap<String,String[]> course_day_map = new HashMap<>();
            List<Course> courses = database.teacher.courses;
            List<Timetable> timetable = database.teacher.timetable;
            String courseNames[] = new String[courses.size()];
            String courseIds[] = new String[courses.size()];
            String courseCodes[] = new String[courses.size()];
            for(int i=0;i < courseIds.length;i++){
                String coursedays[] = new String [7];
                int index=0;
                courseNames[i] = courses.get(i).course_name;
                courseIds[i] = courses.get(i).course_id;
                courseCodes[i]= courses.get(i).course_code;
                for(int j=0;j<timetable.size();j++){
                   
                    if(timetable.get(j).course_id.equals(courseIds[i])){
                        coursedays[index] = timetable.get(j).day;
                    }
                }
                
                course_day_map.put(courseIds[i], (String[]) coursedays);
                
            }
            request.setAttribute("courseNames",courseNames);
            request.setAttribute("courseCodes",courseCodes);
            request.setAttribute("courseIds",courseIds);
            request.setAttribute("course_day_map",course_day_map);
            return false;
        }
    }
}
