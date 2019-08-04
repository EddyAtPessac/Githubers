<%--
  Created by IntelliJ IDEA.
  User: eddy
  Date: 12/07/19
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="header.jsp"%>
<head>
    <title >Githubers</title>
</head>
<body>
<H1 class="text-danger" class="text-center">Githubers list</H1>
<div class="table-responsive">
<table class="table table-bordered  table-striped">
    <thead class="thead-dark">
    <tr class="text-center" >
        <th scope="col">Untrack</th>
        <th  scope="col">gitId</th>
        <th scope="col"> Name</th>
        <th scope="col">Email</th>
        <th scope="col">Login</th>
        <th scope="col">avatarUrl</th>
    </tr>

    <c:forEach items="${gitlist}" var="agit">
        <tr class="text-center" scope="row ">
            <td><button type="button" class="btn btn-outline-info"  onclick="window.location.href = '\gitkiller?Id=${agit.id}'">Untrack</button></td>
            <td>${agit.gitId}</td>
            <td><c:out value="${agit.name}" /></td>
            <td><c:out value="${agit.email}" /></td>
            <td><c:out value="${agit.login}" /></td>
            <td><c:out value="${agit.avatarUrl}" /></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
