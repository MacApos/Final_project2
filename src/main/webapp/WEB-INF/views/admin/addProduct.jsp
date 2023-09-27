<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>
<script src="/resources/js/image_preview/imagePreview.js"></script>

<div class="container">
    <main>
        <div class="container w-25 padding-small text-center">
            <form:form formMethod="POST" modelAttribute="product">
                <h1>Dodaj produkt</h1>

                <div class="form-group">
                    <form:input path="name" cssClass="form-control" placeholder="Podaj nazwę"/>
                    <form:errors path="name" element="div" cssClass="errorDiv"/>
                </div>

                <div class="form-group">
                    <form:textarea path="description" cssClass="form-control" placeholder="Podaj opis"/>
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

                <div class="form-group">
                    <form:input path="color" cssClass="form-control" placeholder="Podaj kolor"/>
                </div>

                <div class="form-group">
                    <form:input path="scent" cssClass="form-control" placeholder="Podaj zapach"/>
                </div>


                <div class="form-group d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <form:input path="price" type="number" cssClass="form-control" placeholder="Podaj cenę"/>
                    <div>zł</div>
                </div>
                <form:errors path="price" element="div" cssClass="errorDiv"/>

                <div class="form-group">
                    <form:input path="img" type="file" cssClass="form-control" placeholder="Podaj zapach"/>
                    <form:errors path="img" element="div" cssClass="errorDiv"/>
                </div>

                <div class="form-group">
                    <img src="" style="width: 50%">
                </div>

                <div class="form-group">
                    <form:select path="type" cssClass="form-select">
                        <form:option value="-" label="Wybierz rodzaj"/>
                        <form:options items="${allProductTypes}"/>
                    </form:select>
                </div>

                <div class="form-group">
                    <select name="category" class="form-select">
                        <c:forEach items="${allCategoriesNames}" var="category">
                            <option value="saab">${category}</option>
                        </c:forEach>
                    </select>
                </div>


                <button class="w-100 btn btn-color " type="submit">Zatwierdź</button>

            </form:form>
        </div>
    </main>
</div>

