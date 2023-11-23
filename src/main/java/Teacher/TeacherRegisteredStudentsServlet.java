package Teacher;

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
import java.util.List;
import java.util.ArrayList;

import mydefault.Course;
import mydefault.Users;

/**
 * Servlet implementation class TeacherRegisteredStudentsServlet
 */
@WebServlet("/RegisteredStudentsServlet")
public class TeacherRegisteredStudentsServlet extends HttpServlet {
	
	String presentCourseName,presentCourseIdName;
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
	  
    // Get the course ID from the request parameter
    int courseId = Integer.parseInt(request.getParameter("courseId"));
   
    

    // Get the list of registered students for the course from your database
    List<Users> registeredStudents = getRegisteredStudents(courseId);
    

    // Set the list of registered students as a request attribute
    HttpSession session = request.getSession();
    
    session.setAttribute("ThisCourseRegisteredStudents", registeredStudents);
    session.setAttribute("presentCourseName", presentCourseName);
    session.setAttribute("presentCourseIdName", presentCourseIdName);
    // Forward the request to your JSP page
    RequestDispatcher dispatcher = request.getRequestDispatcher("/TeacherRegisteredCourseServlet");
    dispatcher.forward(request, response);
  }

  private List<Users> getRegisteredStudents(int courseId) {
	  List<Users> registeredStudents = new ArrayList<>();
	    String url = "jdbc:mysql://localhost:3306/CourseManagementSystem";
	    String user = "ashik";
	    String password = "password";
	    String query = "SELECT * FROM Enrollments WHERE courseId = ?";
	    try (Connection conn = DriverManager.getConnection(url, user, password);
	            PreparedStatement stmt = conn.prepareStatement(query);) {
	        stmt.setInt(1, courseId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	presentCourseName = rs.getString("courseName");
	        	presentCourseIdName = rs.getString("courseIdName");
	            String name = rs.getString("studentName");
	            int semester = rs.getInt("studentsSemester");
	            Users student = new Users(name);
	            student.setSemester(semester);
	            registeredStudents.add(student);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return registeredStudents;
  }
}

