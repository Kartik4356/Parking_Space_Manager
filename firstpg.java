

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public firstpg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();

	       

	       

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adlab", "root", "");

	            String itemToCount = "Two_Wheeler";
	            String itemToCount2 = "Four_Wheeler";


	            String query = "SELECT COUNT(*) AS itemCount FROM park WHERE type = ?";
	            String query2 = "SELECT COUNT(*) AS itemCount FROM park WHERE type = ?";

	            PreparedStatement preparedStatement  = connection.prepareStatement(query);
	            PreparedStatement preparedStatement2  = connection.prepareStatement(query2);

	            preparedStatement.setString(1, itemToCount);
	            preparedStatement2.setString(1, itemToCount2);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            ResultSet resultSet2 = preparedStatement2.executeQuery();


	            int itemCount = 0;
	            int itemCount2 = 0;


	            if (resultSet.next()) {
	                itemCount = resultSet.getInt("itemCount");
	            }
	            
	            if (resultSet2.next()) {
	                itemCount2 = resultSet2.getInt("itemCount");
	            }

	            out.println("<html><head><title>Item Count</title><style>"
	            		+ "#tw{    max-width: 240px;\r\n"
	            		+ "    padding: 20px;\r\n"
	            		+ "    background-color: rgba(255, 255, 255, 0.62);\r\n"
	            		+ "    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
	            		+ "    border-radius: 5px;\r\n"
	            		+ "    margin-top: 100px;"
	            		+ "	   margin-left:40px;}"
	            		+ "#fw{    max-width: 240px;\r\n"
	            		+ "	   padding: 20px;\r\n"
	            		+ "	   background-color: rgba(255, 255, 255, 0.62);\r\n"
	            		+ "	    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
	            		+ "	      border-radius: 5px;"
	            		+ "	    margin-top: -222px;"
	            		+ "	     margin-left:856px;}"
	            		+ "  #mi{ padding-left: 170px;"
	            		+ "font-size: 60px;}"
	            		+ "h2,h3{"
	            		+ "   	 max-width:300;"
	            		+ "   	 margin: 0 auto;"
	            		+ "   	 padding: 20px;\r\n"
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

	            
	            resultSet.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	            e.printStackTrace();
	        } 
	        	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
