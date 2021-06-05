/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
@WebServlet(urlPatterns = {"/View"})
public class View extends HttpServlet {

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
                Database database = (Database) request.getSession().getAttribute("database");
        HashMap<String, String[]> course_day_map = new HashMap<>();
        HashMap<String, String[]> course_student_id_map = new HashMap<>();
        HashMap<String, String[]> course_student_name_map = new HashMap<>();
        HashMap<String, String[]> course_studentEmail_map = new HashMap<>();
        HashMap<String, String[]> course_studentProctorEmail_map = new HashMap<>();
        HashMap<String, String[]> course_date_map = new HashMap<>();
        
        HashMap<String, HashMap<String, String[]>> complete1 = new HashMap<>();
        HashMap<String, HashMap<String, String[]>> complete2 = new HashMap<>();
        String[] studentNames;
        String[] studentIds;
        String[] studentEmails = null;
        String[] studentProctorEmails = null;
        String[] studentPhoneNos;
        String[] studentProctorPhoneNos = null;
        String[] dates;
        List<Course> courses = database.teacher.courses;
        List<Timetable> timetable = database.teacher.timetable;
        String courseNames[] = new String[courses.size()];
        String courseIds[] = new String[courses.size()];
        String courseCodes[] = new String[courses.size()];
        for (int i = 0; i < courseIds.length; i++) {
            HashMap<String, String[]> present = new HashMap<>();
        HashMap<String, String[]> absent = new HashMap<>();
            List<String> attendanceDates = new ArrayList<>();
            String coursedays[] = new String[7];
            int index = 0;
            courseNames[i] = courses.get(i).course_name;
            courseIds[i] = courses.get(i).course_id;
            courseCodes[i] = courses.get(i).course_code;
            System.out.println(courses.get(i).students.size());
            studentNames = new String[courses.get(i).students.size()];
            studentIds = new String[courses.get(i).students.size()];
            studentEmails = new String[courses.get(i).students.size()];
            studentProctorEmails = new String[courses.get(i).students.size()];
            studentPhoneNos = new String[courses.get(i).students.size()];
            studentProctorPhoneNos = new String[courses.get(i).students.size()];
            for (int k = 0; k < courses.get(i).students.size(); k++) {
                System.out.println(k);
                studentIds[k] = courses.get(i).students.get(k).student_id;
                studentNames[k] = courses.get(i).students.get(k).student_name;
                studentEmails[k] = courses.get(i).students.get(k).student_email;
                studentProctorEmails[k] = courses.get(i).students.get(k).student_proctor_email;
                studentProctorPhoneNos[k] = courses.get(i).students.get(k).student_proctor_phone;
            }
            course_student_id_map.put(courses.get(i).course_id, studentIds);
            course_student_name_map.put(courses.get(i).course_id, studentNames);
            course_studentEmail_map.put(courses.get(i).course_id, studentEmails);
            course_studentProctorEmail_map.put(courses.get(i).course_id, studentEmails);

            System.out.println("Attendace");
            System.out.println(courses.get(i).attendance.size());
            for (int l = 0; l < courses.get(i).attendance.size(); l++) {
                attendanceDates.add(courses.get(i).attendance.get(l).attendance_date.toString());
            }
            Set<String> s = new LinkedHashSet<>(attendanceDates);
            int n = s.size();
            dates = new String[n];

            int p = 0;
            for (String x : s) {
                dates[p++] = x;
            }
            
            List<String> presentees = new ArrayList<>();
            List<String> absentees = new ArrayList<>();
            for (String date : dates) {
                ResultSet result = database.getCourseAttendance(courses.get(i).course_id, LocalDate.parse(date));
                try {
                    while (result.next()) {
                        if (result.getString("STATUS").equals("Present")) {
                            presentees.add(result.getString("STUDENT_ID"));
                        } else {
                            absentees.add(result.getString("STUDENT_ID"));
                        }

                    }
                    present.put(date, presentees.toArray(new String[0]));
                    absent.put(date, absentees.toArray(new String[0]));
                }catch (SQLException exception) {
                    System.out.println(exception.toString());
                }
            }
            
            complete1.put(courses.get(i).course_id, present);
            
            complete2.put(courses.get(i).course_id, absent);

            course_date_map.put(courses.get(i).course_id, dates);

            for (int j = 0; j < timetable.size(); j++) {

                if (timetable.get(j).course_id.equals(courseIds[i])) {
                    coursedays[index] = timetable.get(j).day;
                }
            }

            course_day_map.put(courseIds[i], (String[]) coursedays);

        }

        request.setAttribute("courseNames", courseNames);
        request.setAttribute("courseCodes", courseCodes);
        request.setAttribute("courseIds", courseIds);
        request.setAttribute("course_day_map", course_day_map);
        request.setAttribute("course_student_id_map", course_student_id_map);
        request.setAttribute("course_student_name_map", course_student_name_map);
        request.setAttribute("course_studentEmail_map", course_studentEmail_map);
        request.setAttribute("course_studentProctorEmail_map", course_studentProctorEmail_map);
        request.setAttribute("course_date_map", course_date_map);
        request.setAttribute("course_date_map", course_date_map);
        request.setAttribute("studentProctorPhoneNos", studentProctorPhoneNos );
        request.setAttribute("studentEmails", studentEmails );
        request.setAttribute("studentProctorEmails",studentProctorEmails );
        
        request.setAttribute("complete1", complete1);
        request.setAttribute("complete2", complete2);
        
        response.setContentType("text/html;charset=UTF-8");
        if(courseNames!=null){
        RequestDispatcher ds = request.getRequestDispatcher("view.jsp");
        ds.include(request, response);
        ds.forward(request, response);
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet View</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet View at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
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
