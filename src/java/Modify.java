/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/Modify"})
public class Modify extends HttpServlet {

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
        Database database = (Database) request.getSession().getAttribute("database");
        HashMap<String,String[]> course_day_map = new HashMap<>();
        HashMap<String,String[]> course_student_id_map = new HashMap<>();
        HashMap<String,String[]> course_student_name_map = new HashMap<>();
        String[] studentNames;
        String[] studentIds;
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
                System.out.println(courses.get(i).students.size());
                studentNames = new String [courses.get(i).students.size()];
                studentIds = new String [courses.get(i).students.size()];
                for(int k=0;k< courses.get(i).students.size() ;k++){
                    System.out.println(k);
                    studentIds[k] = courses.get(i).students.get(k).student_id;
                    studentNames[k] = courses.get(i).students.get(k).student_name;
                }
                course_student_id_map.put(courses.get(i).course_id, studentIds);
                course_student_name_map.put(courses.get(i).course_id, studentNames);
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
            request.setAttribute("course_student_id_map", course_student_id_map);
            request.setAttribute("course_student_name_map", course_student_name_map);
            RequestDispatcher ds = request.getRequestDispatcher("modify.jsp");
        ds.include(request, response);
        ds.forward(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Modify</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Modify at " + request.getContextPath() + "</h1>");
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

}
