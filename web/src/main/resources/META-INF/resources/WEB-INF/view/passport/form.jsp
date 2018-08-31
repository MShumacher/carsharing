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

<c:set var="baseUrl" value="${pageContext.request.contextPath}/passport" />
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="version" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="fullName" type="text" disabled="${readonly}" />
                <form:errors path="fullName" cssClass="red-text" />
                <label for="fullName">fullName</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="number" type="text" disabled="${readonly}" />
                <form:errors path="number" cssClass="red-text" />
                <label for="number">number</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="issuePlace" type="text" disabled="${readonly}" />
                <form:errors path="issuePlace" cssClass="red-text" />
                <label for="issuePlace">issuePlace</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="issueDate" type="text" disabled="${readonly}"
                            cssClass="datepicker" />
                <form:errors path="issueDate" cssClass="red-text" />
                <label for="issueDate">issueDate</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="birthPlace" type="text" disabled="${readonly}" />
                <form:errors path="birthPlace" cssClass="red-text" />
                <label for="birthPlace">birthPlace</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="birthday" type="text" disabled="${readonly}"
                            cssClass="datepicker" />
                <form:errors path="birthday" cssClass="red-text" />
                <label for="birthday">birthday</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="userAccountId" disabled="${readonly}">
                    <form:options items="${userAccountWithCurrentPassportOrWithoutChoices}" />
                </form:select>
                <form:errors path="userAccountId" cssClass="red-text" />
                <label for="userAccountId">userAccountEmail</label>
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

