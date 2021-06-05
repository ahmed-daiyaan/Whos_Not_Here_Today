<%-- 
    Document   : Dashboard
    Created on : May 13, 2021, 10:44:55 PM
    Author     : ahmed
--%>

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
                <button class="act"><a href='LogInValidation'>Home</a></button>
                <button><a href='MarkAttendance'>Mark</a></button>
                <button><a href='Modify'>Modify</a></button>
                <button><a href='View'>View</a></button>
            </div>
            <center><div class="welcome-name">Good Day, ${teacher_name}!</div></center>
            <div class="active">
                <div class="rainbow">
                    <center>

                        <b>${course_name}</b>
                        <br>
                        ${day}
                        <br>
                        ${start_time} - ${end_time}
                        <br>
                        <br>

                        <button class="btn-1"><a href="MarkAttendance">${mark_tag}</a></button>
                    </center>
                </div>
            </div>

            <div class="floating-text">
                Who's Not Here Today
            </div>
    </body>
</html>
