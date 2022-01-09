<%-- 
    Document   : user
    Created on : Dec 3, 2021, 8:53:47 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="user.DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
    <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"></c:set>
    <c:if test="${loginUser == null || loginUser.roleID ne 'US'}">
        <c:redirect url="login.html"></c:redirect>
    </c:if>
    <h1>User Information:</h1>
    User ID: ${sessionScope.LOGIN_USER.userID}</br>
    Full Name: ${sessionScope.LOGIN_USER.fullName}</br>
    Role ID: ${sessionScope.LOGIN_USER.roleID}</br>
    Password: ${sessionScope.LOGIN_USER.password}</br>
    Status: ${sessionScope.LOGIN_USER.status}</br>

    <c:url var="logoutLink" value="MainController">
        <c:param name="action" value="Logout"></c:param>
    </c:url>
    <a href="${logoutLink}">Logout</a>
</body>
</html>
