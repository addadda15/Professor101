package main.main.professor101;

public class UserHelperClass {  // 학생들 저장하기위한 클래스

    String name, studentID, password, passwordCheck;

    public UserHelperClass(){

    }

    public UserHelperClass(String name, String studentID, String password, String passwordCheck) {
        this.name = name;
        this.studentID = studentID;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
