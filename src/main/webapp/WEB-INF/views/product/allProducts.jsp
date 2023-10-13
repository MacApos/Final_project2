<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>

<div id="admin"><c:out value="${sessionScope.admin}"/></div>
<c:forEach items="${allProducts}" var="product">

    <%--    row-cols-1 row-cols-lg-3--%>
    <div class="container">
        <div class="row align-items-start">
            <div class="col">
                <img src="../../../resources/product_images/${product.img}" style="width: 70%">
            </div>
            <div class="col">
                <a href="<c:url value = "/product/${product.id}"/>">${product.name}</a>
            </div>
            <div class="col-2">
                    ${product.price} zł


                <form:form method="post" action="/cart/${product.id}">
                    <button class="w-100 btn btn-color" type="submit" name="add">Dodaj do koszyka</button>
                </form:form>
                <form:form method="post" action="/cart/${product.id}">
                    <button class="w-100 btn btn-color" type="submit" name="add">Edytuj</button>
                </form:form>
                <form:form method="post" action="/cart/${product.id}">
                    <button class="w-100 btn btn-color" type="submit" name="add">Usuń</button>
                </form:form>

            </div>
        </div>
    </div>


</c:forEach>



