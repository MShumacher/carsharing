<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri" %>


<c:set var="baseUrl"
       value="${pageContext.request.contextPath}/registration"/>
<c:if test="${not empty error}">
    <div class="row">
        <div class="col s12 center">
            <div class="error">${error}</div>
        </div>
    </div>
</c:if>
<div class="row">
    <c:choose>
        <c:when test="${verified}">
            <h4 class="header">
                email Has Been Verified
            </h4>
            <div class="row">
                <div class="col s9"></div>
                <div class="col s3">
                    <a class="waves-effect waves-light btn right"
                       href="${pageContext.request.contextPath}/login">goToLoginPage</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <h4 class="header">
                enterYourKey
            </h4>
            <form:form class="col s12" method="POST"
                       action="${baseUrl}/verifying" modelAttribute="formModel">
                <div class="row">
                    <div class="input-field col s12">
                        <form:input path="verifyKey" type="text"/>
                        <form:errors path="verifyKey" cssClass="red-text"/>
                        <label for="verifyKey">verifyKey</label>
                    </div>
                </div>

                <c:if test="${not empty formModel.verifyKey}">
                    <h4 class="header">
                        invalidKey
                    </h4>
                </c:if>
                <div class="row">
                    <div class="col s9"></div>
                    <div class="col s3">
                        <button class="btn waves-effect waves-light right" type="submit">
                            verifyEmail
                        </button>
                    </div>
                </div>
            </form:form>
        </c:otherwise>
    </c:choose>

</div>