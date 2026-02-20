import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Validate() {
        // Default constructor
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String as = "login";    
        int lf = 0;
        
        if (isValidUser(username, password)) {
            Date loginTime = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedLoginTime = dateFormat.format(loginTime);
            
            try (Connection connection = getDBConnection()) {
                String query = "INSERT INTO emp_data(name, time, op) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, formattedLoginTime);
                    preparedStatement.setString(3, as);
                    preparedStatement.setInt(4, lf);
                    preparedStatement.executeUpdate();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage()); 
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("error.html");
        }
    }

    private boolean isValidUser(String username, String password) {
        try (Connection connection = getDBConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet resultSet = ps.executeQuery()) {
                    return resultSet.next(); 
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}