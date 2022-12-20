package Web.javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {
	private Connection cn;

	public StudentDAO(Connection con) {
		cn = con;
	}

	public void addStudent(StudentPO student) {
		try {
			StudentPO unique = queryStudentByKey(student.getNo());
			if (unique != null) {
				throw new UniqueException("该学生已经注册！");
			}
			String sqlStr = "insert into student(Sno,Sname,Ssex,Sage,Sdept) values(?,?,?,?,?)";
			PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
			prepStmt.setString(1, student.getNo());
			prepStmt.setString(2, student.getName());
			prepStmt.setString(3, student.getSex());
			prepStmt.setInt(4, student.getAge());
			prepStmt.setString(5, student.getDept());
			prepStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("addStudent error:" + e);
		}
	}

	public void addStudents(ArrayList<StudentPO> students) {
		for (StudentPO student : students) {
			addStudent(student);
		}

	}

	public void deleteStudent(StudentPO student) {
		try {
			if (cn != null) {
				String sqlStr = "delete from student where Sno=?";
				PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
																			// a
																			// statement
				prepStmt.setString(1, student.getNo());
				prepStmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println("deleteStudent error:" + e);
		}
	}

	public void deleteStudents(ArrayList<StudentPO> students) {
		for (StudentPO student : students) {
			deleteStudent(student);
		}

	}

	public void updateStudent(StudentPO student) {
		try {
			if (cn != null) {
				String sqlStr = "update Student set Sname=?,Ssex=?,Sage=?,Sdept=? "
						+ " where Sno=?";
				PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create
																			// a
																			// statement
				prepStmt.setString(1, student.getName());
				prepStmt.setString(2, student.getSex());
				prepStmt.setInt(3, student.getAge());
				prepStmt.setString(4, student.getDept());
				prepStmt.setString(5, student.getNo());
				prepStmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println("updateStudent error:" + e);
		}
	}

	public void updateStudents(ArrayList<StudentPO> students) {
		for (StudentPO student : students) {
			updateStudent(student);
		}

	}

	public StudentPO queryStudentByKey(String no) {
		StudentPO student = null;
		try {
			if (cn != null) {
				String sqlStr = "SELECT * FROM student  where Sno=?";
				PreparedStatement prepStmt = cn.prepareStatement(sqlStr);
				prepStmt.setString(1, no);
				ResultSet rs = prepStmt.executeQuery();
				if (rs.next()) {
					student = new StudentPO();
					student.setNo(rs.getString("Sno"));
					student.setName(rs.getString("Sname"));
					student.setAge(rs.getInt("Sage"));
					student.setSex(rs.getString("Ssex"));
					student.setDept(rs.getString("Sdept"));
				}
			}

		} catch (Exception e) {
			System.out.println("queryStudent error:" + e);
		}

		return student;

	}

	public ArrayList<StudentPO> queryStudents() {
		ArrayList<StudentPO> students = new ArrayList<StudentPO>();
		StudentPO student;
		try {
			if (cn != null) {
				Statement stmt = cn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT * FROM student order by Sno");
				while (rs.next()) {
					student = new StudentPO();
					student.setNo(rs.getString("Sno"));
					student.setName(rs.getString("Sname"));
					student.setAge(rs.getInt("Sage"));
					student.setSex(rs.getString("Ssex"));
					student.setDept(rs.getString("Sdept"));
					students.add(student);
				}
			}

		} catch (Exception e) {
			System.out.println("queryStudent error:" + e);
		}

		return students;
	}

}
