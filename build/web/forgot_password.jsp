<%-- 
    Document   : Forgot_Password
    Created on : May 13, 2021, 10:06:08 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Who's Not Here Today</title>
         <link rel="stylesheet" href="css/index.css">
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    </head>
    <body>
         <p class="sign" align="center"> Who's Not Here Today?</p> 
   
        <div class="main">
    <p class="sign" align="center"> Forgot your password? &#128528</p>
     <p align="center"> Don't worry, there's a way </p>
    <form class="form1" method="POST" action="LogInValidation">
      <input class="email" name="email" type="email" align="center" placeholder="Enter your email">
     
      <br>
      
       <input class="retrieve" type="submit" value="Retrieve Password">
      
          </form>
   
    </div>
    </body>
</html>
