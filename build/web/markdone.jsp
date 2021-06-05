<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link rel="stylesheet" href="css/dashboard.css">
       
        <title> Your Dashboard </title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
        </style> 
        
    </head>
    <body>
        <div class="container">
            <div class="header"><center>Dashboard</center></div>
<div class="multi-button">
  <button><a href='LogInValidation'>Home</a></button>
                 <button class="act"><a href='MarkAttendance'>Mark</a></button>
                <button><a href='Modify'>Modify</a></button>
                <button>View</button>
</div>
            <center><div class="welcome-name">Good Day, ${teacher_name}!</div></center>
            <div class="active">
            <div class="rainbow">
                <center>
                   
                    <b>Attendance Marked for ${course_name}</b>
                    <b>Date : ${date}</b><br>
                    <b>No. of Present: ${no_present} </b><br>
                    <b>No. of Absent: ${no_absent}</b>
                 </center>
            </div>
            </div>
          
        <div class="floating-text">
            Who's Not Here Today
        </div>
    </body>
</html>
