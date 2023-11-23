# This project is for Web Technology course. We used Jsp, servlet and Mysql.

# Online Course Management System Documentation

## Introduction

The Online Course Management System is a web-based application that simulates the functionalities of an online course management system. It allows three types of users to interact with the system: students, teachers, and administrators. The system is developed using JSP, Servlet, MySQL, and Bootstrap 5. The development environment includes Eclipse, MySQL Workbench, and Tomcat, and it was built on an Ubuntu computer.

## System Functionalities

### R-1: User Types

The system supports three types of users:

- Student: A student user can register for courses and view their registered courses.
- Teacher: A teacher user can view their registered courses and the list of students enrolled in each course.
- Admin: An admin user can add new courses, assign teachers to courses, and delete courses.

### R-2: User Authentication

All users must authenticate using a username and password. Students and teachers have the option to register if they are new users.

### R-3: Admin Functionality

The admin user has the following functionalities:

- Add Course: The admin can add a new course to the system and assign a teacher to the course.
- Delete Course: The admin can delete a course from the system.

### R-4: Student Functionality

The student user has the following functionalities:

- Register for Course: Students can register for new courses available in the system.
- View Registered Courses: Students can view a list of their registered courses.

### R-5: Teacher Functionality

The teacher user has the following functionalities:

- View Registered Courses: Teachers can view a list of courses they are assigned to.
- View Students: Teachers can select a course and view the list of students enrolled in that particular course.

## Database Structure

The system uses a MySQL database named "CourseManagementSystem" with the following tables:

- Users: This table stores user details, including username and password. It holds information for admin,students and teachers. 
- Courses: This table stores course details, including course name and assigned teacher.
- Enrollments: This table maintains the details of course enrollments, including the mapping between students and courses.

## System Pages and Navigation

The system consists of the following pages:

- Homepage: The homepage displays a login form where users can log in as a student, teacher, or admin. New students and teachers can also register using the provided button.
- Student Page: After logging in as a student, the student page appears. It displays the registered courses and unregistered courses.
- Teacher Page: After logging in as a teacher, the teacher page appears. It displays the registered courses and related students for each course.
- Admin Page: After logging in as an admin, the admin page appears. It displays all courses in the system and provides options to add or delete courses.
- Navigation Bar: The navigation bar includes buttons for logging out, viewing the list of students, and viewing the list of teachers.

## Development Environment

The Online Course Management System was developed using the following tools and technologies:

- IDE: Eclipse
- Database: MySQL Workbench
- Web Server: Apache Tomcat
- Operating System: Ubuntu

## Conclusion

The Online Course Management System provides a user-friendly interface for students, teachers, and administrators to manage courses effectively. It offers various functionalities such as user authentication, course registration, and course management. With its intuitive design and robust features, it simplifies the process of managing online courses.



=======
# course-management-system
This project is a jsp servlet based website. It is  a course project for Web Technology course in sust cse bachelor 4th year.
>>>>>>> origin/main
>>>>>>> origin/main
