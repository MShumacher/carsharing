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

<c:set var="baseUrl" value="${pageContext.request.contextPath}/useraccount" />
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="version" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="email" type="text" disabled="${readonly}" />
                <form:errors path="email" cssClass="red-text" />
                <label for="email">email</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="password" type="text" disabled="${readonly}" />
                <form:errors path="password" cssClass="red-text" />
                <label for="password">password</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="name" type="text" disabled="${readonly}" />
                <form:errors path="name" cssClass="red-text" />
                <label for="name">name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="photoLink" type="text" disabled="${readonly}" />
                <form:errors path="photoLink" cssClass="red-text" />
                <label for="photoLink">photoLink</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="phone" type="text" disabled="${readonly}" />
                <form:errors path="phone" cssClass="red-text" />
                <label for="phone">phone</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="role" disabled="${readonly}">
                    <form:options items="${roleChoices}" />
                </form:select>
                <form:errors path="role" cssClass="red-text" />
                <label for="role">role</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="passportId" disabled="${readonly}">
                    <form:options items="${passportWithCurrentUserAccountOrWithoutChoices}" />
                </form:select>
                <form:errors path="passportId" cssClass="red-text" />
                <label for="passportId">passportNumber</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="drivingLicenseId" disabled="${readonly}">
                    <form:options items="${drivingLicenseWithCurrentUserAccountOrWithoutChoices}" />
                </form:select>
                <form:errors path="drivingLicenseId" cssClass="red-text" />
                <label for="drivingLicenseId">drivingLicenseName</label>
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

