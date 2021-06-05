
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/dashboard.css">
        <link rel="stylesheet" href="css/mark.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
        <script>
           
            var studentNames = new Array();
            var studentIds = new Array();
            <%
                String studentNames[] = (String[]) request.getAttribute("studentNames");
                String studentIds[] = (String[]) request.getAttribute("studentIds");
                for (int i = 0; i < studentNames.length; i++) {
            %>
            studentNames[<%=i%>] = "<%=studentNames[i]%>";
            studentIds[<%=i%>] = "<%=studentIds[i]%>";
            <%}%>
            "use strict";
            var countdownArea;
            var namesArea;
            let interval;
            let count = studentNames.length;
            var height;
            let listener;
            var absentTable;
            var presentTable;
            var tableParent;
            var presentTableParent;
            var absentees = 0;
            var presentees = 0;
            var absented = false;
            var speed=1000;
            document.addEventListener("DOMContentLoaded", function () {
                listener = document.getElementById('listen');
                document.getElementById("sub").disabled = true;
                speed = document.getElementById("speed").value;
                absentTable = document.getElementById("absentTable");
                presentTable = document.getElementById("presentTable");
                tableParent = document.getElementById("tableParent");
                presentTableParent =  document.getElementById("presentTableParent");
                countdownArea = document.querySelector('.countdown');
                namesArea = document.querySelector('.names');
                height = countdownArea.getBoundingClientRect().height;

            });

            function start() {
                if(document.getElementById("start").innerHTML == "Start"){
                    document.getElementById("start").innerHTML = "Restart";
                
                count = 0;
                namesArea.style.transform = 'translateY(0)';
                createTimer();
            }
            else{
                count =0;
                 namesArea.style.transform = 'translateY(0)';
                createTimer();
                
                for(var i=0;i<absentees;i++){
                    absentTable.deleteRow(1);
                }
                for(var i=0;i<presentees;i++){
                    presentTable.deleteRow(1);
                }
                for( var i=0; i< studentNames.length;i++){
                    document.getElementById(studentIds[i].toString()).value = "Present";
                }
                presentees=0;
                absentees=0;
            }

            
            }
            function pause() {
                clearInterval(interval);
            }
            function resume() {
                createTimer();
            }
            function pauseAndResume(){
                console.log("hi");
                pause();
                setSpeed();
                resume();
            }
            function setSpeed(){
                speed = document.getElementById("speed").value;
            }
            document.onkeydown = function (evt) {
                
                if (evt.key == " ") {
                    absented = true;
                    absentees++;
                    document.getElementById(studentIds[count].toString()).value = "Absent";
                    
                    var row = absentTable.insertRow(absentees);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    var cell3 = row.insertCell(2);
                    cell1.innerHTML = studentNames[count];
                    cell2.innerHTML = studentIds[count];
                    cell3.innerHTML = "Absent";
                    tableParent.scrollTop = tableParent.scrollHeight;

                } else if (evt.key == "ArrowDown") {
                    count = count - 2;
                } else if (evt.key == "ArrowUp") {
                    count++;
                }
            }
            function createTimer() {
//                clearInterval(interval);
                   
                interval = setInterval(() => {
                    
                    if(absented==false){
                        presentees++;
                        var row = presentTable.insertRow(presentees);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    var cell3 = row.insertCell(2);
                    cell1.innerHTML = studentNames[count];
                    cell2.innerHTML = studentIds[count];
                    cell3.innerHTML = "Present";
                    presentTableParent.scrollTop = presentTableParent.scrollHeight;
                    }
                    count++; // calculate the offset and apply it
                    const offset = height * count;
                    var translateValue = "translateY(-" + offset + "px)";
                    namesArea.style.transform = translateValue; // what happens when countdown is done
                    absented=false;
                    if (count >= studentNames.length) {
                        // go to the next episode
                        clearInterval(interval);
                        absented=true;
                         document.getElementById("sub").disabled = false;
                    }
                }, speed*1000);
                
            }
        </script>
        <title> Your Dashboard </title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
        </style> 

    </head>
    <body>

        <div class="container" id="listen">
            <div class="header"><center>Dashboard</center></div>
            <div class="multi-button">
                <button><a href='LogInValidation'>Home</a></button>
                <button class="act"><a href='MarkAttendance'>Mark</a></button>
                <button><a href='Modify'>Modify</a></button>
                <button><a href='View'>View</a></button>
            </div>
            <div style="padding-top:1%"></div>
            <div class style="display: flex; justify-content: space-between ">
                <div id="tableParent" style="overflow:scroll;height:270px;overflow:auto; width:30%">
                <table class="w3-table-all" id="absentTable"  >
                    <thead>

                        <tr class="w3-purple">
                            <th style="position: sticky;
                                top: 0;
                                background: purple;
                                ">Name</th>
                            <th style="position: sticky;
                                top: 0;
                                background: purple;">Number</th>
                            <th style="position: sticky;
                                top: 0;background: purple;">Status</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="attendance">
<div class="bg">
                <div class="carousel">

                    <span class="countdown" >

                        <span class="names" >
                            <%

                                for (int i = 0; i < studentNames.length; i++) {%>
                            <span>
                                <%=studentNames[i]%> 
                                <br>
                                <text style="font-size: 30px;" >
                                <%=studentIds[i]%>
                                <text>
                            </span>
                            <br>
                            <%}%>
                        </span>

                    </span>


                </div>
                        
            </div>
            </div>
             <div id="presentTableParent" style="overflow:scroll;height:270px;overflow:auto; width:30%">
                <table class="w3-table-all" id="presentTable"  >
                    <thead>

                        <tr class="w3-purple">
                            <th style="position: sticky;
                                top: 0;
                                background: purple;
                                ">Name</th>
                            <th style="position: sticky;
                                top: 0;
                                background: purple;">Number</th>
                            <th style="position: sticky;
                                top: 0;background: purple;">Status</th>
                        </tr>
                    </thead>
                </table>
            </div>
            </div>
            

           
            <div class="buttons">
                Speed: <input type="number" id="speed"  min="1" max="25" value="1" onchange="pauseAndResume()">
            <button onclick="start()" id="start">Start</button>
            <button onclick="pause()" >Pause</button>
            <button onclick="resume()">Resume</button>
            </div>
            <form action="SetAttendance" method="POST">
                <%for (int i = 0; i < studentIds.length; i++) {%>
                <input type="hidden" id="<%=studentIds[i]%>" name="<%=studentIds[i]%>" value="Present">
                <%}%>
                <div class="sub">
                <input id="sub" type="submit" value="Mark Attendance">
                </div>
            </form>
            <!--</div>-->
            
        </div>
        <div class="floating-text">
            Who's Not Here Today
        </div>
    </body>
</html>