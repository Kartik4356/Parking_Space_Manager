import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Register() {
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
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();

         String username = request.getParameter("username");
         String password = request.getParameter("password");
         String id = request.getParameter("id");
         String shift = request.getParameter("shift");

         try (Connection connection = getDBConnection()) {
             String query = "INSERT INTO users (id, username, password, shift) VALUES (?, ?, ?, ?)";
             try (PreparedStatement ps = connection.prepareStatement(query)) {
                 ps.setString(1, id);
                 ps.setString(2, username);
                 ps.setString(3, password);
                 ps.setString(4, shift);
                 ps.executeUpdate();
             }

             out.println("Registration successful!");
         } catch (Exception e) {
             out.println("Error: " + e.getMessage());
         } finally {
             out.close();
         }    
    }
}