<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>ɾ��ѧ����¼</title>

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

<h3>ɾ��ѧ����¼</h3>
<form name="studentDelete" method="post" action="/JavaAdvance/ApplicationController">
	<table id="studentInfo"  border="0" cellspacing="0" >
		<tr>			
	    	<td> <label for="no">ѧ��: </label></td>
			<td> <input type="text" name="no" id="no" value="<%=no%>" readonly></td>        
	     </tr>
	     <tr>			
	    	<td> <label for="name">����: </label></td>
			<td> <input type="text" name="name" id="name" value="<%=name%>" readonly></td>        
	     </tr>
	     <tr>			
	    	<td> <label for="sex">�Ա�: </label></td>
			<td><input type="text" name="sex" id="sex" value="<%=sex%>" readonly></td>        
	     </tr>  
	     <tr>			
	    	<td> <label for="age">����: </label></td>
			<td> <input type="text" name="age" id="age"  value="<%=age%>" readonly></td>        
	     </tr>   
	          
	     <tr>			
	    	<td> <label for="dept">ѧԺ: </label></td>
			<td> <input type="text" name="dept" id="dept"  value="<%=dept%>" readonly></td>        
	     </tr> 	          
	  </table>	
	  <input  name="entity" id="entity" value="Student" type="hidden">		
       	<input name="operation" id="operation" value="delete" type="hidden">
  <p> 
    <input type="submit" name="Submit" value="�ύ" >
    <input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);"> 
 </p>
 </form>
 
</body>
</html>