<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>

<div class="container w-25 padding-small text-center">
    <form:form method="post" modelAttribute="user">
        <h1>Logowanie</h1>

        <div class="form-group">
            <form:input path="email" cssClass="form-control" placeholder="Podaj email"/>
            <div class="errorDiv">${emailMessage}</div>
        </div>

        <div class="form-group">
            <form:password path="password" cssClass="form-control" placeholder="Podaj hasło"/>
            <div class="errorDiv">${passwordMessage}</div>
        </div>

        <button class="w-100 btn btn-color" type="submit">Zatwierdź</button>

    </form:form>
</div>

