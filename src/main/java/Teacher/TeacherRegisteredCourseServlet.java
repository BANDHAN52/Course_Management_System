package Teacher;

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
 * Servlet implementation class TeacherRegisteredCourseServlet
 */
@WebServlet("/teacherCourses")
public class TeacherRegisteredCourseServlet extends HttpServlet {
	String messages = "got you";
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CourseManagementSystem";
    private static final String DB_USERNAME = "ashik";
    private static final String DB_PASSWORD = "password";
    
    int firstCourseId;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String presentCourseName,firstCourseName,presentCourseIdName,firstCourseIdName;
    	
    	
    	HttpSession session1 = request.getSession();
    	String teacherName = (String) session1.getAttribute("userNameFull");
    	
    	
    	int teacherId = (Integer)session1.getAttribute("teacherId");
        List<Course> registeredCourses = getRegisteredCourses(teacherId);
        
//        firstCourseId = registeredCourses.get(0).getId();
//        firstCourseName = registeredCourses.get(0).getCourseName();
//        firstCourseIdName = registeredCourses.get(0).getCourseId();
        
        if(!registeredCourses.isEmpty())
        {
        	firstCourseId = registeredCourses.get(0).getId();
            firstCourseName = registeredCourses.get(0).getCourseName();
            firstCourseIdName = registeredCourses.get(0).getCourseId();
        }
        else
        {
        	firstCourseId = 0;
            firstCourseName = null;
            firstCourseIdName = null;
        }
        
        request.setAttribute("registeredCourses", registeredCourses);
        
        List<Users> registeredStudents = null;
		try {
			registeredStudents = getRegisteredStudents(firstCourseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
    	List<Users> registeredStudents1 = (List<Users>) session.getAttribute("ThisCourseRegisteredStudents");

    	presentCourseName = (String) session.getAttribute("presentCourseName");
    	presentCourseIdName = (String) session.getAttribute("presentCourseIdName");
        
        if(registeredStudents1 !=null)
        {
        	
        	request.setAttribute("registeredStudents", registeredStudents1);
        	session.setAttribute("courseName", presentCourseName);
        	session.setAttribute("courseIdName", presentCourseIdName);
        }
        else
        {
        	
        	request.setAttribute("registeredStudents", registeredStudents);
        	session.setAttribute("courseName", firstCourseName);
        	session.setAttribute("courseIdName", firstCourseIdName);
        }
		
		request.setAttribute("teacherName", teacherName);
        request.getRequestDispatcher("Teacher.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
       
    	HttpSession session = request.getSession();
    	List<Users> registeredStudents = (List<Users>) session.getAttribute("ThisCourseRegisteredStudents");
    	
		try {
			registeredStudents = getRegisteredStudents(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        request.setAttribute("registeredStudents", registeredStudents);
        request.getRequestDispatcher("Teacher.jsp").forward(request, response);
    }

    private List<Course> getRegisteredCourses(int teacherId) {
        List<Course> registeredCourses = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM Courses WHERE teacherId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, teacherId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Course course = new Course();
                course.setId(result.getInt("Id"));
                course.setCourseId(result.getString("courseId"));
                course.setCourseName(result.getString("courseName"));

                registeredCourses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registeredCourses;
    }

    public List<Users> getRegisteredStudents(int courseId) throws SQLException {
        List<Users> students = new ArrayList<>();
    
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM Enrollments WHERE courseId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseId);
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String studentName = rs.getString("studentName");
                int semester = rs.getInt("studentsSemester");
                Users student = new Users(studentName);
                student.setSemester(semester);
                students.add(student);
            }
        }
    
        return students;
    }
}
