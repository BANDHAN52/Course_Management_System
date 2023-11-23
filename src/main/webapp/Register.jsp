<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<title>Registration Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css">
    
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
            <a class="navbar-brand" href="Home1.jsp">Online Course Managment System</a>

            <!-- Navbar Links -->
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Students</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Teachers</a>
                    </li>
                    
                </ul>
            </div>
        </div>
    </nav>


		<%-- Display error message if available --%>
    <c:if test="${not empty errorMessage}">
        <p style="color: red">${errorMessage}</p>
    </c:if>

		

	<div class="container my-5" style="padding-top: 80px;">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">Registration Form</h4>
                </div>
                <div class="card-body">
                    <form action="register" method="post">
                        <div class="form-group mb-3">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required placeholder="Mofiz">
                        </div>
                        <div class="form-group mb-3">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required placeholder="mofiz123">
                        </div>
                        <div class="form-group mb-3">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required placeholder="mofiz123">
                        </div>
                        <div class="form-group mb-3">
                            <label for="userType">User Type</label>
                            <select class="form-select" id="userType" name="userType">
                                <option value="student">Student</option>
                                <option value="teacher">Teacher</option>
                            </select>
                        </div>
                        <div class="form-group mb-3">
                            <label for="semester">Semester</label>
                            <input type="number" class="form-control" id="semester" name="semester" required min="1" max="12">
                        </div>
                        <div class="form-group mb-0">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>