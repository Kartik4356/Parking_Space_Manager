

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String id = request.getParameter("id");
	        String shift = request.getParameter("shift");

	        try {
	        	 Class.forName("com.mysql.jdbc.Driver");
	             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adlab", "root", "");

	            String query = "INSERT INTO users (id, username, password, shift) VALUES (?, ?, ?, ?)";
	            try (PreparedStatement ps = connection.prepareStatement(query)) 
	            {
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
