package domain;

public class CourseArrange {
    private String arrangeNo;
    private  Course course;
    private  Classtbl classtbl;
    private Teacher teacher;
    private String studyRoom;

    public CourseArrange() {
    }

    public CourseArrange(String arrangeNo, Course course, Classtbl classtbl, Teacher teacher, String studyRoom) {
        this.arrangeNo = arrangeNo;
        this.course = course;
        this.classtbl = classtbl;
        this.teacher = teacher;
        this.studyRoom = studyRoom;
    }

    public String getArrangeNo() {
        return arrangeNo;
    }

    public void setArrangeNo(String arrangeNo) {
        this.arrangeNo = arrangeNo;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classtbl getClasstbl() {
        return classtbl;
    }

    public void setClasstbl(Classtbl classtbl) {
        this.classtbl = classtbl;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getStudyRoom() {
        return studyRoom;
    }

    public void setStudyRoom(String studyRoom) {
        this.studyRoom = studyRoom;
    }

    @Override
    public String toString() {
        return "CourseArrange{" +
                "arrangeNo='" + arrangeNo + '\'' +
                ", course=" + course +
                ", classtbl=" + classtbl +
                ", teacher=" + teacher +
                ", studyRoom='" + studyRoom + '\'' +
                '}';
    }
}
