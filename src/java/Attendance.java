
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed
 */
public class Attendance {

    
    String student_id;
    String course_id;
    String teacher_id;
    LocalDate attendance_date;
    String status;

    Attendance(
            String student_id,
            String course_id,
            String teacher_id,
            LocalDate attendance_date,
            String status) {
        
        this.student_id = student_id;
        this.course_id = course_id;
        this.teacher_id = teacher_id;
        this.attendance_date = attendance_date;
        this.status = status;
    }
}
