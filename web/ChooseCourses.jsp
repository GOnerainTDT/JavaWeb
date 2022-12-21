<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.CoursePO" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>增加课程</title>
  <script type="text/javascript">
    function gotoAdd(){
      document.scoreAdd.action="Choose1.jsp"
      document.scoreAdd.submit();
    }
    function gotoPortal(){
      document.location.href ="/JavaAdvance/portal.html";
    }
    function printSelection(selectedOption) {
      document.getElementById('selection').innerHTML = selectedOption.value;
    }
  </script>
</head>
<body>

<h3>增加课程记录</h3>
<form name="scoreAdd" id="scoreAdd" method="post" action="/JavaAdvance/ApplicationController">
  <table id="scoreInfo"   border="0" cellspacing="0" >
    <%

      if (session != null) {
        List<String> list = (List<String>) session.getAttribute("list");
        System.out.println(list.size());
        System.out.println(list.get(0));
    %>
    <tr> 学号 </tr><select name = "Sno">
      <c:forEach items="${list}" var="item">
        <option value="${item}">${item}</option>
      </c:forEach>
    </select>

    <%
      }
    %>

    <%
      if (session != null) {
        List<String> list1 = (List<String>) session.getAttribute("list1");
        System.out.println(list1.size());
    %>
    <tr> 课程号 </tr><select name = "Cno" onchange="printSelection(this.options[this.selectedIndex])">
      <c:forEach items="${list1}" var="item1">
        <option value="${item1}">${item1}</option>
      </c:forEach>
    </select>
    <div id="selection"></div>
    <%
      }
    %>

  </table>
  <input  name="entity" id="entity" value="Score" type="hidden">
  <input  name="operation" id="operation" value="insert" type="hidden">
  <p>
    <input type="submit" name="Submit" value="提交" >
    <input type="button" name="exit" value="退回门户" onclick="gotoPortal()">
  </p>
</form>

</body>
</html>