<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 21.09.2023
  Time: 00:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>


        td {
            vertical-align: top;
        }
    </style>
</head>
<body>
<table>
    <form:form method="post" modelAttribute="user">
        <tr>
            <td>First name:</td>
            <td><form:input path="firstName"/><form:errors path="firstName" element="div" cssClass="errorDiv"/></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><form:input path="lastName"/><form:errors path="lastName" element="div" cssClass="errorDiv"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email"/><form:errors path="email" element="div" cssClass="errorDiv"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input path="password"/>
                <div class="errorDiv">
                    <form:errors path="password" cssClass="errorDiv"/><br>
                    <c:forEach items="${passwordMessages}" var="message">
                        ${message}<br>
                    </c:forEach>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </form:form>
</table>
</body>
</html>
