<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.CoursePO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Web.javabean.ScorePO" %>

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
        <Tr>
            <th>课程号</th><th>学号</th><th>分数</th>
        </Tr>
        <%
            if (session != null) {
                List<ScorePO> list2 = (List<ScorePO>) session.getAttribute("scoreList1");
                for (int index=0;index<list2.size();++index){
                    ScorePO aa = list2.get(index);
                    System.out.println(list2.size());
        %>
        <tr>
            <td><%=aa.getCno()%></td>
            <td><%=aa.getSno()%></td>
            <td>
                <input type="text" name="grades[<%=index%>]" id="grade" value="<%=aa.getGrade()%>" />
                <input type="hidden" name="indices[<%=index%>]" value="<%=index%>" />
            </td>
        </tr>
        <%
                }
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
    <input  name="operation" id="operation" value="up" type="hidden">
    <p>
        <input type="submit" name="Submit" value="提交" >
        <input type="button" name="exit" value="退回门户" onclick="gotoPortal()">
    </p>
</form>

</body>
</html>