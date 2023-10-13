<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../home/header.jsp"/>

<script src="../../../resources/js/selectList/customSelect.js"></script>
<script src="../../../resources/js/validation/validate.js"></script>

<div class="container">
    <main>
        <div class="container w-25 padding-small text-center">
            <form:form method="post" modelAttribute="product" novalidate="validate">
                <h1>Dodaj produkt</h1>

                <div class="form-group">
                    <form:input path="name" cssClass="form-control" placeholder="Podaj nazwę"
                                required="true" minlength="2"/>
                    <form:errors path="name" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                        Podaj poprawne imię.
                    </div>
                </div>

                <div class="form-group">
                    <form:textarea path="description" cssClass="form-control" placeholder="Podaj opis"
                                   required="true" minlength="2"/>
                    <form:errors path="description" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                        Podaj poprawny opis.
                    </div>
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
                    <form:input path="color" type="text" class="form-control" placeholder="Podaj kolor"
                                disabled="true" required="true" minlength="2"/>
                    <div style="position:absolute; left:0; right:0; top:0; bottom:0;"></div>
                    <form:errors path="color" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                        Podaj poprawny kolor.
                    </div>
                </div>

                <div class="form-group" style="position:relative;">
                    <form:input path="scent" type="text" class="form-control" placeholder="Podaj zapach"
                                disabled="true" required="true" minlength="2"/>
                    <div style="position:absolute; left:0; right:0; top:0; bottom:0;"></div>
                    <form:errors path="scent" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                        Podaj poprawny zapach.
                    </div>
                </div>

                <div class="input-group d-grid column-gap-2 d-sm-flex justify-content-sm-center align-items-center">
                    <form:input path="price" type="number" cssClass="form-control" placeholder="Podaj cenę"
                                required="true" min="0.01" step="0.01"/>
                    <div>zł</div>
                    <div class="invalid-feedback">
                        Podaj poprawną cenę.
                    </div>
                </div>
                <form:errors path="price" element="div" cssClass="errorDiv"/>

                <div class="form-group" style="text-align: left">
                    Zdjęcie:<br>
                    <form:input path="img" type="file" cssClass="form-control" placeholder="Dodaj zdjęcie"
                                required="true"/>
                    <form:errors path="img" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                        Dodaj zdjęcie.
                    </div>
                </div>

                <div class="form-group">
                    <img src="" style="width: 50%">
                </div>

                <div class="form-group">
                    <form:select path="type" cssClass="form-select" required="true">
                        <form:option value="" id="placeholder" disabled="true" selected="true" label="Wybierz rodzaj"/>
                        <form:options items="${allProductTypes}"/>
                        <form:option value="new" label="Dodaj nowy"/>
                    </form:select>
                    <div class="invalid-feedback">
                        Podaj rodzaj.
                    </div>

                    <form:input path="type" cssClass="form-control" placeholder="Podaj rodzaj"
                                required="true" minlength="2"/>
                    <form:errors path="type" element="div" cssClass="errorDiv"/>
                    <div class="invalid-feedback">
                        Podaj poprawny rodzaj.
                    </div>
                </div>

                <div class="form-group">
                    <select name="categoryName" class="form-select" required>
                        <option value="" id="placeholder" disabled selected>Wybierz rodzaj</option>
                        <c:forEach items="${allCategoriesNames}" var="category">
                            <option>${category.value}</option>
                        </c:forEach>
                        <option value="new">Dodaj nową</option>
                    </select>
                    <div class="invalid-feedback">
                        Podaj kategorię.
                    </div>

                    <input name="categoryName" class="form-control" placeholder="Podaj kategorię"
                           required minlength="2"/>
                    <div class="errorDiv">${categoryMessage}</div>
                    <div class="invalid-feedback">
                        Podaj poprawną kategorię.
                    </div>
                </div>

                <button class="w-100 btn btn-color" type="submit">Zatwierdź</button>

            </form:form>
        </div>
    </main>
</div>


<script src="../../../resources/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="../../../resources/assets/dist/js/jquery.min.js"></script>



