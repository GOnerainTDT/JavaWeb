<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="Web.javabean.StudentPO"%>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.ScorePO" %>
<%@ page import="Web.javabean.CoursePO" %>
<%
  request.setCharacterEncoding("gbk");
  List<StudentPO> students=(List<StudentPO>)request.getAttribute("studentList");
  List<ScorePO> scores=(List<ScorePO>)request.getAttribute("scoreList");
  List<CoursePO> courses=(List<CoursePO>)request.getAttribute("courseList");
  List<String> list1 = new ArrayList<>();
  for (CoursePO course : courses){
    list1.add(course.getNo());
  }
  List<String> list = new ArrayList<>();
  for (StudentPO student : students){
    list.add(student.getNo());
  }
  session.setAttribute("list",list);
  session.setAttribute("list1",list1);
  session.setAttribute("scoreList1", scores);
//  session.setAttribute("courseList1", courses);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <script language="javascript">
    function choose(number){
      var rowNo = eval(number);
      document.forwarder.no.value = document.all("scorelistTable").rows[rowNo].cells[0].innerText;
      document.forwarder.name.value = document.all("scorelistTable").rows[rowNo].cells[1].innerText;
      document.forwarder.sex.value = document.all("scorelistTable").rows[rowNo].cells[2].innerText;
      document.forwarder.age.value = document.all("scorelistTable").rows[rowNo].cells[3].innerText;
      document.forwarder.dept.value = document.all("scorelistTable").rows[rowNo].cells[4].innerText;

      if (document.all("scorelistTable").rows.length>1) {
        for (var i=1; i<document.all("scorelistTable").rows.length; i++){
          document.all("scorelistTable").rows[i].style.backgroundColor="";
        }
      }
      document.all("scorelistTable").rows[rowNo].style.backgroundColor="#ff998f";
    }
    function gotoAdd(){
      document.forwarder.action="ChooseCourses.jsp"
      document.forwarder.submit();
    }
    function gotoUpdate(){
      document.forwarder.action="StudentUpdate.jsp"
      document.forwarder.submit();
    }
    function gotoDelete(){
      document.forwarder.action="SetGrade.jsp"
      document.forwarder.submit();
    }
    function gotoPortal(){
      document.location.href ="/JavaAdvance/portal.html";
    }
  </script>
</head>

<body>

<Table name="scorelistTable" id="scorelistTable" border=1>
  <Tr>
    <th>成绩管理系统</th>
  </Tr>
</Table>
<p>
  <input type="button" name="add" value="学生选课" onclick="gotoAdd()">
  <input type="button" name="edit" value="修改" onclick="gotoUpdate()">
  <input type="button" name="delete" value="给定成绩" onclick="gotoDelete()">
  <input type="button" name="exit" value="退回门户" onclick="gotoPortal()">
</p>
<form  method="post" name="forwarder" id="forwarder">
  <input type=hidden name="no" id="no" />
  <input type=hidden name="name" id="name" />
  <input type=hidden name="sex" id="sex" />
  <input type=hidden name="age" id="age" />
  <input type=hidden name="dept" id="dept" />
</form>
</body>
</html>
