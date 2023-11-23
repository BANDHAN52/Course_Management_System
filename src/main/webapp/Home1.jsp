<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Course Management System</title>
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
  
  <style>
	.card{
	opacity: 0.9;
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
<body style = "background-image: url('images/homeImage.jpg'); background-repeat: no-repeat;
  background-size: cover; background-position: center center; background-attachment: fixed;" >
  
  
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

	<!--  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Online Course Management System</a>
      </div>
    </nav> -->
    <div class="container my-5" style="padding-top: 80px;">
      <div class="row justify-content-center">
        <div class="col-lg-6">
          <div class="card border-0 shadow">
            <div class="card-header bg-white">
              <h3 class="text-center">Welcome to the Online Course Management System</h3>
            </div>
            <div class="card-body">
              <form action="login" method="post">
                <div class="mb-3">
                  <label for="username" class="form-label">Username</label>
                  <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                  <label for="userType" class="form-label">User Type</label>
                  <select class="form-control" id="userType" name="userType">
                    <option value="student">Student</option>
                    <option value="teacher">Teacher</option>
                    <option value="admin">Admin</option>
                  </select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Log In</button>
              </form>
              <hr>
              <p class="text-center">New to the system?</p>
              <div class="d-grid gap-2">
                <a href="Register.jsp" class="btn btn-outline-primary">Register as a Student</a>
    			<a href="Register.jsp" class="btn btn-outline-primary">Register as a Teacher</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    
    <% String errorMessage = (String) request.getAttribute("errorMessage");
if (errorMessage != null) {
%>
<div class="alert alert-danger">
    <%= errorMessage %>
</div>
<%
}
%>
    
    
    <!-- Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.min.js"></script>
  
  


</body>
</html>