

<%@page import="org.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/dashboard.css">
        <link rel="stylesheet" href="css/mark.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" type="text/css" href="http://davidwalsh.name/dw-content/jquery-ui-css/custom-theme/jquery-ui-1.7.2.custom.css">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
        <script>
            <%
                HashMap<String, String[]> course_date_map = (HashMap<String, String[]>) request.getAttribute("course_date_map");
                JSONObject coursedatejson = new JSONObject(course_date_map);
                
                String courseNames[] = (String[]) request.getAttribute("courseNames");
                String courseCodes[] = (String[]) request.getAttribute("courseCodes");
                String courseIds[] = (String[]) request.getAttribute("courseIds");
                HashMap<String, String[]> course_day_map = (HashMap<String, String[]>) request.getAttribute("course_day_map");
                JSONObject coursedayjson = new JSONObject(course_day_map);
                HashMap<String, String[]> course_student_id_map = (HashMap<String, String[]>) request.getAttribute("course_student_id_map");
                JSONObject coursestudentidjson = new JSONObject(course_student_id_map);
                HashMap<String, String[]> course_student_name_map = (HashMap<String, String[]>) request.getAttribute("course_student_name_map");
                JSONObject coursestudentnamejson = new JSONObject(course_student_name_map);
                HashMap<String, HashMap<String, String[]>> present = (HashMap<String, HashMap<String, String[]>>) request.getAttribute("complete1");
                JSONObject presentees = new JSONObject(present);
                HashMap<String, HashMap<String, String[]>> absent = (HashMap<String, HashMap<String, String[]>>) request.getAttribute("complete1");
                JSONObject absentees = new JSONObject(absent);
            %>
            course_date_map = <%=coursedatejson.toString()%>;
            course_day_map = <%=coursedayjson.toString()%>;
            course_student_id_map = <%=coursestudentidjson.toString()%>;
            course_student_name_map = <%=coursestudentnamejson.toString()%>;
            let courseIds = new Array();
            let courseNames = new Array();
            let courseCodes = new Array();
            <% for (int i = 0; i < courseIds.length; i++) {%>
            courseIds[<%=i%>] = "<%=courseIds[i]%>";
            <%}%>
            <% for (int i = 0; i < courseCodes.length; i++) {%>
            courseCodes[<%=i%>] = "<%=courseCodes[i]%>";
            <%}%>
            <% for (int i = 0; i < courseCodes.length; i++) {%>
            courseCodes[<%=i%>] = "<%=courseCodes[i]%>";
            <%}%>
            var present = <%=presentees.toString()%>
            var absent = <%=absentees.toString()%>
            var rows = 0;
            var selectedCourseID;
            var clear = false;
            function courseSelect() {
                document.getElementById("toClass").value=selectedValue;
                
                 document.getElementById("head1").innerHTML="Name";
                    document.getElementById("head2").innerHTML="ID";
                    document.getElementById("head3").innerHTML="Status";
                var selectedValue = document.getElementById("slct").value;
                let arr1 = new Array();
                let arr2 = new Array();
                arr1 = course_student_id_map[selectedValue];
                arr2 = course_student_name_map[selectedValue];
                for (var i = 0; i < arr1.length; i++) {
                    var x = document.getElementById("studentslct");
                    var option = document.createElement("option");
                    option.text = arr1[i] + " " + arr2[i];
                    option.value = arr1[i];
                    x.add(option);

                }
                dateSet();

            }
            function fillStudents() {
                var presentTable = document.getElementById("presentTable");
                for (var i = 0; i < rows; i++) {
                    presentTable.deleteRow(1);
                    clear = true;
                }
                if (clear == true) {
                    rows = 0;
                    clear = false;
                }
                console.log(document.getElementById("date").value);
                var selectedValue = document.getElementById("slct").value;
                let arr1 = new Array();
                let arr2 = new Array();
                arr1 = course_student_id_map[selectedValue];
                arr2 = course_student_name_map[selectedValue];

                let status = {};


                selectedCourseID = document.getElementById("slct").value;

                for (var i = 0; i < course_date_map[selectedCourseID].length; i++) {
                    var date = document.getElementById("date").value;
                    var presents = present[selectedCourseID][date.toString()];
                    for (var j = 0; j < arr1.length; j++) {

                        if (presents.includes(arr1[j])) {
                            status[arr1[j]] = "Present";
                        } else {
                            status[arr1[j]] = "Absent";
                        }
                    }
                }
                console.log(status);
                for (var i = 0; i < arr1.length; i++) {

                    presentTable = document.getElementById("presentTable");
                    var row = presentTable.insertRow(i + 1);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    var cell3 = row.insertCell(2)
                    cell1.innerHTML = arr2[i];

                    cell2.innerHTML = arr1[i];
                    cell3.innerHTML = status[arr1[i]];
                    rows++;
                }
            }

            function dateSet() {
                var select = document.getElementById("slct");
                var selectedValue = select.value;
                let arr = new Array();
                console.log(selectedValue);
                arr = course_day_map[selectedValue];
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i] == "MONDAY") {
                        arr[i] = 1;
                    } else if (arr[i] == "TUESDAY") {
                        arr[i] = 2;
                    } else if (arr[i] == "WEDNESDAY") {
                        arr[i] = 3;
                    } else if (arr[i] == "THURSDAY") {
                        arr[i] = 4;
                    } else if (arr[i] == "FRIDAY") {
                        arr[i] = 5;
                    } else if (arr[i] == "SATURDAY") {
                        arr[i] = 6;
                    } else if (arr[i] == "SUNDAY") {
                        arr[i] = 7;
                    }
                }
                recreate(arr);
            }

            function onlyTheseWeekDays(arr) {
                var days = [];
                if (arr instanceof Array) {
                    days = arr;
                } else if (!isNaN(Number(arr))) {
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
            function recreate(arr) {
                $("#date").datepicker("destroy");
                jQuery(document).ready(function () {
                    jQuery('#date').datepicker({
                        //minDate: new Date(2010, 0, 1),
                        //maxDate: new Date(2010, 5, 31),
                        dateFormat: 'yy-mm-dd',
                        constrainInput: true,
                        beforeShowDay: onlyTheseWeekDays(arr)
                    });
                });
            }
            function studentSelect() {
                
                var presentTable = document.getElementById("presentTable");
                for (var i = 0; i < rows; i++) {
                    presentTable.deleteRow(1);
                    clear = true;
                }
                if (clear == true) {
                    rows = 0;
                    clear = false;
                }
                var selectedValue = document.getElementById("studentslct").value;
              
                console.log("selected value: " + selectedValue);
                selectedCourseID = document.getElementById("slct").value;
                var dateStatus = {};
                var courseDates = course_date_map[selectedCourseID];
                console.log(present[selectedCourseID]['2021-06-01']);
                console.log(courseDates);

                for (var i = 0; i < courseDates.length; i++) {
                    var date = courseDates[i];
                    console.log(date);
                    if (present[selectedCourseID][date].includes(selectedValue)) {
                        dateStatus[courseDates[i]] = "Present";
                    } else {
                        dateStatus[courseDates[i]] = "Absent";
                    }
                }
                let arr1 = new Array();
                let arr2 = new Array();
                arr1 = course_student_id_map[selectedCourseID];
                arr2 = course_student_name_map[selectedCourseID];
                  document.getElementById("head1").innerHTML="Date";
                    document.getElementById("head2").innerHTML="Status";
                    document.getElementById("head3").innerHTML="";
                for (var i = 0; i < courseDates.length; i++) {
                  
                    presentTable = document.getElementById("presentTable");
                    var row = presentTable.insertRow(i + 1);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);

                    cell1.innerHTML = courseDates[i];

                    cell2.innerHTML = dateStatus[courseDates[i]];

                    rows++;
                }

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
                <button><a href='LogInValidation'>Home</a></button>
                <button><a href='MarkAttendance'>Mark</a></button>
                <button><a href='Modify'>Modify</a></button>
                <button  class="act">View</button>
            </div>
            <!--<div class="datas" style="display:flex; justify-content: space-between">-->
            <center>
 <div style="padding-top:2%"></div>
                <div class="select">
                    <select onchange="courseSelect()" name="slct" id="slct">
                        <option selected disabled>Choose a Course</option>
                        <%

                            for (int i = 0; i < courseNames.length; i++) {
                        %>
                        <option value="<%=courseIds[i]%>"><%=courseNames[i]%> - <%=courseCodes[i]%></option>
                        <% }%>
                    </select>

                </div>
                     <div style="padding-top:1%"></div>
                <div class="select">
                    <select onchange="studentSelect()" name="studentslct" id="studentslct">
                        <option selected disabled>Choose a Student</option>
                    </select>

                </div>
                      <div style="padding-top:1%"></div>
                <input type="text" name="date" id="date" readonly="readonly" size="12" onchange="fillStudents()" />
                 
                <div style="padding-top:1%"></div>
                <form action="EmailToClass" method="POST">
                   <input class="toClass" id="toClass" name="toClass" type="hidden" value=""/>
                   <input type="submit" value="Email">
                </form>
              
                <div style="padding-top:1%"></div>
                <div id="tableParent" style="overflow:scroll;height:230px;overflow:auto; width:100%">
                    
                    <table class="w3-table-all" id="presentTable"  >
                        <thead>

                            <tr class="w3-purple">
                                <th id="head1" style="position: sticky;
                                    top: 0;
                                    background: purple;
                                    ">Name</th>
                                <th  id="head2" style="position: sticky;
                                    top: 0;
                                    background: purple;
                                    ">ID</th>
                                <th  id="head3" style="position: sticky;
                                    top: 0;
                                    background: purple;">Status</th>

                            </tr>
                        </thead>
                    </table>
                </div>
            </center>
        </div>

        <div class="floating-text">
            Who's Not Here Today
        </div>
    </body>
</html>
