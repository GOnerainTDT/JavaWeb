<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="Web.javabean.StudentPO"%>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.CoursePO" %>
<%
  request.setCharacterEncoding("gbk");
  List<CoursePO> courses=(List<CoursePO>)request.getAttribute("courseList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <script language="javascript">
    function choose(number){
      var rowNo = eval(number);
      document.forwarder.no.value = document.all("listTable").rows[rowNo].cells[0].innerText;
      document.forwarder.name.value = document.all("listTable").rows[rowNo].cells[1].innerText;
      document.forwarder.credit.value = document.all("listTable").rows[rowNo].cells[2].innerText;
      document.forwarder.prerequisite.value = document.all("listTable").rows[rowNo].cells[3].innerText;
      // document.forwarder.dept.value = document.all("listTable").rows[rowNo].cells[4].innerText;

      if (document.all("listTable").rows.length>1) {
        for (var i=1; i<document.all("listTable").rows.length; i++){
          document.all("listTable").rows[i].style.backgroundColor="";
        }
      }
      document.all("listTable").rows[rowNo].style.backgroundColor="#ff998f";
    }
    function gotoAdd(){
      document.forwarder.action="Add.jsp"
      document.forwarder.submit();
    }
    function gotoUpdate(){
      document.forwarder.action="StudentUpdate.jsp"
      document.forwarder.submit();
    }
    function gotoDelete(){
      document.forwarder.action="StudentDelete.jsp"
      document.forwarder.submit();
    }
    function gotoPortal(){
      document.location.href ="/JavaAdvance/portal.html";
    }
  </script>
</head>

<body>

<Table name="clistTable" id="clistTable" border=1>
  <Tr>
    <th>课程号</th><th>课程名</th><th>学分</th><th>前向选修</th>
  </Tr>
  <%
    CoursePO course;
    List<String> list = new ArrayList<>();
    for (int index=0;index<courses.size();index++){
      course=courses.get(index);
      list.add(course.getNo() + " " + course.getName());
      System.out.println("List + Prerequisite " + course.getPrerequisite());
  %>
  <tr onclick="choose('<%=index+1%>')">
    <td><%=course.getNo()%></td>
    <td><%=course.getName()%></td>
    <td><%=course.getCredit()%></td>
    <td><%=course.getPrerequisite()%></td>
  </tr>
  <%}
//    HttpSession session = request.getSession();
    session.setAttribute("list", list);
    %>
</Table>
<p>
  <input type="button" name="add" value="新增" onclick="gotoAdd()">
  <input type="button" name="edit" value="修改" onclick="gotoUpdate()">
  <input type="button" name="delete" value="删除" onclick="gotoDelete()">
  <input type="button" name="exit" value="退回门户" onclick="gotoPortal()">
</p>
<form  method="post" name="forwarder" id="forwarder">
  <input type=hidden name="no" id="no" />
  <input type=hidden name="name" id="name" />
  <input type=hidden name="credit" id="credit" />
  <input type=hidden name="prerequisite" id="prerequisite" />
</form>
</body>
</html>
