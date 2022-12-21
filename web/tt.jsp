<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@ page import="Web.javabean.CoursePO" %>
<%
  if (session != null) {
    List<String> list = (List<String>) session.getAttribute("list");
%>

<select>
  <c:forEach items="${list}" var="item">
    <option value="${item}">${item}</option>
  </c:forEach>
</select>

<%
  }
%>