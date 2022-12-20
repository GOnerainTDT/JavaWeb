package Web.controller;

import Web.javabean.StudentDAO;
import Web.javabean.StudentPO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

public class StudentAction implements IAction {

	public String execute(HttpServletRequest request, Connection cn) {
		String operation = request.getParameter("operation");
		StudentDAO studentDAO = new StudentDAO(cn);
		if (operation.equals("insert") || operation.equals("update")
				|| operation.equals("delete")) {
			StudentPO student = new StudentPO();
			String no = request.getParameter("no");
			System.out.println("no" + no);
			student.setNo(no);
			String name = request.getParameter("name");
			student.setName(name);
			String sex = request.getParameter("sex");
			student.setSex(sex);
			String age = request.getParameter("age");
			student.setAge(Integer.parseInt(age));
			String dept = request.getParameter("dept");
			student.setDept(dept);

			if (operation.equals("insert")) {
				studentDAO.addStudent(student);
			} else if (operation.equals("delete")) {
				studentDAO.deleteStudent(student);
			} else if (operation.equals("update")) {
				studentDAO.updateStudent(student);
			}

		}

		List<StudentPO> students = studentDAO.queryStudents();
//		List<StudentPO> students;
		request.setAttribute("studentList", students);
		//HttpSession session=request.getSession(true);
		//session.setAttribute("courseList", courses);
		return "/StudentList.jsp";
	}

}
