<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="Web.javabean.StudentPO"%>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.CoursePO" %>
<%@ page import="Web.javabean.ScorePO" %>
<%
  request.setCharacterEncoding("gbk");
  List<ScorePO> scores=(List<ScorePO>)session.getAttribute("scoreList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <script language="javascript">
    function choose(number){
      var rowNo = eval(number);
      document.scoreAdd.Cno.value = document.all("clistTabled").rows[rowNo].cells[0].innerText;
      document.scoreAdd.Sno.value = document.all("clistTabled").rows[rowNo].cells[1].innerText;
      document.scoreAdd.grade.value = document.all("clistTabled").rows[rowNo].cells[2].innerText;

      if (document.all("clistTabled").rows.length>1) {
        for (var i=1; i<document.all("clistTabled").rows.length; i++){
          document.all("clistTabled").rows[i].style.backgroundColor="";
        }
      }
      document.all("clistTabled").rows[rowNo].style.backgroundColor="#ff998f";
    }
    function gotoPortal(){
      document.location.href ="/JavaAdvance/portal.html";
    }
  </script>
</head>

<body>
<form name="scoreAdd" id="scoreAdd" method="post" action="/JavaAdvance/ApplicationController">
<Table name="clistTabled" id="clistTabled" border=1>
  <Tr>
    <th>课程号</th><th>学号</th><th>成绩</th>
  </Tr>
  <%
    ScorePO score;
    if (session != null) {
      List<ScorePO> list2 = (List<ScorePO>) session.getAttribute("scoreList1");
      for (int index=0;index<list2.size();index++){
        score=list2.get(index);
        System.out.println("scoresize+ " + list2.size());
  %>
  <tr onclick="choose('<%=index+1%>')">
    <td><%=score.getCno()%></td>
    <td><%=score.getSno()%></td>
    <td><%=score.getGrade()%></td>
  </tr>
  <%}
  }
  %>
</Table>
  <input type=hidden name="Cno" id="no" />
  <input type=hidden name="Sno" id="name" />
  <input type=hidden name="grade" id="credit" />
<input  name="entity" id="entity" value="Score" type="hidden">
<input  name="operation" id="operation" value="delete" type="hidden">
<p>
  <input type="submit" name="Submit" value="提交" >
  <input type="button" name="exit" value="退回门户" onclick="gotoPortal()">
</p>
</form>
</body>
</html>
