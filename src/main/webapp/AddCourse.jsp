<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Course</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
</head>
<body>


    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Add Course</h4>
                    </div>
                    <div class="card-body">
                        <form method="post" action="AddCourseServlet">
                            <div class="mb-3">
                                <label for="courseId" class="form-label">Course ID</label>
                                <input type="text" class="form-control" id="courseId" name="courseId" required>
                            </div>
                            <div class="mb-3">
                                <label for="courseName" class="form-label">Course Name</label>
                                <input type="text" class="form-control" id="courseName" name="courseName" required>
                            </div>
                            <div class="mb-3">
                                <label for="teacherId" class="form-label">Assign Teacher</label>
                                <select class="form-select" id="teacherId" name="teacherId" required>
                                    <c:forEach var="user" items="${users}">
                                        <c:if test="${user.userType eq 'teacher'}">
                                            <option value="${user.userId},${user.name}">${user.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Course</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/js/bootstrap.min.js"></script>
</body>
</html>
