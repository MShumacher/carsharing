<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="header">new user registration</h4>

<c:if test="${'DataIntegrityViolationException' eq error}">
	<h5 class="header red-text">choose another email</h5>
</c:if>

<c:set var="baseUrl"
	value="${pageContext.request.contextPath}/registration" />

<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<div class="row">
			<div class="input-field col s12">
				<form:input path="email" type="email" data-error="wrong" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email">email</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="password" type="password" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password">password</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">name</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="phone" type="text" />
				<form:errors path="phone" cssClass="red-text" />
				<label for="phone">phone</label>
			</div>
		</div>

		<div class="row">
 			<div class="col s6">
				<div class="g-recaptcha"
					data-sitekey="6LcjfVoUAAAAANHUeelMXznTDWOMpjVUS6x3sWBg"></div>
			</div>
			<div class="col s6">
				<button class="btn-large waves-effect waves-light center" type="submit">signUp</button>
			</div>
		</div>
	</form:form>
</div>
