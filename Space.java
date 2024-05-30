

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Space() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	        

	        try {
	           
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adlab", "root", "");

	           Statement statement = connection.createStatement();
	           ResultSet resultSet = statement.executeQuery("SELECT * FROM park");

	            out.println("<div id='tib'><table border='1'><tr><th>Car NO</th><th> Category</th><th>Time</th><th>Date</th><th>Mobile NO</th></tr>");

	            while (resultSet.next()) {
	                String id = resultSet.getString("num");
	                String type = resultSet.getString("type");
	                String time = resultSet.getString("time"); 
	                String date = resultSet.getString("date");
	                int mobi = resultSet.getInt("mob");

	                out.println("<tr><td>" + id + "</td><td>" + type + "</td><td>" + time + "</td><td>" + date + "</td><td>" + mobi + "</td></tr>");
	            }

	            out.println("</table></div>");
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (Exception e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	            e.printStackTrace();
	        } finally {
	            
	        	
	        }

	        out.println("</body></html>");
	    }	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
