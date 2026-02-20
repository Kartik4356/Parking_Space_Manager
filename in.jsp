<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<html>
<head>
    <title>Data Entry Form</title>
    <style type="text/css">
    #min {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.62);
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    margin-top: 100px;
    
    
}

button {
    color: black;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    text-align: center;
    }
    
    
    </style>
</head>
<body>


<form  action="" method="post">
   <div id="min">
    Car number : <input type="text" name="num" placeholder="XX-12-XX-4685" required pattern="[A-Z]{2}-[0-9]{2}-[A-Z]{2}-[0-9]{4}" ><br><br>
    Category : <select name="type">
        <option value="Two_Wheeler">Two Wheeler</option>
        <option value="Four_Wheeler">Four Wheeler</option>
    </select><br><br>
    Time : <input type="time" name="time" id="tiin" required><br><br>
    Date : <input type="date" name="date" id="dtin" required><br><br>
     Contact : <input type="tel" name="mob" required><br><br><br>
    <button type="submit" >IN</button>
    </div>
</form>



<%
        if (request.getMethod().equalsIgnoreCase("post")) {
        String num = request.getParameter("num");
        String type = request.getParameter("type");
        String time = request.getParameter("time");
        String date = request.getParameter("date");
        String mob = request.getParameter("mob");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String dbUrl = System.getenv("RDS_DB_URL");
            String dbUser = System.getenv("RDS_DB_USER");
            String dbPassword = System.getenv("RDS_DB_PASS");

            if (dbUrl == null || dbUser == null || dbPassword == null) {
                throw new RuntimeException("Missing database environment variables! Check RDS_DB_URL, RDS_DB_USER, RDS_DB_PASS.");
            }

            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String query = "INSERT INTO park (num,type,time,date,mob) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, num);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, mob);



            int rowsAffected = preparedStatement.executeUpdate();
            Integer entryCount = (Integer) session.getAttribute("entryCount");
            if (entryCount == null) {
                entryCount = 1;
            } else {
                entryCount++;
            }
            session.setAttribute("entryCount", entryCount);
            if (rowsAffected > 0) {
                %>
                <script type="text/javascript">
                alert("Vehicle entered successfully!");
             </script>
             <%
                 } else {
                     %>
                     <script type="text/javascript">
                         alert("Error while entering data!! Check format");
                      </script>
                      <%
                 }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            %>
             <script type="text/javascript">
             alert("Error while entering data!!");
          </script>  
          <%      }
        }
    
%>


<script> 
document.getElementById('dtin').valueAsDate = new Date();
var currentTime = new Date();
var hours = currentTime.getHours();
var minutes = currentTime.getMinutes();
var formattedTime = (hours < 10 ? '0' : '') + hours + ':' + (minutes < 10 ? '0' : '') + minutes;
document.getElementById('tiin').value = formattedTime;
 </script>

</body>
</html>