<%-- 
    Document   : Dashboard
    Created on : May 13, 2021, 10:44:55 PM
    Author     : ahmed
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link rel="stylesheet" href="css/dashboard.css">
       <link rel="stylesheet" type="text/css" href="http://davidwalsh.name/dw-content/jquery-ui-css/custom-theme/jquery-ui-1.7.2.custom.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
       <script>
/* create an array of days which need to be disabled */
var disabledDays = ["1-1-2011","1-8-2011","1-15-2011"];

   
    var courseIds = new Array();
    var courseDays = new Array();
    let course_day_map;
<%
    String courseNames[] = (String[]) request.getAttribute("courseNames");
    String courseIds[] = (String[]) request.getAttribute("courseIds");
    HashMap<String,String[]> course_day_map = (HashMap<String,String[]>) request.getAttribute("course_day_map");
    JSONObject jsonObj = new JSONObject(course_day_map);
    
    %>
    course_day_map = <%=jsonObj.toString()%>;
    

 <% for (int i=0;i<courseIds.length;i++){ %>
    courseIds[<%=i%>] = "<%=courseIds[i]%>";
    <%}%>

function courseSelect(){
    var select = document.getElementById("slct");
    var selectedValue = select.value;
    let arr =  new Array();
    console.log(selectedValue);
    arr=course_day_map[selectedValue];
    for(var i=0;i< arr.length;i++){
        if(arr[i]=="MONDAY"){
            arr[i]=1;
        }
        else if(arr[i]=="TUESDAY"){
            arr[i]=2;
        }
         else if(arr[i]=="WEDNESDAY"){
            arr[i]=3;
        }
         else if(arr[i]=="THURSDAY"){
            arr[i]=4;
        }
         else if(arr[i]=="FRIDAY"){
            arr[i]=5;
        }
         else if(arr[i]=="SATURDAY"){
            arr[i]=6;
        }
         else if(arr[i]=="SUNDAY"){
            arr[i]=7;
        }
    }
    recreate(arr);
}

function onlyTheseWeekDays(arr) {
    var days = [];
    if (arr instanceof Array) {
        days = arr;
    }
    else if (!isNaN(Number(arr))) {
        days.push(arr);
    }
    return function (date) {
        var dayOfWeek = date.getDay(),
            i;
        for (i = 0; i < days.length; i += 1) {
            if (days[i] === dayOfWeek) {
                return [true];
            }
        }
        return [false];
    };
}
function recreate(arr){
    $("#date").datepicker("destroy");
    jQuery(document).ready(function() {
  jQuery('#date').datepicker({
    //minDate: new Date(2010, 0, 1),
    //maxDate: new Date(2010, 5, 31),
    dateFormat: 'yy-mm-dd',
    constrainInput: true,
    beforeShowDay: onlyTheseWeekDays(arr)
  });
});
}

</script>
        <title> Your Dashboard </title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
        </style> 
        
    </head>
    <body>
        <div class="container">
            <div class="header"><center>Dashboard</center></div>
<div class="multi-button">
  <button ><a href='LogInValidation'>Home</a></button>
  <button class="act"><a href='MarkAttendance'>Mark</a></button>
                <button><a href='Modify'>Modify</a></button>
                <button>View</button>
</div>
   
            <center>
  <form action="SetClass" method="POST">
      <div style="padding-top:10%"></div>
            <div class="select">
  <select onchange="courseSelect()" name="slct" id="slct">
    <option selected disabled>Choose a Course</option>
    <% 
    
    for(int i=0;i< courseNames.length;i++)
    {
    %>
    <option value="<%=courseIds[i]%>"><%=courseNames[i]%></option>
    <% } %>
  </select>
  
  
            </div>
  <div style="padding-top:3%"></div>
   <input type="text" name="date" id="date" readonly="readonly" size="12" />
             <br>
  <br>
  <input type="submit" value="Mark">
  </form>
  </center>
</div>
            
        <div class="floating-text">
            Who's Not Here Today
        </div>
    </body>
</html>
