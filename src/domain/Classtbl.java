package domain;

public class Classtbl {
    private String classNo;
    private String className;
    private String college;

    public Classtbl() {
        super();
    }

    public Classtbl(String classNo, String className, String college) {
        this.classNo = classNo;
        this.className = className;
        this.college = college;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Classtbl{" +
                "classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
}
