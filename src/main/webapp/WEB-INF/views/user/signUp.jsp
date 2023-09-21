<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/home.jsp"/>

<div class="container">
    <main>
        <div class="container w-25 padding-small text-center">
            <form:form method="post" modelAttribute="user">
                <h1>Rejestracja</h1>

                <div class="form-group">
                    <form:input path="firstName"  cssClass="form-control"  placeholder="Podaj imię"/>
                    <form:errors path="firstName" element="div" cssClass="errorDiv"/>
                </div>

                <div class="form-group">
                    <form:input path="lastName"  cssClass="form-control"  placeholder="Podaj nazwisko"/>
                    <form:errors path="firstName" element="div" cssClass="errorDiv"/>
                </div>

                <div class="form-group">
                    <form:input path="lastName"  cssClass="form-control"  placeholder="Podaj nazwisko"/>
                    <form:errors path="firstName" element="div" cssClass="errorDiv"/>
                </div>

                <div class="input-group has-validation">
                    <span class="input-group-text">@</span>
                    <form:input path="email"  cssClass="form-control"  placeholder="Podaj email"/>
                    <form:errors path="email" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                    </div>
                </div>

                <button class="w-100 btn btn-color " type="submit">Zatwierdź</button>

            </form:form>
        </div>
    </main>
</div>

