<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>����ѧ����¼</title>
<script type="text/javascript">
function checkStudentInfo(){   
    var _name=document.studentUpdate.name.value;
    var _sex=document.studentUpdate.sex.value;
    var _age=document.studentUpdate.age.value;
	var _dept=document.studentUpdate.dept.value;	
	if (_name==null || _name.toString()==""){
	 	alert("��������Ϊ�գ������룡");
	 	document.studentUpdate.name.focus();
	 	return false;
	}
	if (_age==null || _age.toString()==""){
	 	alert("���䲻��Ϊ�գ������룡");
	 	document.studentUpdate.age.focus();
	 	return false;
	}
	if (_dept==null || _dept.toString()==""){
	 	alert("Ժϵ����Ϊ�գ������룡");
	 	document.studentUpdate.dept.focus();
	 	return false;
	}
	else{
	   var intAge=parseInt(_age,10);
	   if (isNaN(intAge)||intAge<10||intAge>40){		   
	   		alert("���������˲�ǡ����ֵ�����������룡");
	   		document.studentUpdate.age.focus();
	   		return false;
	   }		   	
	
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
 <form name="studentUpdate" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
	<table id="studentInfo"  border="0" cellspacing="0" >

		<tr>			
	    	<td> <label for="no">ѧ��: </label></td>
			<td> <input type="text" name="no" id="no" value="<%=no%>" readonly></td>        
	     </tr>
	     <tr>			
	    	<td> <label for="name">����: </label></td>
			<td> <input type="text" name="name" id="name" value="<%=name%>"></td>        
	     </tr>
	     <tr>			
	    	<td> <label for="sex">�Ա�: </label></td>
			<td> 
			 	<select name="sex">
	   				<% if (sex.equals("Ů")){ %>
			    		<option>��</option>
			    		<option selected>Ů</option>  
					<% }else{ %>
			  		<option selected>��</option>
			    		<option>Ů</option>  
					<% }%>  		
		 		</select> 
		 	</td>        
	     </tr>  
	     <tr>			
	    	<td> <label for="age">����: </label></td>
			<td> <input type="text" name="age" id="age"  value="<%=age%>"></td>        
	     </tr>   
	          
	     <tr>			
	    	<td> <label for="dept">ѧԺ: </label></td>
			<td> <input type="text" name="dept" id="dept"  value="<%=dept%>"></td>        
	     </tr>       
	          
	  </table>
 	   <input  name="entity" id="entity" value="Student" type="hidden">		      
       <input name="operation" id="operation" value="update" type="hidden">

	 <p> 
	    <input type="submit" name="Submit" value="�ύ">
	    <input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);"> 
	 </p>
  </form>
</body>
</html>