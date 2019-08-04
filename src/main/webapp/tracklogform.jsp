<%--
  Created by IntelliJ IDEA.
  User: eddy
  Date: 01/08/19
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="header.jsp"%>
<html>
    <head>
        <title>Which you search for</title>
    </head>
<body>

<c:if test = "${gitlog}!=null">
    <p> ${gitlog} is not found. try again </p>
</c:if>

<form method="post" >

    <div class="form-group">
        <label for="login">Github loggin</label>
        <input type="text" class="form-control" id="login" aria-describedby="emailHelp" placeholder="Enter log">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
<%--

    <p>
        Search for loggin :<br>
        <input type="text" name="login"><br>
    </p>
    <p>
        <input type="submit">
    </p>
--%>
</form>
</body>
</html>

