package LoginRegister;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CourseManagementSystem";
    private static final String DB_USERNAME = "ashik";
    private static final String DB_PASSWORD = "password";
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	
    	
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        int semester = Integer.parseInt(request.getParameter("semester"));
        
        
        
     // Check if the user already exists
        if (userExists(username)) {
        	
            // User already exists, display an error message
            request.setAttribute("errorMessage", "Username already exists");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        try {
            // Create a database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL statement
            String sql = "INSERT INTO Users (name, userName, userPassword, userType, semester) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, userType);
            statement.setInt(5, semester);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            int insertedId = 2;
            
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    insertedId = generatedKeys.getInt(1);
                    // Use the insertedId as needed
                    System.out.println("Inserted ID: " + insertedId);
                }
            }
            
            session.setAttribute("userId", insertedId);
            session.setAttribute("username", username);
            session.setAttribute("userType", userType);
            session.setAttribute("userNameFull", name);
            session.setAttribute("storedUsersSemester", semester);
            

            // Close the database connection
            statement.close();
            conn.close();
            
            // Redirect to the appropriate page based on user type
            if (userType.equals("student")) {
            	
                //response.sendRedirect("Student.jsp");
            	
            	session.setAttribute("studentId", insertedId);
            	response.sendRedirect("StudentCourses?studentId=" + insertedId); 
            }
            else {
            	
                //response.sendRedirect("Teacher.jsp");
            	
            	session.setAttribute("teacherId", insertedId);
            	request.setAttribute("usersNameFull", name);
            	
            	response.sendRedirect("teacherCourses?teacherId=" + insertedId + "&usersNameFull=" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println(e.getMessage());
        }
    }
    
    private boolean userExists(String username) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM Users WHERE userName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet result = statement.executeQuery();
            return result.next(); // Return true if a row is found (user exists)
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }



}
