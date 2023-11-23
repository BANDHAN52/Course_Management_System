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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import mydefault.Course;

/**
 * Servlet implementation class StudentCourseServlet
 */
@WebServlet("/StudentCourses")
public class StudentCourseServlet extends HttpServlet {
	String studentName;
	int studentsSemester;

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
    	//String studentIdParam = request.getParameter("studentId");
    
    	HttpSession session3 = request.getSession();
    	int studentId = 0;
    	studentId = (Integer) session3.getAttribute("studentId");
    	if (studentId != 0) {
    	    //studentId = Integer.parseInt(studentIdParam);
    	    System.out.println("student id "+studentId);
    	} else {
    	    // handle the case where the parameter is null
    		System.out.println("student id null");
    	}

        // retrieve registered courses for the student
        List<Course> registeredCourses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CourseManagementSystem", "ashik", "password")) {
            String sql = "SELECT * FROM Enrollments WHERE studentId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                    	studentName = rs.getString("studentName");
                    	studentsSemester = rs.getInt("studentsSemester");
                    	System.out.println("student servlet: semester: "+studentsSemester);
                        Course course = new Course(rs.getInt("enrollmentId"),rs.getInt("courseId"), rs.getString("courseIdName"), rs.getString("courseName"), studentId);
                        registeredCourses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // retrieve unregistered courses for the student
        List<Course> unregisteredCourses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/CourseManagementSystem", "ashik", "password")) {
            String sql = "SELECT Id, courseId, courseName FROM Courses WHERE Id NOT IN (SELECT courseId FROM Enrollments WHERE studentId = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Course course = new Course(rs.getInt("Id"), rs.getString("courseId"), rs.getString("courseName"), studentId);
                        unregisteredCourses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        HttpSession session = request.getSession();
        studentName = (String)session.getAttribute("userNameFull");
        
        
        String currentStudentName = studentName;
        
        session.setAttribute("currentStudentName", currentStudentName);
        session.setAttribute("currentStudentSemester", studentsSemester);
        session.setAttribute("StudentRegisteredCourses", registeredCourses);
        session.setAttribute("StudentUnregisteredCourses", unregisteredCourses);
        session.setAttribute("CurrentStudentId", studentId);


        // set attributes and forward to the JSP page
        request.setAttribute("studentName", studentName);
        request.setAttribute("registeredCourses", registeredCourses);
        request.setAttribute("unregisteredCourses", unregisteredCourses);
        request.getRequestDispatcher("Student.jsp").forward(request, response);
    }
}
