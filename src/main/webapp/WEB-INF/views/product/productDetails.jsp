<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>

<div class="row row-cols-2 row-cols-lg-3">
    <div>
        <img src="../../../resources/product_images/${product.img}" style="width: 50%">
    </div>
    <div>
        <div>Nazwa: ${product.name}</div>
        <div>Opis: ${product.description}</div>
        <c:if test="${not empty product.size}">
            <div>Kolor: ${product.color}</div>
        </c:if>
        <c:if test="${not empty product.color}">
            <div>Rozmiar: ${product.size}</div>
        </c:if>
        <c:if test="${not empty product.scent}">
            <div>Zapach: ${product.scent}</div>
        </c:if>
        <div> Cena: ${product.price} zł </div>
        <div>Rodzaj: ${product.type}</div>
        <div>Kategoria: ${product.category.name}</div>
        <form:form method="post" action="/cart/${product.id}" modelAttribute="cartItem">
            <div>Liczba:
                <form:input path="quantity" type="number" cssClass="form-control" placeholder="Podaj liczbę artykułów"/>
                <form:errors path="quantity" element="div" cssClass="errorDiv"/>
            </div>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <button class="w-50 btn btn-color" type="submit" name="add">Dodaj do koszyka</button>
                <button class="w-50 btn btn-color" type="submit" name="order">Zamów</button>
            </div>
        </form:form>

    </div>

</div>




