import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Logout() {
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
        HttpSession session = request.getSession();
        
        Integer entryCount = (Integer) session.getAttribute("entryCount");
        int safeEntryCount = (entryCount != null) ? entryCount : 0;

        String username = (String) session.getAttribute("username");
        Date loginTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedLoginTime = dateFormat.format(loginTime);
        String as = "logout";
        
        try (Connection connection = getDBConnection()) {
            String query = "INSERT INTO emp_data(name, time, op, S_done) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, formattedLoginTime);
                preparedStatement.setString(3, as);
                preparedStatement.setInt(4, safeEntryCount);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); 
        }
        
        session.invalidate();
        response.sendRedirect("Login.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}