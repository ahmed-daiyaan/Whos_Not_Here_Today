
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed
 */
public class Database {

    Connection connection;
    Statement statement;
    Statement statement2;
    Statement statement3;
    Statement statement4;
    Statement statement5;
    Statement statement6;
    Statement statement7;
    Statement statement8;
    Statement statement9;
    ResultSet result;
    Teacher teacher;

    public void connect() {
        try {
            String dbURL = "jdbc:derby://localhost:1527/teachers;";
            String user = "root";
            String pwd = "root";
            connection = DriverManager.getConnection(dbURL, user, pwd);
            statement = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement4 = connection.createStatement();
            statement5 = connection.createStatement();
            statement6= connection.createStatement();
            statement7 = connection.createStatement();
            statement8 = connection.createStatement();
            statement9 = connection.createStatement();
            if (connection != null) {
                System.out.println("Connected to Database");
            } else {
                System.out.println("Unable to connect to Database");
            }
        } catch (SQLException exception) {
            System.out.println("Database Connection Error: " + exception.toString());
        }
    }

    public boolean validateLogIn(String uname, String password) {
        try {
            result = statement.executeQuery("SELECT * FROM ROOT.TEACHER WHERE TEACHER_EMAIL = '" + uname + "' AND PASSWORD='" + password + "'");
            if (result.next()) {
                teacher = new Teacher(
                        result.getString("TEACHER_ID"),
                        result.getString("TEACHER_NAME"),
                        result.getString("TEACHER_EMAIL")
                );
                return true;
            } else {
                return false;
            }
        } catch (SQLException exception) {
            System.out.println("Database Exception: " + exception.toString());
            return false;
        }
    }

    public void getTeacherDetails() {
        try {

            result = statement.executeQuery("SELECT * FROM TIMETABLE WHERE TEACHER_ID = '" + teacher.teacher_id + "'");
            while (result.next()) {
                teacher.addTimetableSchedule(
                        result.getString("ID"),
                        result.getString("COURSE_NAME"),
                        result.getString("DAY"),
                        result.getTime("START_TIME").toLocalTime(),
                        result.getTime("END_TIME").toLocalTime()
                );
            }

            result = statement.executeQuery("SELECT * FROM COURSE WHERE TEACHER_ID = '" + teacher.teacher_id + "'");
            while (result.next()) {
                List<String> student_id = new ArrayList<>();
                List<String> student_name = new ArrayList<>();
                List<String> student_phoneno = new ArrayList<>();
                List<String> student_email = new ArrayList<>();
                List<String> student_proctor_email = new ArrayList<>();
                List<String> student_proctor_phoneno = new ArrayList<>();
                List<String> student_parent_phone = new ArrayList<>();
                ResultSet studentResult;
                studentResult = statement2.executeQuery("SELECT * FROM COURSE C, STUDENT S, STUDENT_COURSE SC WHERE C.COURSE_ID = SC.COURSE_ID AND SC.STUDENT_ID = S.STUDENT_ID AND C.COURSE_ID ='" + result.getString("COURSE_ID") + "'");
                while (studentResult.next()) {
                    student_id.add(studentResult.getString("STUDENT_ID"));
                    student_name.add(studentResult.getString("STUDENT_NAME"));
                    student_phoneno.add(studentResult.getString("PHONE_NO"));
                    student_email.add(studentResult.getString("EMAIL_ID"));
                    student_proctor_email.add(studentResult.getString("PROCTOR_EMAIL"));
                    student_proctor_phoneno.add(studentResult.getString("PROCTOR_PHONE"));
                    student_parent_phone.add(studentResult.getString("PARENT_PHONE"));
                }
               
                List<String> attendance_student_id = new ArrayList<>();
                List<String> attendance_teacher_id = new ArrayList<>();
                List<String> status = new ArrayList<>();
                List<String> attendance_course_id = new ArrayList<>();
                List<LocalDate> date = new ArrayList<>();
                ResultSet attendanceResult;
                System.out.println("SELECT * FROM ATTENDANCE WHERE COURSE_ID = '" + result.getString("COURSE_ID") + "'");
                attendanceResult = statement3.executeQuery("SELECT * FROM ATTENDANCE WHERE COURSE_ID = '" + result.getString("COURSE_ID") + "'");
                while (attendanceResult.next()) {
                    
                    date.add(attendanceResult.getDate("ATTENDANCE_DATE").toLocalDate());
                    attendance_student_id.add(attendanceResult.getString("STUDENT_ID"));
                    attendance_teacher_id.add(attendanceResult.getString("TEACHER_ID"));
                    attendance_course_id.add(attendanceResult.getString("COURSE_ID"));
                    status.add(attendanceResult.getString("STATUS"));
                }
                teacher.addCourse(
                        result.getString("COURSE_ID"),
                        result.getString("COURSE_NAME"),
                        result.getString("COURSE_CODE"),
                        student_id,
                        student_name,
                        student_phoneno,
                        student_email,
                        student_proctor_email,
                        student_proctor_phoneno,
                        student_parent_phone,
                       
                        attendance_student_id,
                        attendance_teacher_id,
                        status,
                        attendance_course_id,
                        date
                );
            }

        } catch (SQLException exception) {
            System.out.println("Database Exception: " + exception.toString());
        }
    }
    public void updateAttendance(LocalDate date, String student_id, String course_id, String status){
        try {
            System.out.println("UPDATE ATTENDANCE SET STATUS='"+status+"' WHERE STUDENT_ID = '"+student_id+"' AND ATTENDANCE_DATE = '"+date+"' AND COURSE_ID = '"+course_id+"'");
        statement4.executeUpdate("UPDATE ATTENDANCE SET STATUS='"+status+"' WHERE STUDENT_ID = '"+student_id+"' AND ATTENDANCE_DATE = '"+date+"' AND COURSE_ID = '"+course_id+"'");
     } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        }
    public ResultSet getCourseAttendance(String course_id, LocalDate date){
        ResultSet attend = null;        
        try {

        attend = statement5.executeQuery("SELECT * FROM ATTENDANCE WHERE COURSE_ID = '"+course_id+"' AND ATTENDANCE_DATE = '"+date.toString()+"'");
   
                } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return attend;
                }
    public String getEmail(String studentId){
        try {
            
            statement6.executeUpdate("SELECT STUDENT_EMAIL FROM STUDENT WHERE STUDENT_ID = '"+studentId+'"');
            if(result.next()){
                return result.getString("STUDENT_EMAIL");
            }
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return "";
    }
    public String getClassEmails(String courseId){
        try {
            String studentEmails="";
            String studentId;
            ResultSet courseStudents= statement7.executeQuery("SELECT * FROM STUDENT_COURSE WHERE COURSE_ID ='"+courseId+"'");
            while(courseStudents.next()){
               studentId = courseStudents.getString("STUDENT_ID");
               ResultSet students = statement8.executeQuery("SELECT * FROM STUDENT WHERE STUDENT_ID = '"+"'");
               while(students.next()){
                   studentEmails+= students.getString("STUDENT_EMAIL")+";";
               }
                
            }
            return studentEmails;
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        return "";
    }
    public void markAttendance(
            int sno,
            String student_id,
            String course_id,
            String teacher_id,
            LocalDate attendance_date,
            String status
    ) {

        try {
            
            statement3.executeUpdate("INSERT INTO ATTENDANCE VALUES(" + sno + ",'" + attendance_date + "','" + student_id + "','" + status + "','" + teacher_id + "','" + course_id + "')");
        } catch (SQLException exception) {
            System.out.println(exception.toString());
        }
        Attendance attendance = new Attendance(
                student_id,
                course_id,
                teacher_id,
                attendance_date,
                status);
       for(int i=0;i< this.teacher.courses.size();i++){
           if(this.teacher.courses.get(i).course_id.equals(course_id)){
               this.teacher.courses.get(i).addAttendance(attendance);
           }
       }
    }
}
