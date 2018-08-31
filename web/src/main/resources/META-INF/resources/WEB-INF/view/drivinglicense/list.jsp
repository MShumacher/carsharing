<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<c:set var="baseUrl" value="${pageContext.request.contextPath}/drivinglicense" />

<h4 class="header">drivingLicenses</h4>

<table class="bordered highlight">
    <tbody>
    <tr>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="number">number</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="expirationDate">expirationDate</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="categories">categories</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="userAccountEmail">userAccountEmail</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="version">version</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="createdDate">createdDate</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="lastModifiedDate">lastModifiedDate</mytaglib:sort-link></th>
        <th></th>
    </tr>
    <c:forEach var="drivingLicense" items="${listDto.list}" varStatus="loopCounter">
        <tr>
            <td><c:out value="${drivingLicense.id}" /></td>
            <td><c:out value="${drivingLicense.number}" /></td>
            <td><fmt:formatDate value="${drivingLicense.expirationDate}" pattern="dd.MM.yyyy" /></td>
            <td><c:out value="${drivingLicense.categories}" /></td>
            <td><c:out value="${drivingLicense.userAccountEmail}" /></td>
            <td><c:out value="${drivingLicense.version}" /></td>
            <td><javatime:format value="${drivingLicense.createdDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>
            <td><javatime:format value="${drivingLicense.lastModifiedDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>
            <td class="right">
                <a class="btn-floating"	href="${baseUrl}/${drivingLicense.id}">
                    <i class="material-icons">info</i>
                </a>
                <a class="btn-floating"	href="${baseUrl}/${drivingLicense.id}/edit">
                    <i class="material-icons">edit</i>
                </a>
                <a class="btn-floating red" href="${baseUrl}/${drivingLicense.id}/delete">
                    <i class="material-icons">delete</i>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add">
    <i class="material-icons">add</i>
</a>