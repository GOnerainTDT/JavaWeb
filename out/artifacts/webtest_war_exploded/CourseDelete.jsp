<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>����ѧ����¼</title>
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

<h3>ɾ���γ̼�¼</h3>
<form name="courseUpdate" id="courseUpdate" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
  <table id="courseInfo"   border="0" cellspacing="0" >
    <tr>
      <td> <label for="Cno">�γ̺�: </label></td>
      <td> <input type="text" name="Cno" id="Cno" value="<%=no%>"></td>
    </tr>
    <tr>
      <td> <label for="Cname">�γ���: </label></td>
      <td> <input type="text" name="Cname" id="Cname" value="<%=name%>"></td>
    </tr>
    <tr>
      <td> <label for="Ccredit">ѧ��: </label></td>
      <td> <input type="text" name="Ccredit" id="Ccredit" value="<%=Ccredit%>"></td>
    </tr>
    <tr>
      <td> <label for="prerequisite">ǰ��ѡ��: </label></td>
      <td> <input type="text" name="prerequisite" id="prerequisite" value="<%=prerequisite%>"></td>
    </tr>

  </table>
  <input  name="entity" id="entity" value="Course" type="hidden">
  <input  name="operation" id="operation" value="delete" type="hidden">
  <p>
    <input type="submit" name="Submit" value="�ύ" >
    <input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);">
  </p>
</form>


</body>
</html>