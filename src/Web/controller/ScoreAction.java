package Web.controller;

import Web.javabean.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

public class ScoreAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, Connection cn) {
        String operation = request.getParameter("operation");
        StudentDAO studentDAO = new StudentDAO(cn);
        CourseDAO courseDAO = new CourseDAO(cn);
        ScoreDAO scoreDAO = new ScoreDAO(cn);
        List<ScorePO> scores = scoreDAO.queryScores();
        if (operation.equals("insert") || operation.equals("update")
                || operation.equals("delete")) {
            ScorePO score = new ScorePO();
            String Cno = request.getParameter("Cno");
            System.out.println("no" + Cno);
            score.setCno(Cno);
            String Sno = request.getParameter("Sno");
            score.setSno(Sno);
            String grade = request.getParameter("Grade");
            score.setGrade(grade);
            System.out.println("scoregrade + " + grade);
            if (operation.equals("insert")) {
                scoreDAO.addScore(score);
                System.out.println("score + " + score.getCno());
            } else if (operation.equals("delete")) {
//                courseDAO.deleteCourse(course);
//                studentDAO.deleteStudent(student);
            } else if (operation.equals("update")) {
                scoreDAO.updateScore(score);
//                courseDAO.updateCourse(course);
//                studentDAO.updateStudent(student);
            }

        } else if (operation.equals("up")) {
            System.out.println("UP");
            for (int index=0;index<scores.size();++index){
                ScorePO score = scores.get(index);
                String gg = "grades[" + index+"]";
                String gradess = request.getParameter(gg);
                score.setGrade(gradess);
                scoreDAO.updateScore(score);
            }
            
        }

        List<CoursePO> courses = courseDAO.queryCourses();
        List<StudentPO> students = studentDAO.queryStudents();
        request.setAttribute("courseList", courses);
        request.setAttribute("scoreList", scores);
        request.setAttribute("studentList", students);
        //HttpSession session=request.getSession(true);
        //session.setAttribute("courseList", courses);
        return "/ScoreList.jsp";
    }
}
