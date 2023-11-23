package Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import mydefault.Course;

/**
 * Servlet implementation class AdminLoadCoursesServlet
 */
@WebServlet("/courses")
public class AdminLoadCoursesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CourseManagementSystem";
    private static final String DB_USERNAME = "ashik";
    private static final String DB_PASSWORD = "password";

    // SQL queries
    private static final String SELECT_ALL_COURSES = "SELECT * FROM Courses";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	System.out.println("AdminLoadCoursesServlet: doGet() method executed");

        // Create a list to hold the courses
        List<Course> courses = new ArrayList<>();
        
        // Try-with-resources to automatically close the resources after use
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_COURSES);
             ResultSet rs = stmt.executeQuery()) {
        	
        	
        	// Iterate through the result set and add each course to the list
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("courseId"));
                course.setId(rs.getInt("Id"));
                course.setCourseName(rs.getString("courseName"));
                course.setTeacherId(rs.getInt("teacherId"));
                course.setTeacherName(rs.getString("teacherName"));
                courses.add(course);
                
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Set the courses as a request attribute and forward to the JSP page
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
    }

}
