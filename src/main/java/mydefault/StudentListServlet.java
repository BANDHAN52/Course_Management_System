package mydefault;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mydefault.Users;

/**
 * Servlet implementation class StudentListServlet
 */

@WebServlet("/Students")
public class StudentListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CourseManagementSystem";
    private static final String DB_USERNAME = "ashik";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Users> students = new ArrayList<>();
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT name, semester FROM Users WHERE userType = 'student'";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                int semester = result.getInt("semester");

                Users student = new Users(name);
                student.setSemester(semester);;
                students.add(student);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        System.out.println(students.get(0).getName());

        request.setAttribute("students", students);
        request.getRequestDispatcher("StudentList.jsp").forward(request, response);
    }
}




