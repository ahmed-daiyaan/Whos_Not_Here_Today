
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
public class Course {

    Course(String course_id, String course_name, String course_code, List<String> student_id,
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
        this.students = new ArrayList<>();
        this.attendance =  new ArrayList<>();
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_code = course_code;
        for (int i = 0; i < student_id.size(); i++) {
            Student student = new Student(
                    student_id.get(i),
                    student_name.get(i),
                    student_phoneno.get(i),
                    student_email.get(i),
                    student_proctor_email.get(i),
                    student_proctor_phoneno.get(i),
                    student_parent_phone.get(i)
            );
            this.students.add(student);
        }
        for (int i = 0; i < attendance_student_id.size(); i++) {
           
            Attendance addAttendance = new Attendance(
                    
                    attendance_student_id.get(i),
                    attendance_course_id.get(i),
                    attendance_teacher_id.get(i),
                    date.get(i),
                    status.get(i)
            );
            
            this.attendance.add(addAttendance);
        }

    }

    public void addAttendance(Attendance newAttendance) {
        this.attendance.add(newAttendance);

    }

    String course_id;
    String course_name;
    String course_code;
    List<Student> students;
    List<Attendance> attendance;
}
