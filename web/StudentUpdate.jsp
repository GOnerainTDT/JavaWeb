<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>����ѧ����¼</title>
	<script type="text/javascript">
		function checkStudentInfo(){
			var _no=document.studentAdd.no.value;
			var _name=document.studentAdd.name.value;
			var _sex=document.studentAdd.sex.value;
			var _age=document.studentAdd.age.value;
			var _dept=document.studentAdd.dept.value;
			if (_no==null || _no.toString()==""){
				alert("ѧ�Ų���Ϊ�գ������룡");
				document.studentAdd.no.focus();
				return false;
			}
			if (_name==null || _name.toString()==""){
				alert("��������Ϊ�գ������룡");
				document.studentAdd.name.focus();
				return false;
			}
			if (_age==null || _age.toString()==""){
				alert("���䲻��Ϊ�գ������룡");
				document.studentAdd.age.focus();
				return false;
			}else{
				var intAge=parseInt(_age,10);
				if (isNaN(intAge)||intAge<10||intAge>40){
					alert("���������˲�ǡ����ֵ�����������룡");
					document.studentAdd.age.focus();
					return false;
				}

			}
			if (_dept==null || _dept.toString()==""){
				alert("Ժϵ����Ϊ�գ������룡");
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

<h3>����ѧ����¼</h3>
<form name="studentAdd" id="studentAdd" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
	<table id="studentInfo"   border="0" cellspacing="0" >
		<tr>
			<td> <label for="no">ѧ��: </label></td>
			<td> <input type="text" name="no" id="no" value="<%=no%>"></td>
		</tr>
		<tr>
			<td> <label for="name">����: </label></td>
			<td> <input type="text" name="name" id="name" value="<%=name%>"></td>
		</tr>
		<tr>
			<td> <label for="sex">�Ա�: </label></td>
			<td>
				<select name="sex" id="sex">
					<option selected="selected">��</option>
					<option>Ů</option>
				</select>
			</td>
		</tr>
		<tr>
			<td> <label for="age">����: </label></td>
			<td> <input type="text" name="age" id="age" value="<%=age%>"></td>
		</tr>

		<tr>
			<td> <label for="dept">ѧԺ: </label></td>
			<td> <input type="text" name="dept" id="dept" value="<%=dept%>"></td>
		</tr>

	</table>
	<input  name="entity" id="entity" value="Student" type="hidden">
	<input  name="operation" id="operation" value="update" type="hidden">
	<p>
		<input type="submit" name="Submit" value="�ύ" >
		<input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);">
	</p>
</form>


</body>
</html>