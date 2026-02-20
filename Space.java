import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Space
 */
@WebServlet("/Space")
public class Space extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Space() {
        super();
    }

    private Connection getDBConnection() throws SQLException, ClassNotFoundException {
        String dbUrl = System.getenv("RDS_DB_URL");
        String dbUser = System.getenv("RDS_DB_USER");
        String dbPassword = System.getenv("RDS_DB_PASS");

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            throw new RuntimeException("Missing database environment variables! Check RDS_DB_URL, RDS_DB_USER, RDS_DB_PASS.");
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("<html><head><title>Display Data</title><style>"
                + "#tib{max-width: 400px;\r\n"
                + "    margin: 0 auto;\r\n"
                + "    padding: 20px;\r\n"
                + "    background-color: rgba(255, 255, 255, 0.9);\r\n"
                + "    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
                + "    border-radius: 5px;\r\n"
                + "    margin-top: 100px; }</style></head><body>");

        try (Connection connection = getDBConnection()) {
            
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM park")) {

                out.println("<div id='tib'><table border='1'><tr><th>Car NO</th><th> Category</th><th>Time</th><th>Date</th><th>Mobile NO</th></tr>");

                while (resultSet.next()) {
                    String id = resultSet.getString("num");
                    String type = resultSet.getString("type");
                    String time = resultSet.getString("time"); 
                    String date = resultSet.getString("date");
                    String mobi = resultSet.getString("mob"); 

                    out.println("<tr><td>" + id + "</td><td>" + type + "</td><td>" + time + "</td><td>" + date + "</td><td>" + mobi + "</td></tr>");
                }

                out.println("</table></div>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } 

        out.println("</body></html>");
    }    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}