<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Student List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
    <style>
        body {
            
            background-color: #f8f9fa;
        }
        
        h1 {
            text-align: center;
            margin-bottom: 2rem;
        }
        .table {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .table th {
            background-color: #f8f9fa;
            border-bottom: none;
            font-weight: 500;
        }
        .table td {
            border-bottom: 1px solid #dee2e6;
        }
        
        .container1 {
            max-width: 600px;
        }
        
        
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
                 
                </ul>
            </div>
        </div>
    </nav>







    <div class="container container1 my-5" style="padding-top: 80px;">
        <h1>Students</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Semester</th>
                </tr>
            </thead>
            <tbody>
                <%-- Iterate over the student list and display each student --%>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.name}</td>
                        <td>${student.semester}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <%!
    public String getUserServlet(String userType) {
        if (userType.equals("student")) {
            return "StudentServlet";
        } else if (userType.equals("admin")) {
            return "AdminServlet";
        } else if (userType.equals("teacher")) {
            return "TeacherServlet";
        } else {
            // Default servlet URL if userType doesn't match any condition
            return "#"; // or any other default URL
        }
    }
%>
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
