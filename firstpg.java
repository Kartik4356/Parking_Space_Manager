import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class firstpg
 */
@WebServlet("/firstpg")
public class firstpg extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public firstpg() {
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int itemCount = 0;
        int itemCount2 = 0;

        try (Connection connection = getDBConnection()) {
            
            String itemToCount = "Two_Wheeler";
            String itemToCount2 = "Four_Wheeler";
            String query = "SELECT COUNT(*) AS itemCount FROM park WHERE type = ?";

            // Fetch Two-Wheeler count
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, itemToCount);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        itemCount = resultSet.getInt("itemCount");
                    }
                }
            }
            
            // Fetch Four-Wheeler count
            try (PreparedStatement preparedStatement2 = connection.prepareStatement(query)) {
                preparedStatement2.setString(1, itemToCount2);
                try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
                    if (resultSet2.next()) {
                        itemCount2 = resultSet2.getInt("itemCount");
                    }
                }
            }

            // Output the HTML and CSS Dashboard
            out.println("<html><head><title>Item Count</title><style>"
                    + "#tw{    max-width: 240px;\r\n"
                    + "    padding: 20px;\r\n"
                    + "    background-color: rgba(255, 255, 255, 0.62);\r\n"
                    + "    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
                    + "    border-radius: 5px;\r\n"
                    + "    margin-top: 100px;"
                    + "    margin-left:40px;}"
                    + "#fw{    max-width: 240px;\r\n"
                    + "    padding: 20px;\r\n"
                    + "    background-color: rgba(255, 255, 255, 0.62);\r\n"
                    + "    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
                    + "      border-radius: 5px;"
                    + "    margin-top: -222px;"
                    + "     margin-left:856px;}"
                    + "  #mi{ padding-left: 170px;"
                    + "font-size: 60px;}"
                    + "h2,h3{"
                    + "      max-width:300;"
                    + "      margin: 0 auto;"
                    + "      padding: 20px;\r\n"
                    + "     background-color: rgba(255, 255, 255, 0.62);\r\n"
                    + "     box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
                    + "     border-radius: 23px;\r\n"
                    + "}"
                    + "</style></head><body>");
            
            out.println("<div id='tw'><h1> Two Wheeler's in parking <br><div id='mi'>" + itemCount + "</div></h1></div>");
            out.println("<div id='fw'><h1> Four Wheeler's in parking <br><div id='mi'>" + itemCount2 + "</div></h1></div>");
            out.println("<br><h2>Welcome</h2><br>");
            out.println("<h3>Manage your parking the best and easy way possible without any hassel and loss</h3>");

            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}