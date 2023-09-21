<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>
<form:form method="post" modelAttribute="newUser">
Last name: <form:input path="lastName"/><form:errors path="lastName" element="div" cssClass="errorDiv" /><br>
</form:form>
</body>
</html>
