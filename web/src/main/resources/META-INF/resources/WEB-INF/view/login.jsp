<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<div class="row">
    <div class="input-field col s12 center">
        <h2>
            login
        </h2>
    </div>
</div>
<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <form name='loginForm' action="<c:url value='login' />" method='POST'>
            <div class="row">
                <div class="input-field col s12 center">
                    <input type='text' name='email' value=''><label for="email">email:</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <input type='password' name='password' /><label for="password">password:</label>
                </div>
            </div>
            <c:if test="${not empty error}">
                <div class="row">
                    <div class="col s12 center">
                        <div class="error">${error}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="row">
                    <div class="col s12 center">
                        <div class="msg">${msg}</div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col s12 center">
                    <button class="btn waves-effect waves-light" type="submit">login</button>
                </div>
            </div>
            <div class="row">
                <div class="col s12 center">
                    <a href="${pageContext.request.contextPath}/registration">registration</a>
                </div>
            </div>
        </form>
    </div>
    <div class="col s3"></div>
</div>