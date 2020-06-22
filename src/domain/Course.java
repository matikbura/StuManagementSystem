package domain;


public class Course {
    private String courseNo;
    private String courseName;
    private String studyTime;
    private String grade;
    private int courseType;
    private Integer term;

    public Course() {
        super();
    }

    public Course(String courseNo, String courseName,
                  String studyTime, String grade, int courseType, int term) {
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.studyTime = studyTime;
        this.grade = grade;
        this.courseType = courseType;
        this.term = term;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", studyTime='" + studyTime + '\'' +
                ", grade='" + grade + '\'' +
                ", courseType=" + courseType +
                ", term=" + term +
                '}';
    }

    public Course(String courseNo, String courseName, String studyTime, String grade, int courseType, Integer term) {
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.studyTime = studyTime;
        this.grade = grade;
        this.courseType = courseType;
        this.term = term;
    }
}
