<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>
<script src="/resources/js/image_preview/imagePreview.js"></script>
<script src="/resources/js/selectList/customSelect.js"></script>
<script src="/resources/js/validation/validate.js"></script>

<div class="container">
    <main>
        <div class="container w-25 padding-small text-center">
            <form:form formMethod="POST" modelAttribute="product" novalidate="validate">
                <h1>Dodaj produkt</h1>

                <div class="form-group">
                    <form:input path="name" cssClass="form-control" placeholder="Podaj nazwę"
                                required="true" minlength="2"/>
                    <form:errors path="name" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                        Please provide a valid name.
                    </div>
                </div>

                <div class="form-group">
                    <form:textarea path="description" cssClass="form-control" placeholder="Podaj opis"
                                   required="true" minlength="2"/>
                    <form:errors path="description" element="div" cssClass="errorDiv"/>
                </div>

                <c:set var="sizes" value="${['XS', 'S','M','L','XL']}"/>

                <div class="form-group" style="text-align: left">
                    Rozmiar:<br>
                    <c:forEach items="${sizes}" var="size">
                        <div class="form-check form-check-inline">
                                ${size} <form:radiobutton path="size" cssClass="form-check-input" value="${size}"/>
                        </div>
                    </c:forEach>
                </div>

                <div class="form-group" style="position:relative;">
                    <input name="color" type="text" class="form-control" placeholder="Podaj kolor" disabled/>
                    <div style="position:absolute; left:0; right:0; top:0; bottom:0;"></div>
                </div>

                <div class="form-group" style="position:relative;">
                    <form:input path="scent" type="text" class="form-control" placeholder="Podaj zapach" disabled="true"/>
                    <div style="position:absolute; left:0; right:0; top:0; bottom:0;"></div>
                </div>

                <div class="form-group d-grid gap-2 d-sm-flex justify-content-sm-center align-items-center">
                    <form:input path="price" type="number" cssClass="form-control" placeholder="Podaj cenę"
                                required="true" min="0.01" step="0.01"/>
                    <div>zł</div>
                </div>
                <form:errors path="price" element="div" cssClass="errorDiv"/>

                <%--                <div class="form-group">--%>
                <%--                    <form:input path="img" type="file" cssClass="form-control" placeholder="Dodaj zdjęcie"/>--%>
                <%--                    <form:errors path="img" element="div" cssClass="errorDiv"/>--%>
                <%--                </div>--%>

                <div class="form-group">
                    <div class="input-group ">
                        <button class="btn btn-light text-truncate"
                                type="button">Szukaj
                        </button>
                        <div class="form-control text-start text-truncate">
                            Dodaj zdjęcie
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <img src="../../../resources/product_images/" style="width: 50%">
                </div>

                <div class="form-group">
                    <select name="type" class="form-select">
                        <option value="placeholder" disabled selected>Wybierz rodzaj</option>
                        <c:forEach items="${allProductTypes}" var="type">
                            <option>${type}</option>
                        </c:forEach>
                        <option value="new">Dodaj nowy</option>
                    </select>
                    <form:input path="type" cssClass="form-control d-none" placeholder="Podaj rodzaj"/>
                </div>

                <div class="form-group">
                    <select name="category" class="form-select">
                        <option value="placeholder" disabled selected>Wybierz rodzaj</option>
                        <c:forEach items="${allCategoriesNames}" var="category">
                            <option>${category}</option>
                        </c:forEach>
                        <option value="new">Dodaj nową</option>
                    </select>
                    <form:input path="category" cssClass="form-control d-none" placeholder="Podaj kategorię"/>
                </div>

                <button class="w-100 btn btn-color " type="submit">Zatwierdź</button>

            </form:form>
        </div>
    </main>
</div>


<script src="../../../resources/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="../../../resources/assets/dist/js/jquery.min.js"></script>



