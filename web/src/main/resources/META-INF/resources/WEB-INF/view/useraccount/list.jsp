<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<c:set var="baseUrl" value="${pageContext.request.contextPath}/useraccount" />

<h4 class="header">userAccounts</h4>

<table class="bordered highlight">
    <tbody>
    <tr>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="name">name</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="email">email</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="password">password</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="photoLink">photoLink</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="phone">phone</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="role">role</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="verifyKey">verifyKey</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="verified">verified</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="passportNumber">passportNumber</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="drivingLicenseNumber">drivingLicenseNumber</mytaglib:sort-link></th>
        <%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="version">version</mytaglib:sort-link></th>--%>
        <%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="createdDate">createdDate</mytaglib:sort-link></th>--%>
        <%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="lastModifiedDate">lastModifiedDate</mytaglib:sort-link></th>--%>
        <th></th>
    </tr>
    <c:forEach var="userAccount" items="${listDto.list}" varStatus="loopCounter">
        <tr>
            <td><c:out value="${userAccount.id}" /></td>
            <td><c:out value="${userAccount.name}" /></td>
            <td><c:out value="${userAccount.email}" /></td>
            <td><c:out value="${userAccount.password}" /></td>
            <td><c:out value="${userAccount.photoLink}" /></td>
            <td><c:out value="${userAccount.phone}" /></td>
            <td><c:out value="${userAccount.role}" /></td>
            <td><c:out value="${userAccount.passportNumber}" /></td>
            <td><c:out value="${userAccount.drivingLicenseNumber}" /></td>
            <td><c:out value="${userAccount.verifyKey}" /></td>
            <td><c:out value="${userAccount.verified}" /></td>
            <%--<td><c:out value="${userAccount.version}" /></td>--%>
            <%--<td><javatime:format value="${userAccount.createdDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>--%>
            <%--<td><javatime:format value="${userAccount.lastModifiedDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>--%>
            <td class="right">
                <a class="btn-floating"	href="${baseUrl}/${userAccount.id}">
                    <i class="material-icons">info</i>
                </a>
                <a class="btn-floating"	href="${baseUrl}/${userAccount.id}/edit">
                    <i class="material-icons">edit</i>
                </a>
                <a class="btn-floating red" href="${baseUrl}/${userAccount.id}/delete">
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