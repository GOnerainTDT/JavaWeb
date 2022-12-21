package Web.javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class CourseDAO {
    private Connection cn;

    public CourseDAO(Connection con) {
        cn = con;
    }

    public void addCourse(CoursePO course) {
        try {
            CoursePO unique = queryCourseByKey(course.getNo());
            if (unique != null) {
                throw new UniqueException("该课程已经注册！");
            }
            String sqlStr = "insert into course (Cno,Cname,Ccredit,prerequisite) values(?,?,?,?)";
            PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
            prepStmt.setString(1, course.getNo());
            prepStmt.setString(2, course.getName());
            prepStmt.setString(3, course.getCredit());
            prepStmt.setString(4, course.getPrerequisite());
            prepStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("addCourse error:" + e);
        }
    }

    public void addCourses(ArrayList<CoursePO> courses) {
        for (CoursePO course : courses) {
            addCourse(course);
        }

    }

    public void deleteCourse(CoursePO course) {
        try {
            if (cn != null) {
                String sqlStr = "delete from course where Cno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
                // a
                // statement
                prepStmt.setString(1, course.getNo());
                prepStmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("deleteCourse error:" + e);
        }
    }

    public void deleteCourses(ArrayList<CoursePO> courses) {
        for (CoursePO course : courses) {
            deleteCourse(course);
        }

    }

    public void updateCourse(CoursePO course) {
        try {
            if (cn != null) {
                String sqlStr = "update Course set Cname=?,Ccredit=?,prerequisite=? "
                        + " where Cno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
                // a
                // statement
                prepStmt.setString(1, course.getName());
                prepStmt.setString(2, course.getCredit());
                prepStmt.setString(3, course.getPrerequisite());
                prepStmt.setString(4, course.getNo());
                prepStmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("updateCourse error:" + e);
        }
    }

    public void updateCourses(ArrayList<CoursePO> courses) {
        for (CoursePO course : courses) {
            updateCourse(course);
        }

    }

    public CoursePO queryCourseByKey(String no) {
        CoursePO course = null;
        try {
            if (cn != null) {
                String sqlStr = "SELECT * FROM Course  where Cno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr);
                prepStmt.setString(1, no);
                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    course = new CoursePO();
                    course.setNo(rs.getString("Cno"));
                    course.setName(rs.getString("Cname"));
                    course.setCredit(rs.getString("Ccredit"));
                    course.setPrerequisite(rs.getString("prerequisite"));
                }
            }

        } catch (Exception e) {
            System.out.println("queryCourse error:" + e);
        }

        return course;

    }

    public ArrayList<CoursePO> queryCourses() {
        ArrayList<CoursePO> courses = new ArrayList<CoursePO>();
        CoursePO course;
        try {
            if (cn != null) {
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Course order by Cno");
                while (rs.next()) {
                    course = new CoursePO();
                    course.setNo(rs.getString("Cno"));
                    course.setName(rs.getString("Cname"));
                    course.setCredit(rs.getString("Ccredit"));
                    course.setPrerequisite(rs.getString("prerequisite"));
                    courses.add(course);
                }
            }

        } catch (Exception e) {
            System.out.println("queryCourse error:" + e);
        }

        return courses;
    }
}
