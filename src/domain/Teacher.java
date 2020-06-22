package domain;

public class Teacher {
    private String teacherNo;
    private String teacherName;
    private String password;
    private int professional;
    private String education;
    private String address;
    private String phone;
    private String email;
    private String subject;
    public Teacher() {
    }

    public Teacher(String teacherNo, String teacherName, String password,
                   int professional, String education, String address, String phone,
                   String email, String subject) {
        this.teacherNo = teacherNo;
        this.teacherName = teacherName;
        this.password = password;
        this.professional = professional;
        this.education = education;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.subject = subject;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfessional() {
        return professional;
    }

    public void setProfessional(int profession) {
        this.professional = profession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherNo='" + teacherNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", password='" + password + '\'' +
                ", profession='" + professional + '\'' +
                ", education='" + education + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
