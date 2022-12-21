<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.CoursePO" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>���ӿγ�</title>
    <script type="text/javascript">
        function checkStudentInfo(){
            var _no=document.courseAdd.Cno.value;
            var _name=document.courseAdd.Cname.value;
            var _credit=document.courseAdd.Ccredit.value;
            var _p=document.courseAdd.prerequisite.value;
            if (_no==null || _no.toString()==""){
                alert("�γ̺Ų���Ϊ�գ������룡");
                document.courseAdd.Cno.focus();
                return false;
            }
            if (_name==null || _name.toString()==""){
                alert("�γ�������Ϊ�գ������룡");
                document.courseAdd.Cname.focus();
                return false;
            }
            if (_credit==null || _credit.toString()==""){
                alert("ѧ�ֲ���Ϊ�գ������룡");
                document.courseAdd.Ccredit.focus();
                return false;
            }

            return true;

        }

    </script>
</head>
<body>

<h3>���ӿγ̼�¼</h3>
<form name="courseAdd" id="courseAdd" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
    <table id="courseInfo"   border="0" cellspacing="0" >
        <tr>
            <td> <label for="Cno">�γ̺�: </label></td>
            <td> <input type="text" name="Cno" id="Cno"></td>
        </tr>
        <tr>
            <td> <label for="Cname">�γ���: </label></td>
            <td> <input type="text" name="Cname" id="Cname"></td>
        </tr>
        <tr>
            <td> <label for="Ccredit">ѧ��: </label></td>
            <td> <input type="text" name="Ccredit" id="Ccredit"></td>
        </tr>
        <%
            CoursePO course;
            if (session != null) {
                List<String> list = (List<String>) session.getAttribute("list");
                list.add("��ǰ��ѡ��");
                System.out.println(list.size());
                System.out.println(list.get(0));
        %>
        <select name = "prerequisite">
            <c:forEach items="${list}" var="item">
                <option value="${item}">${item}</option>
            </c:forEach>
        </select>

        <%
            }
        %>

    </table>
    <input  name="entity" id="entity" value="Course" type="hidden">
    <input  name="operation" id="operation" value="insert" type="hidden">
    <p>
        <input type="submit" name="Submit" value="�ύ" >
        <input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);">
    </p>
</form>


</body>
</html>