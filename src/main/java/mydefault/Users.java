package mydefault;

public class Users {
	private int userId;
    private String name;
    private String userName;
    private String userPassword;
    private String userType;
    private int semester;

    public Users(int userId, String name, String userType) {
        this.userId = userId;
        this.name = name;
        this.userType = userType;
    }
    
    

	public Users(String name) {
		
		this.name = name;
	}



	public Users(int userId, String name, int semester) {
		super();
		this.userId = userId;
		this.name = name;
		this.semester = semester;
	}



	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

}
