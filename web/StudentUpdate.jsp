<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>更新学生记录</title>
	<script type="text/javascript">
		function checkStudentInfo(){
			var _no=document.studentAdd.no.value;
			var _name=document.studentAdd.name.value;
			var _sex=document.studentAdd.sex.value;
			var _age=document.studentAdd.age.value;
			var _dept=document.studentAdd.dept.value;
			if (_no==null || _no.toString()==""){
				alert("学号不能为空，请输入！");
				document.studentAdd.no.focus();
				return false;
			}
			if (_name==null || _name.toString()==""){
				alert("姓名不能为空，请输入！");
				document.studentAdd.name.focus();
				return false;
			}
			if (_age==null || _age.toString()==""){
				alert("年龄不能为空，请输入！");
				document.studentAdd.age.focus();
				return false;
			}else{
				var intAge=parseInt(_age,10);
				if (isNaN(intAge)||intAge<10||intAge>40){
					alert("年龄输入了不恰当的值，请重新输入！");
					document.studentAdd.age.focus();
					return false;
				}

			}
			if (_dept==null || _dept.toString()==""){
				alert("院系不能为空，请输入！");
				document.studentAdd.dept.focus();
				return false;
			}

			return true;

		}

	</script>
</head>
<body>

<%
	request.setCharacterEncoding("gbk");
	String no=request.getParameter("no");
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String age=request.getParameter("age");
	String dept=request.getParameter("dept");
%>

<h3>增加学生记录</h3>
<form name="studentAdd" id="studentAdd" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
	<table id="studentInfo"   border="0" cellspacing="0" >
		<tr>
			<td> <label for="no">学号: </label></td>
			<td> <input type="text" name="no" id="no" value="<%=no%>"></td>
		</tr>
		<tr>
			<td> <label for="name">姓名: </label></td>
			<td> <input type="text" name="name" id="name" value="<%=name%>"></td>
		</tr>
		<tr>
			<td> <label for="sex">性别: </label></td>
			<td>
				<select name="sex" id="sex">
					<option selected="selected">男</option>
					<option>女</option>
				</select>
			</td>
		</tr>
		<tr>
			<td> <label for="age">年龄: </label></td>
			<td> <input type="text" name="age" id="age" value="<%=age%>"></td>
		</tr>

		<tr>
			<td> <label for="dept">学院: </label></td>
			<td> <input type="text" name="dept" id="dept" value="<%=dept%>"></td>
		</tr>

	</table>
	<input  name="entity" id="entity" value="Student" type="hidden">
	<input  name="operation" id="operation" value="update" type="hidden">
	<p>
		<input type="submit" name="Submit" value="提交" >
		<input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
	</p>
</form>


</body>
</html>