<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:choose>
  <c:when test="${readonly}">
<h4 class="header">View</h4>
  </c:when>
  <c:otherwise>
<h4 class="header">Edit</h4>
  </c:otherwise>
</c:choose>

<c:if test="${'PersistenceException' eq error}">
	<h5 class="header red-text">persist. exception</h5>
</c:if>
<c:if test="${'ObjectOptimisticLockingFailureException' eq error}">
    <h5 class="header red-text">ObjectOptimisticLockingFailureException</h5>
</c:if>

<c:set var="baseUrl" value="${pageContext.request.contextPath}/car" />
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="version" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:select path="brandId" disabled="${readonly}">
                    <form:options items="${brandChoices}" />
                </form:select>
                <label for="brandId">brandName</label>
            </div>
        </div>
        <%--TODO automatic loading models--%>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="modelId" disabled="${readonly}">
                    <form:options items="${modelChoices}" />
                    <%--<form:options items="${modelChoicesByBrand}" />--%>
                </form:select>
                <label for="modelId">modelName</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="year" type="number" disabled="${readonly}" />
                <form:errors path="year" cssClass="red-text" />
                <label for="year">year</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="plate" type="text" disabled="${readonly}" />
                <form:errors path="plate" cssClass="red-text" />
                <label for="plate">plate</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="mileage" type="number" disabled="${readonly}" />
                <form:errors path="mileage" cssClass="red-text" />
                <label for="mileage">mileage</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="seats" type="number" disabled="${readonly}" />
                <form:errors path="seats" cssClass="red-text" />
                <label for="seats">seats</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="gearboxId" disabled="${readonly}">
                    <form:options items="${gearboxChoices}" />
                </form:select>
                <form:errors path="gearboxId" cssClass="red-text" />
                <label for="gearboxId">gearboxName</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="bodyTypeId" disabled="${readonly}">
                    <form:options items="${bodyTypeChoices}" />
                </form:select>
                <form:errors path="bodyTypeId" cssClass="red-text" />
                <label for="bodyTypeId">bodyTypeName</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="driveId" disabled="${readonly}">
                    <form:options items="${driveChoices}" />
                </form:select>
                <form:errors path="driveId" cssClass="red-text" />
                <label for="driveId">driveName</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="engineTypeId" disabled="${readonly}">
                    <form:options items="${engineTypeChoices}" />
                </form:select>
                <form:errors path="engineTypeId" cssClass="red-text" />
                <label for="engineTypeId">engineTypeName</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="charge" type="number" disabled="${readonly}" />
                <form:errors path="charge" cssClass="red-text" />
                <label for="charge">charge</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="conditions" type="text" disabled="${readonly}" />
                <form:errors path="conditions" cssClass="red-text" />
                <label for="conditions">conditions</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="insurance" type="text" disabled="${readonly}" />
                <form:errors path="insurance" cssClass="red-text" />
                <label for="insurance">insurance</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="carParameterIds" disabled="${readonly}">
                    <form:options items="${carParameterChoices}" />
                </form:select>
                <form:errors path="carParameterIds" cssClass="red-text" />
                <label for="carParameterIds">carParameters</label>
            </div>
        </div>
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right" type="submit">save<i class="material-icons right">save</i></button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="${baseUrl}"><i class="material-icons left">reply</i>back
                </a>
            </div>
        </div>
    </form:form>
</div>

