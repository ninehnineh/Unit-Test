<%-- 
    Document   : viewCart
    Created on : Oct 7, 2021, 10:52:21 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="shopping.Tea"%>
<%@page import="shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <h1>Your selected tea: </h1>
        <c:if test="${sessionScope.CART == null}">
            <font color="red"><h2>Empty Cart!</h2>
        </c:if>
        <c:if test="${sessionScope.CART != null}">
            <c:if test="${not empty sessionScope.CART}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Remove</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="0"></c:set>
                        <c:forEach var="item" varStatus="counter" items="${sessionScope.CART.getCart()}">
                            <c:set var="total" value="${total + item.value.price * item.value.quantity}"></c:set>
                            <form action="MainController">
                                <tr>
                                    <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="id" value="${item.value.id}" readonly=""/>
                                </td>
                                <td>${item.value.name}</td>
                                <td>${item.value.price}$</td>
                                <td>
                                    <input type="number" name="quantity" value="${item.value.quantity}" min="1"/>
                                </td>
                                <td>${item.value.quantity * item.value.price }</td>
                                <td>
                                    <input type="submit" name="action" value="Remove"/>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Edit"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <h1>Total:${total}$</h1>
        </c:if>
    </c:if>
    <a href="shopping.jsp">Add More</a>
    <form action="MainController">
        <input type="submit" name="action" value="Checkout"/>
    </form>
</body>
</html>
