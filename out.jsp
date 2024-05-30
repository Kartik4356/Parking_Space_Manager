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
    
    <button type="submit" >OUT</button>
    </div>
</form>


<%
		if (request.getMethod().equalsIgnoreCase("post")) {
        String num = request.getParameter("num");
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adlab", "root", "");

            String query = "DELETE FROM park WHERE num = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, num);
           
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
            	%>
            	<script type="text/javascript">
                alert("Vehicle Removed successfully!");
             </script>
             <%
            	 } else {
            		 %>
            		 <script type="text/javascript">
                     alert("No such vehicle found!!");
                  </script>
                  <%
            	 }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
        	out.println("Error: " + e.getMessage()); }
		}
    
%>



</body>
</html>
