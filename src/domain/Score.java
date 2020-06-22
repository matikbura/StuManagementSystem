package domain;

public class Score {
    private Integer id ;
    private Student student;
    private CourseArrange courseArrange;
    private Integer score;

    public Score() {
    }

    public Score(Integer id, Student student, CourseArrange courseArrange, Integer score) {
        this.id = id;
        this.student = student;
        this.courseArrange = courseArrange;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseArrange getCourseArrange() {
        return courseArrange;
    }

    public void setCourseArrange(CourseArrange courseArrange) {
        this.courseArrange = courseArrange;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", student=" + student +
                ", courseArrange=" + courseArrange +
                ", score=" + score +
                '}';
    }
}
