<%-- 
    Document   : create.jsp
    Created on : Dec 8, 2021, 7:41:56 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Input new user information</h1>
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" required=""/>
            <font color="red">${requestScope.USER_ERROR.userIDError}</font></br>
            Full Name <input type="text" name="fullName" required=""/>
            <font color="red">${requestScope.USER_ERROR.fullNameError}</font></br>
            Role ID <input type="text" name="roleID" value="US" readonly="" /></br>
            Password <input type="password" name="password" required=""/></br>
            Confirm <input type="password" name="confirm" required=""/>
            <font color="red">${requestScope.USER_ERROR.confirmPasswordError}</font></br>
            <input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
