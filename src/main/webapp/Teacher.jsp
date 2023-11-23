<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registered Courses</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
    
    <style>
        /* Navbar */
        .navbar {
            background: linear-gradient(to bottom, #66ccff 0%, #006699 100%);
        }
        
        /* Navbar Brand */
        .navbar-brand {
            color: #fff;
            font-size: 2rem;
            font-weight: bold;
            text-shadow: 2px 2px #666;
        }
        
        /* Navbar Links */
        .nav-link {
            color: #fff !important;
            font-size: 1.2rem;
            font-weight: bold;
            text-shadow: 1px 1px #666;
        }
        
        .nav-link:hover {
            background-color: #fff;
            color: #000 !important;
        }
        
        /* Logout Button */
        .logout-btn {
            background-color: #a0a0a0;
            color: #000;
            border: none;
            border-radius: 20px;
            font-size: 1.2rem;
            font-weight: bold;
            text-shadow: 1px 1px 2px #333;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        
        .logout-btn:hover {
            background-color: #fff;
            color: #fff;
        }
        
        /* Animation */
        @keyframes slideInFromTop {
            0% {
                transform: translateY(-100%);
            }
            100% {
                transform: translateY(0);
            }
        }
        
        .animated-top {
            animation: slideInFromTop 0.5s ease;
        }
    </style>
    
    
    
</head>
<body>

	<!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <!-- Navbar Brand -->
            <a class="navbar-brand" href="#">Online Course Managment System</a>

            <!-- Navbar Links -->
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="StudentListServlet">Students</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="TeacherListServlet">Teachers</a>
                    </li>
                    <li class="nav-item">
                        <button class="logout-btn nav-link" onclick="location.href='Home1.jsp'">Logout</button>
                    </li>
                </ul>
            </div>
        </div>
    </nav>








	


    <div class="container my-5" style="padding-top: 80px;">
    <h3 class="text-center mb-5">Teacher name:   <big><i>${teacherName}</i></big> </h3>
        <div class="row">
            <div class="col-md-6">
            <h1 class="text-center mb-5">Registered Courses</h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Course ID</th>
                            <th>Course Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${registeredCourses}">
                            <tr>
                                <td>${course.courseId}</td>
                                <td><a href="TeacherRegisteredStudentsServlet?courseId=${course.id}">${course.courseName}</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-6">
                
                <h3 class="text-center mb-5">Registered Students</h3>
                <h2 class="text-center mb-5" id="courseName">${courseIdName}:  ${courseName}</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Student Name</th>
                            <th>Semester</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${registeredStudents}">
                            <tr>
                                <td>${student.name}</td>
                                <td>${student.semester}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
	