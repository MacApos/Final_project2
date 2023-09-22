<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>


<c:forEach items="${allProducts}" var="product">
    <div class="row row-cols-1 row-cols-lg-3">
        <div>
            <img src="../../../resources/product_images/${product.img}.jpg" style="width: 70%">
        </div>
        <div>
            <a href = "<c:url value = "/product/${product.id}"/>">${product.name}</a>
        </div>
        <div>
                ${product.price} zł
        </div>
    </div>
</c:forEach>



