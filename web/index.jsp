<%-- 
    Document   : index
    Created on : May 13, 2021, 7:12:51 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

<head>
  <link rel="stylesheet" href="css/index.css">
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <title>Sign in</title>
</head>

<body>
    <p class="sign" align="center"> Who's Not Here Today?</p> 
    <p align="center"> An Attendance Tool </p>
  <div class="main">
    <p class="sign" align="center">Login Teach!</p>
    <form class="form1" method="POST" action="LogInValidation">
      <input class="un " name="un" type="text" align="center" placeholder="Username">
      <input class="pass" name="password" type="password" align="center" placeholder="Password">
      <input class="submit" type="submit" value="Log In">
      <br>
      <br>
      <center><div class="error">${message}</div></center>
      <div class="forgot">
      <a href="forgot_password.jsp">Forgot Password? So, teachers forget too.. </a>
     
      </div>
          </form>
   
    </div>
     
</body>

</html>
