<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.CoursePO" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>增加课程</title>
    <script type="text/javascript">
        function checkStudentInfo(){
            var _no=document.courseAdd.Cno.value;
            var _name=document.courseAdd.Cname.value;
            var _credit=document.courseAdd.Ccredit.value;
            var _p=document.courseAdd.prerequisite.value;
            if (_no==null || _no.toString()==""){
                alert("课程号不能为空，请输入！");
                document.courseAdd.Cno.focus();
                return false;
            }
            if (_name==null || _name.toString()==""){
                alert("课程名不能为空，请输入！");
                document.courseAdd.Cname.focus();
                return false;
            }
            if (_credit==null || _credit.toString()==""){
                alert("年龄不能为空，请输入！");
                document.courseAdd.Ccredit.focus();
                return false;
            }

            return true;

        }

    </script>
</head>
<body>

<h3>增加学生记录</h3>
<form name="courseAdd" id="courseAdd" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
    <table id="courseInfo"   border="0" cellspacing="0" >
        <tr>
            <td> <label for="Cno">课程号: </label></td>
            <td> <input type="text" name="Cno" id="Cno"></td>
        </tr>
        <tr>
            <td> <label for="Cname">课程名: </label></td>
            <td> <input type="text" name="Cname" id="Cname"></td>
        </tr>
        <tr>
            <td> <label for="Ccredit">学分: </label></td>
            <td> <input type="text" name="Ccredit" id="Ccredit"></td>
        </tr>
        <tr>
            <label for="prerequisite">前向选修: </label><br>
            <select id="prerequisite" name="prerequisite">
        <%
            request.setCharacterEncoding("gbk");
            List<CoursePO> courses=(List<CoursePO>)request.getAttribute("courseList");
            for (CoursePO course : courses){
        %>
                <option <%=course.getPrerequisite() + courses.size()%> </option>
        <%}%>
            </select>
        </tr>
<%--        <tr>--%>
<%--            <td> <label for="prerequisite">前向选修: </label></td>--%>
<%--            <td> <input type="text" name="prerequisite" id="prerequisite"></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <label for="prerequisite">前向选修: </label><br>--%>
<%--            <select id="prerequisite" name="prerequisite">--%>
<%--                <c:forEach var="pp" items="${pps}">--%>
<%--                    <option value="${pp}">${pp}</option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>
<%--        </tr>--%>

    </table>
    <input  name="entity" id="entity" value="Course" type="hidden">
    <input  name="operation" id="operation" value="insert" type="hidden">
    <p>
        <input type="submit" name="Submit" value="提交" >
        <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
    </p>
</form>


</body>
</html>