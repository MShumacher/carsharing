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

<c:set var="baseUrl" value="${pageContext.request.contextPath}/message" />
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="version" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="message" type="text" disabled="${readonly}" />
                <form:errors path="message" cssClass="red-text" />
                <label for="message">message</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="adId" disabled="${readonly}">
                    <form:options items="${adChoices}" />
                </form:select>
                <form:errors path="adId" cssClass="red-text" />
                <label for="adId">adId</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="senderId" disabled="${readonly}">
                    <form:options items="${userAccountChoices}" />
                </form:select>
                <form:errors path="senderId" cssClass="red-text" />
                <label for="senderId">senderEmail</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="recipientId" disabled="${readonly}">
                    <form:options items="${userAccountChoices}" />
                </form:select>
                <form:errors path="recipientId" cssClass="red-text" />
                <label for="recipientId">recepientEmail</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <div class="switch">
                    <label>not viewed<form:checkbox path="viewed" disabled="${readonly}" />
                        <span class="lever"></span>viewed
                </div>
                <label class="switch-label">viewed?</label> <br />
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

