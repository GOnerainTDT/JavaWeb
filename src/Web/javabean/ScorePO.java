package Web.javabean;

public class ScorePO {
    String Cno;
    String Sno;
    String Grade;

    public void setGrade(String grade) {
        Grade = grade;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getGrade() {
        return Grade;
    }

    public String getCno() {
        return Cno;
    }

    public String getSno() {
        return Sno;
    }
}
