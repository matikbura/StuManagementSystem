package domain;

public class Student {
    private String studentNo;
    private String name;
    private String password;
    private String address;
    private String phone;
    private String email;
    private String classNo;

    public Student() {
    }

    public Student(String studentNo, String name, String password, String address, String phone, String email, String classNo) {
        this.studentNo = studentNo;
        this.name = name;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.classNo = classNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", classNo='" + classNo + '\'' +
                '}';
    }
}
