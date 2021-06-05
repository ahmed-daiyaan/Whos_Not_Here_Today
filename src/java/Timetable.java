
import java.time.LocalTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed
 */
public class Timetable {

    String course_id;
    LocalTime start_time;
    LocalTime end_time;
    String day;
    String course_name;
    
    Timetable(String course_id,
            String course_name,
            String day,
            LocalTime start_time,
            LocalTime end_time) {
        this.course_id = course_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.day = day;
        this.course_name = course_name;

    }
}
