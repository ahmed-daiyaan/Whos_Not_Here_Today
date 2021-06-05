<%@page import="java.util.List"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link rel="stylesheet" href="css/dashboard.css">
       <link rel="stylesheet" href="css/dashboard.css">
       <link rel="stylesheet" type="text/css" href="http://davidwalsh.name/dw-content/jquery-ui-css/custom-theme/jquery-ui-1.7.2.custom.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
        <title> Your Dashboard </title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
        </style> 
        <script>
            <%
    String courseNames[] = (String[]) request.getAttribute("courseNames");
    String courseCodes[] = (String[]) request.getAttribute("courseCodes");
    String courseIds[] = (String[]) request.getAttribute("courseIds");
    HashMap<String,String[]> course_day_map = (HashMap<String,String[]>) request.getAttribute("course_day_map");
    JSONObject coursedayjson = new JSONObject(course_day_map);
    HashMap<String,String[]> course_student_id_map = (HashMap<String,String[]>) request.getAttribute("course_student_id_map");
    JSONObject coursestudentidjson = new JSONObject(course_student_id_map);
    HashMap<String,String[]> course_student_name_map = (HashMap<String,String[]>) request.getAttribute("course_student_name_map");
    JSONObject coursestudentnamejson = new JSONObject(course_student_name_map);
    
    %>
    course_day_map = <%=coursedayjson.toString()%>;
    course_student_id_map = <%=coursestudentidjson.toString()%>;
    course_student_name_map = <%=coursestudentnamejson.toString()%>;

 <% for (int i=0;i<courseIds.length;i++){ %>
    courseIds[<%=i%>] = "<%=courseIds[i]%>";
    <%}%>
function studentSelect(){
    var selectedValue = document.getElementById("slct").value;
    let arr1 =  new Array();
    let arr2 = new Array();
        arr1 = course_student_id_map[selectedValue];
        arr2 = course_student_name_map[selectedValue];
       for(var i=0;i< arr1.length;i++){
           var x = document.getElementById("studentslct");
  var option = document.createElement("option");
  option.text = arr1[i] + " " + arr2[i] ;
  option.value = arr1[i];
  x.add(option);
       }
}
function courseSelect(){
    var select = document.getElementById("slct");
    var selectedValue = select.value;
    let arr =  new Array();
    console.log(selectedValue);
    arr=course_day_map[selectedValue];
    studentSelect();
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
function done(){
    alert("Attendance Updated")
}
        </script>
    </head>
    <body>
        <div class="container">
            <div class="header"><center>Dashboard</center></div>
  <div class="multi-button">
                <button><a href='LogInValidation'>Home</a></button>
                <button ><a href='MarkAttendance'>Mark</a></button>
                <button class="act"><a href='Modify'>Modify</a></button>
                <button><a href='View'>View</a></button>
            </div>
            <center>
                <div style="padding-top:5%"></div>
            <form method="POST" action="ChangeStatus">
                
           <div class="select">
  <select onchange="courseSelect()" name="slct" id="slct">
    <option selected disabled>Choose a Course</option>
    <% 
    
    for(int i=0;i< courseNames.length;i++)
    {
    %>
    <option value="<%=courseIds[i]%>"><%=courseNames[i]%> - <%=courseCodes[i]%></option>
    <% } %>
  </select>
  
            </div>
  <div style="padding-top:2%"></div>
            <div class="select">
  <select onchange="studentSelect()" name="studentslct" id="studentslct">
    <option selected disabled>Choose a Student</option>
  </select>
  
            </div>
  <div style="padding-top:2%"></div>
   Date: <input type="text" name="date" id="date" readonly="readonly" size="12" />
             <br>
  <br>
  <div style="padding-top:2%"></div>
  <div class="select">
  <select  name="status" id="status">
    <option selected disabled>Choose Status</option>
    <option value="Present">Present</option>
    <option value="Absent" >Absent</option>
  </select>
  </div>
  <div style="padding-top:2%"></div>
  <input type="submit" value="Mark" onclick="done()">
   </form>
  </center>
        </div>
  
         
        <div class="floating-text">
            Who's Not Here Today
        </div>
    </body>
</html>
