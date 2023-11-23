package LoginRegister;

import jakarta.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	int studentsId;
	private static final long serialVersionUID = 1L;

    private String dbURL = "jdbc:mysql://localhost:3306/CourseManagementSystem";
    private String dbUser = "ashik";
    private String dbPassword = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Open a connection to the database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            // Prepare the SQL statement to retrieve the user
            String sql = "SELECT * FROM Users WHERE userName = ? AND userPassword = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            // Check if the user exists
            if (rs.next()) {
                // Retrieve the user's details from the database
                int userId = rs.getInt("userId");
                String storedUsername = rs.getString("userName");
                String storedUserType = rs.getString("userType");
                String storedUserNameFull = rs.getString("name");
                int storedUsersSemester = Integer.parseInt(rs.getString("semester"));

                // Check if the user type matches
                if (!userType.equals(storedUserType)) {
                    // If the user type doesn't match, show an error message
                    request.setAttribute("errorMessage", "Invalid user type for the selected user");
                    request.getRequestDispatcher("Home1.jsp").forward(request, response);
                    return;
                }

                // Store the user's details in a session
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("username", storedUsername);
                session.setAttribute("userType", storedUserType);
                session.setAttribute("userNameFull", storedUserNameFull);
                session.setAttribute("storedUsersSemester", storedUsersSemester);
                	
                // Redirect the user to the appropriate page based on their user type
                if (userType.equals("student")) {
                	
                	//request.setAttribute("studentId", userId);
                	session.setAttribute("studentId", userId);
                	response.sendRedirect("StudentCourses?studentId=" + userId); 
                } else if (userType.equals("teacher")) {
                	
                	
                    
                	
                	//request.setAttribute("teacherId", userId);
                	session.setAttribute("teacherId", userId);
                	request.setAttribute("usersNameFull", storedUserNameFull);
                	
                	response.sendRedirect("teacherCourses?teacherId=" + userId + "&usersNameFull=" + storedUserNameFull);

                	
                	//response.sendRedirect("teacherCourses?teacherId=" + userId);
                    //response.sendRedirect("Teacher.jsp");
                } else if (userType.equals("admin")) {
                    response.sendRedirect(request.getContextPath() + "/courses");
                }
            } else {
                // If the user doesn't exist, show an error message
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("Home1.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

