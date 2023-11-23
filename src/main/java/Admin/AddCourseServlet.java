package Admin;

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
import java.util.List;
import java.util.ArrayList;

import mydefault.Users;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourse")
public class AddCourseServlet extends HttpServlet {
	String name3 = null;
    private static final long serialVersionUID = 1L;

    // MySQL database connection details
    static final String DB_URL = "jdbc:mysql://localhost/CourseManagementSystem";
    static final String DB_USER = "ashik";
    static final String DB_PASS = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Get the form data
        String courseId = request.getParameter("courseId");
        String courseName = request.getParameter("courseName");
        //String teacherId = request.getParameter("teacherId");
        
        String selectedOption = request.getParameter("teacherId");
        String[] values = selectedOption.split(",");
        String teacherId = values[0];
        String teacherName = values[1];


        // Save the course data to the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Courses (courseId, courseName, teacherId, teacherName) VALUES (?, ?, ?, ?)");
            stmt.setString(1, courseId);
            stmt.setString(2, courseName);
            stmt.setString(3, teacherId);
            stmt.setString(4, teacherName);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Redirect to the home page
        response.sendRedirect(request.getContextPath() + "/courses");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Users> teachers = new ArrayList<>();

        // Get the list of teachers from the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE userType = 'teacher'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users teacher = new Users(rs.getInt("userId"), rs.getString("name"), rs.getString("userType"));
                name3 = rs.getString("name");
                teachers.add(teacher);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Set the list of teachers as a request attribute and forward to the JSP page
        request.setAttribute("users", teachers);
        request.setAttribute("message", "teacher name is: "+name3);
        request.getRequestDispatcher("/AddCourse.jsp").forward(request, response);
    }
}