<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html><body>
<table border=’1’ cellspacing=’0’ cellpadding=’5’>
  <tr>
    <% for ( int i=0; i<10; i++ ) { %>
    <td><%= i %></td>
    <% } %>
  </tr>
  <tr>
    <% for ( int i=0; i<10; i++ ) { %>
    <td><%= i *i%></td>
    <% } %>
  </tr>
</table>
</body></html>
