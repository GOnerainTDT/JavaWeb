<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>更新学生记录</title>
  <script type="text/javascript">

  </script>
</head>
<body>

<%
  request.setCharacterEncoding("gbk");
  String no=request.getParameter("no");
  String name=request.getParameter("name");
  String Ccredit=request.getParameter("credit");
  String prerequisite=request.getParameter("prerequisite");
%>

<h3>删除课程记录</h3>
<form name="courseUpdate" id="courseUpdate" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
  <table id="courseInfo"   border="0" cellspacing="0" >
    <tr>
      <td> <label for="Cno">课程号: </label></td>
      <td> <input type="text" name="Cno" id="Cno" value="<%=no%>"></td>
    </tr>
    <tr>
      <td> <label for="Cname">课程名: </label></td>
      <td> <input type="text" name="Cname" id="Cname" value="<%=name%>"></td>
    </tr>
    <tr>
      <td> <label for="Ccredit">学分: </label></td>
      <td> <input type="text" name="Ccredit" id="Ccredit" value="<%=Ccredit%>"></td>
    </tr>
    <tr>
      <td> <label for="prerequisite">前向选修: </label></td>
      <td> <input type="text" name="prerequisite" id="prerequisite" value="<%=prerequisite%>"></td>
    </tr>

  </table>
  <input  name="entity" id="entity" value="Course" type="hidden">
  <input  name="operation" id="operation" value="delete" type="hidden">
  <p>
    <input type="submit" name="Submit" value="提交" >
    <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
  </p>
</form>


</body>
</html>