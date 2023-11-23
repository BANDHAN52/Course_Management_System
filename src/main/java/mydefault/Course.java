package mydefault;

public class Course {
    private int id;
    private String courseId;
    private String courseName;
    private String teacherName;
    public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	private int teacherId;
    private int enrollmentId;
    private int studentId;
    
    public Course(int id, String courseId, String courseName, int teacherId) {
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
    }
    public Course() {
		// TODO Auto-generated constructor stub
	}
    
    
    public Course(int id, String courseId, String courseName) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.courseName = courseName;
	}
	public Course(int enrollmentId, int courseId, String courseIdName, String courseName, int studentId) {
        this.enrollmentId = enrollmentId;
        this.id = courseId;
        this.courseId = courseIdName;
        this.courseName = courseName;
        this.studentId = studentId;
    }
	public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public int getTeacherId() {
        return teacherId;
    }
    
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
	public int getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
    
}
