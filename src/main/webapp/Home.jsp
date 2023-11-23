<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>

<link rel="stylesheet" href="css/bootstrap.css">


<style>
	.card{
	opacity: 0.8;
	}
	
	

</style>
</head>
<body style = "background-image: url('images/homeImage.jpg'); background-repeat: no-repeat;
  background-size: cover;">

	<div align = center style="margin-top: 40px">
        <h2>Welcome <br>to<br>Online Course Management System</h2>
    </div>


	<div align = center style="margin-top: 150px">
	
	<div class = "row">
	
	<div class = "col">
	
	<div class="card text-bg-dark mb-3" style="max-width: 18rem;" >
        <div class="card-header">Student</div>
        <div class="card-body">
          <h5 class="card-title">.</h5>
          <p class="card-text">.</p>
          <a class="btn btn-light" onclick="goToPage('Login.jsp')">Login</a>
          <a class="btn btn-light" onclick="goToPage('Register.jsp')">Register</a>
        </div>
      </div>
	
	</div>
	
	
	<div class = "col">
	
	<div class="card text-bg-success mb-3" style="max-width: 18rem;">
        <div class="card-header">Teacher</div>
        <div class="card-body">
          <h5 class="card-title">.</h5>
          <p class="card-text">.</p>
          <a class="btn btn-light" onclick="goToPage('Login.jsp')">Login</a>
          <a class="btn btn-light" onclick="goToPage('Register.jsp')">Register</a>
        </div>
      </div>
	
	</div>
	
	
	<div class = "col">
	
	<div class="card text-bg-primary mb-3" style="max-width: 18rem;">
        <div class="card-header">Admin</div>
        <div class="card-body">
          <h5 class="card-title">.</h5>
          <p class="card-text">.</p>
          <a class="btn btn-light" onclick="goToPage('Login.jsp')">Login</a>
        </div>
      </div>
	
	</div>
	
      
     
      </div>
	
	
	</div>
	
	</div>
      
     
      
     
   <script>
  function goToPage(page) {
    window.location.href = page;
  }
</script>
     
      
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>