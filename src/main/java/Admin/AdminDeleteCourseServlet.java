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
import java.sql.SQLException;

/**
 * Servlet implementation class AdminDeleteCourseServlet
 */
@WebServlet("/admin-delete-course")
public class AdminDeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/CourseManagementSystem";
    private static final String DB_USERNAME = "ashik";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the courseId parameter from the request
        int courseId = Integer.parseInt(request.getParameter("id"));
        System.out.println("courseId: "+courseId);

        try {
            // Create a database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL statementdelete-course
            String deleteEnrollmentsQuery = "DELETE FROM Enrollments WHERE courseId = ?";
            PreparedStatement deleteEnrollmentsStatement = conn.prepareStatement(deleteEnrollmentsQuery);
            deleteEnrollmentsStatement.setInt(1, courseId);
            deleteEnrollmentsStatement.executeUpdate();
            deleteEnrollmentsStatement.close();

            // Delete from Courses table
            String deleteCourseQuery = "DELETE FROM Courses WHERE Id = ?";
            PreparedStatement deleteCourseStatement = conn.prepareStatement(deleteCourseQuery);
            deleteCourseStatement.setInt(1, courseId);
            deleteCourseStatement.executeUpdate();
            deleteCourseStatement.close();
            
            conn.close();

            // Redirect back to the course list page
            //response.sendRedirect("Admin.jsp");
            response.sendRedirect(request.getContextPath() + "/courses");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

