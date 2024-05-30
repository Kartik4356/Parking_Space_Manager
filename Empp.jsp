<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<html>
<head>
    <title>Data Entry Form</title>
    <style type="text/css">
    
    h1{
     
    margin: 0 auto;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.53);
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    margin-top: 10px; 
    }
    #min {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.5);
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    margin-top: 100px; 
}

	#tb { 
	max-width: 155px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    margin-top: 105px; 
    text-align: center;
}

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

   <h1>Employee Work Details</h1><br>

<form  action="" method="post">
   <div id="min">
    Employee name : <input type="text" name="name" ><br><br>
    
    <button type="submit" >GO</button>
    </div>
</form>


<%
		if (request.getMethod().equalsIgnoreCase("post")) {
        String nam = request.getParameter("name");
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adlab", "root", "");

            String query = "SELECT * FROM emp_data WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nam);
           
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                out.println("<div id='tb'><table border='1'>");
                out.println("<tr><th>Name</th><th>Time</th><th>Operation</th><th>Entry's done</th></tr>");

                do {
                    out.println("<tr>");
                    out.println("<td>" + resultSet.getString("name") + "</td>");
                    out.println("<td>" + resultSet.getString("time") + "</td>");
                    out.println("<td>" + resultSet.getString("op") + "</td>");
                    out.println("</tr>");
                    
                } while (resultSet.next());

                out.println("</table></div>");
            } else { %>
<script type="text/javascript">
                alert("User not found");
             </script>           <% }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
        	out.println("Error: " + e.getMessage()); }
		}
    
%>


</body>
</html>