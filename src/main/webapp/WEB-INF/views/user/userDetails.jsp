<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>


<div class="container w-75 padding-small text-center ">
    <h1>Dane użytkownika</h1>
    <div class="container" style="width: 55%">

        <div class="row form-group">
            <div class="col text-end">
                Imię:
            </div>
            <div class="col text-start">
                ${showUser.firstName}
            </div>
        </div>
        <div class="row form-group border-top border-black border-0">
            <div class="col text-end">
                Nazwisko:
            </div>
            <div class="col text-start">
                ${showUser.lastName}
            </div>
        </div>
        <div class="row form-group border-top border-black border-0">
            <div class="col text-end">
                Email:
            </div>
            <div class="col text-start ">
                ${showUser.email}
            </div>
        </div>
        <div class="row form-group border-top border-black border-0">
            <div class="col text-end">
                Liczba produktów
                w koszyku:
            </div>

            <c:choose>
                <c:when test="${not empty sessionScope.cart.itemsQuantity}">
                    <c:set var="itemsQuantity" value="${sessionScope.cart.itemsQuantity}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="itemsQuantity" value="0"/>
                </c:otherwise>
            </c:choose>

            <div class="col text-start">
                ${sessionScope.cart.itemsQuantity}
            </div>
        </div>
        <div class="form-group d-grid gap-2 d-sm-flex justify-content-sm-center">
            <button class="w-50 btn btn-color" type="submit">Edytuj</button>
            <button class="w-50 btn btn-color" type="submit">Usuń</button>
        </div>

        <div class="form-group d-grid gap-2 d-sm-flex justify-content-sm-center">
            <button class="w-50 btn btn-color" type="submit">Wyloguj</button>
        </div>
    </div>
</div>

