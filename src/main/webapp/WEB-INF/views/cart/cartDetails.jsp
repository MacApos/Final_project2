<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>

<c:choose>
    <c:when test="${empty cartItems}">
        Twój koszyk jest pusty.
    </c:when>
    <c:otherwise>
        <c:forEach var="cartItem" items="${cartItems}">
            <div class="row row-cols-2 row-cols-lg-3">
                <div>
                    <img src="../../../resources/product_images/${cartItem.value.img}.jpg" style="width: 50%">
                </div>
                <div>
                    <div>Nazwa: ${cartItem.value.name}</div>
                    <div>Opis: ${cartItem.value.description}</div>
                    <c:if test="${not empty cartItem.value.size}">
                        <div>Kolor: ${cartItem.value.color}</div>
                    </c:if>
                    <c:if test="${not empty cartItem.value.color}">
                        <div>Rozmiar: ${cartItem.value.size}</div>
                    </c:if>
                    <c:if test="${not empty cartItem.value.scent}">
                        <div>Zapach: ${cartItem.value.scent}</div>
                    </c:if>
                    <div>Rodzaj: ${cartItem.value.type}</div>
                    <div>Kategoria: ${cartItem.value.category.name}</div>
                    <div> Quantity: ${cartItem.key.quantity}</div>
                    <form:form method="post" action="/cart/deleteFromCart?id=${cartItem.key.id}">
                        <button class="w-50 btn btn-color" type="submit">
                            Usuń
                        </button>
                    </form:form>
                </div>
            </div>
        </c:forEach>
        Cena całkowita: ${totalPriceForCart} zł
    </c:otherwise>
</c:choose>