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

@WebServlet("/Teachers")
public class TeacherListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CourseManagementSystem";
    private static final String DB_USERNAME = "ashik";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Users> teachers = new ArrayList<>();
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT name, semester FROM Users WHERE userType = 'teacher'";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                int semester = result.getInt("semester");

                Users teacher = new Users(name);
                teacher.setSemester(semester);;
                teachers.add(teacher);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        

        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("TeacherList.jsp").forward(request, response);
    }
}




