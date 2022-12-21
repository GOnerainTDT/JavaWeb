package Web.controller;

import Web.javabean.CourseDAO;
import Web.javabean.CoursePO;
import Web.javabean.StudentDAO;
import Web.javabean.StudentPO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

public class CourseAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, Connection cn) {
        String operation = request.getParameter("operation");
        StudentDAO studentDAO = new StudentDAO(cn);
        CourseDAO courseDAO = new CourseDAO(cn);
        if (operation.equals("insert") || operation.equals("update")
                || operation.equals("delete")) {
            CoursePO course = new CoursePO();
            String no = request.getParameter("Cno");
            System.out.println("no" + no);
            course.setNo(no);
            String name = request.getParameter("Cname");
            course.setName(name);
            String credit = request.getParameter("Ccredit");
            course.setCredit(credit);
            String prerequisite = request.getParameter("prerequisite");
            System.out.println("prerequisite" + prerequisite);
            course.setPrerequisite(prerequisite);

            if (operation.equals("insert")) {
                courseDAO.addCourse(course);
                System.out.println(course.getPrerequisite());
            } else if (operation.equals("delete")) {
                courseDAO.deleteCourse(course);
//                studentDAO.deleteStudent(student);
            } else if (operation.equals("update")) {
                courseDAO.updateCourse(course);
//                studentDAO.updateStudent(student);
            }

        }

        List<CoursePO> courses = courseDAO.queryCourses();
        for (CoursePO course : courses){
            System.out.println("Action " + course.getPrerequisite());
        }
//		List<StudentPO> students;
        request.setAttribute("courseList", courses);
        //HttpSession session=request.getSession(true);
        //session.setAttribute("courseList", courses);
        return "/CourseList.jsp";
    }
}
