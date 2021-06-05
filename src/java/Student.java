/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmed
 */
public class Student {

    String student_id;
    String student_name;
    String student_phoneno;
    String student_email;
    String student_proctor_email;
    String student_proctor_phone;
    String student_parent_phone;

    Student(String student_id,
            String student_name,
            String student_phoneno,
            String student_email,
            String student_proctor_email,
            String student_proctor_phone,
            String student_parent_phone) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_phoneno = student_phoneno;
        this.student_email = student_email;
        this.student_proctor_email = student_proctor_email;
        this.student_proctor_phone = student_proctor_phone;
        this.student_parent_phone = student_parent_phone;
    }
}
