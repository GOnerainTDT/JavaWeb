package Web.javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class ScoreDAO {
    private Connection cn;

    public ScoreDAO(Connection con) {
        cn = con;
    }

    public void addScore(ScorePO score) {
        try {
            System.out.println(12334);
            ScorePO unique = queryGradeByKey(score.getCno(), score.getSno());
            System.out.println(12335);
            if (unique != null) {
                throw new UniqueException("该学生的课程成绩已经注册！");
            }
            System.out.println(1233);
            String sqlStr = "insert into score (Cno,Sno,grade) values(?,?,?)";
            PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
            prepStmt.setString(1, score.getCno());
            prepStmt.setString(2, score.getSno());
            prepStmt.setString(3, score.getGrade());
            prepStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("addGrade error:" + e);
        }
    }

    public void addScores(ArrayList<ScorePO> scores) {
        for (ScorePO score : scores) {
            addScore(score);
        }
    }

    public void deleteScore(ScorePO score) {
        try {
            if (cn != null) {
                String sqlStr = "delete from score where Cno=? AND Sno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
                // a
                // statement
                prepStmt.setString(1, score.getCno());
                prepStmt.setString(2, score.getSno());
                prepStmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("deleteScore error:" + e);
        }
    }

    public void deleteScores(ArrayList<ScorePO> scores) {
        for (ScorePO score : scores) {
            deleteScore(score);
        }

    }

    public void updateScore(ScorePO score) {
        try {
            if (cn != null) {
                String sqlStr = "update Score set Cno=?,Sno=?,Grade=? "
                        + " where Cno=? AND Sno = ?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
                // a
                // statement
                prepStmt.setString(1, score.getCno());
                prepStmt.setString(2, score.getSno());
                prepStmt.setString(3, score.getGrade());
                prepStmt.setString(4, score.getCno());
                prepStmt.setString(5, score.getSno());
                prepStmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("updateScore error:" + e);
        }
    }

    public void updateScore(ArrayList<ScorePO> scores) {
        for (ScorePO score : scores) {
            updateScore(score);
        }

    }

    public ScorePO queryGradeByKey(String cno, String sno) {
        ScorePO grade = null;
        try {
            if (cn != null) {
                String sqlStr = "SELECT * FROM Score  where Cno=? AND Sno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr);
                prepStmt.setString(1, cno);
                prepStmt.setString(2, sno);
                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    grade = new ScorePO();
                    grade.setCno(rs.getString("Cno"));
                    grade.setSno(rs.getString("Sno"));
                    grade.setGrade(rs.getString("Grade"));
                }
            }

        } catch (Exception e) {
            System.out.println("queryScore error:" + e);
        }

        return grade;

    }

    public ArrayList<ScorePO> queryScores() {
        ArrayList<ScorePO> scores = new ArrayList<ScorePO>();
        ScorePO score;
        try {
            if (cn != null) {
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Score order by Cno");
                while (rs.next()) {
                    score = new ScorePO();
                    score.setCno(rs.getString("Cno"));
                    score.setSno(rs.getString("Sno"));
                    score.setGrade(rs.getString("Grade"));
                    scores.add(score);
                }
            }

        } catch (Exception e) {
            System.out.println("queryScore error:" + e);
        }

        return scores;
    }
}
