/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmed
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Teacher {
    
    Teacher(String teacher_id, String teacher_name, String teacher_email) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.teacher_email = teacher_email;
        this.timetable = new ArrayList<>();
        this.courses = new ArrayList<>();
        
    }
    
    public boolean checkforActiveClass() {
        String day = LocalDate.now().getDayOfWeek().name().toUpperCase();
        LocalTime now = LocalTime.now();
        for (int i = 0; i < this.timetable.size(); i++) {
            if ((now.isAfter(timetable.get(i).start_time)) && (now.isBefore(timetable.get(i).end_time)) && (day.equals(timetable.get(i).day))) {
                this.active_class = timetable.get(i);
                return true;
            }
        }
        return false;
    }

    public void setActiveClass(String course_id) {
        for (int i = 0; i < this.timetable.size(); i++) {
            if (timetable.get(i).course_id.equals(course_id)) {
                this.active_class = timetable.get(i);
            }
        }
    }
    
    public void addTimetableSchedule(String course_id,
            String course_name,
            String day,
            LocalTime start_time,
            LocalTime end_time
    ) {
        Timetable schedule = new Timetable(
                course_id, course_name, day, start_time, end_time
        );
        this.timetable.add(schedule);
        
    }

    public void addAttendance(Attendance attendance) {
        for (int i = 0; i < this.courses.size(); i++) {
            if (this.courses.get(i).course_id.equals(attendance.course_id)) {
                this.courses.get(i).addAttendance(attendance);
            }
        }
        
    }
    
    public void addCourse(String course_id, String course_name, String course_code, List<String> student_id,
            List<String> student_name,
            List<String> student_phoneno,
            List<String> student_email,
            List<String> student_proctor_email,
            List<String> student_proctor_phoneno,
            List<String> student_parent_phone,
           
            List<String> attendance_student_id,
            List<String> attendance_teacher_id,
            List<String> status,
            List<String> attendance_course_id,
            List<LocalDate> date
    ) {
        Course course = new Course(course_id, course_name, course_code, student_id,
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
        this.courses.add(course);
    }
    String teacher_id;
    String teacher_name;
    String teacher_email;
    Timetable active_class;
    List<Timetable> timetable;
    List<Course> courses;
    
}
