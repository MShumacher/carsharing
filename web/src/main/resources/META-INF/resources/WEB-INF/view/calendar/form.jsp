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

<c:set var="baseUrl" value="${pageContext.request.contextPath}/calendar" />
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="version" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:select path="renterId" disabled="${readonly}">
                    <form:options items="${userAccountChoices}" />
                </form:select>
                <form:errors path="renterId" cssClass="red-text" />
                <label for="renterId">renterEmail</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="carId" disabled="${readonly}">
                    <form:options items="${carChoices}" />
                </form:select>
                <form:errors path="carId" cssClass="red-text" />
                <label for="carId">carPlate</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="start" type="text" disabled="${readonly}"
                            cssClass="datepicker" />
                <form:errors path="start" cssClass="red-text" />
                <label for="start">start</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="end" type="text" disabled="${readonly}"
                            cssClass="datepicker" />
                <form:errors path="end" cssClass="red-text" />
                <label for="end">end</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="totalPrice" type="number" disabled="${readonly}" />
                <form:errors path="totalPrice" cssClass="red-text" />
                <label for="totalPrice">totalPrice</label>
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

