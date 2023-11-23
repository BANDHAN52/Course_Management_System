package Student;

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
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import mydefault.Course;

/**
 * Servlet implementation class RegisterCourseServlet
 */
public class RegisterCourseServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String courseIdName = request.getParameter("courseIdName");
	    String courseName = request.getParameter("courseName");
	    int courseId = Integer.parseInt(request.getParameter("courseId"));
	    
	    //String studentId = "1"; // Replace with the actual student ID
	    
	    HttpSession session = request.getSession();
	    String studentName = (String) session.getAttribute("userNameFull");
	    int studentId = (int) session.getAttribute("CurrentStudentId");
	    int studentsSemester = (int) session.getAttribute("storedUsersSemester");

	    
	    
	    
	    
	    String message = "register course servlet: ";
	    message+="\ncourseId: "+courseId;
	    message+="\ncourseIdName: "+courseIdName;
	    message+="\ncourseName: "+courseName;
	    message+="\nstudentName: "+studentName;
	    message+="\nstudentId: "+studentId;
	    
	    
	    // Insert a new row into the Enrollments table
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CourseManagementSystem", "ashik", "password");
	        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Enrollments (studentId, courseId, courseIdName, courseName, studentName, studentsSemester) VALUES (?, ?, ?, ?, ?, ?)");
	        pstmt.setInt(1, studentId);
	        pstmt.setInt(2, courseId);
	        pstmt.setString(3, courseIdName); // Replace with the actual course ID name
	        pstmt.setString(4, courseName); // Replace with the actual course name
	        pstmt.setString(5, studentName); // Replace with the actual student name
	        pstmt.setInt(6, studentsSemester);
	        pstmt.executeUpdate();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    // Redirect to the same page to update the list of registered and unregistered courses
	    response.sendRedirect(request.getContextPath() + "/StudentCourses");
	}


}
