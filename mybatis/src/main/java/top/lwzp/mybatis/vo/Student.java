package top.lwzp.mybatis.vo;

public class Student {
    private int stuNo;
    private String stuName;
    private int stuAge;
    private String gradeName;

    public Student(int stuNo, String stuName, int stuAge, String gradeName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.gradeName = gradeName;
    }

    public Student() {}

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"stuNo\":")
                .append(stuNo);
        sb.append(",\"stuName\":\"")
                .append(stuName).append('\"');
        sb.append(",\"stuAge\":")
                .append(stuAge);
        sb.append(",\"gradeName\":\"")
                .append(gradeName).append('\"');
        sb.append('}');
        return sb.toString().replace("\"null\"", "\"\"");
    }
}
