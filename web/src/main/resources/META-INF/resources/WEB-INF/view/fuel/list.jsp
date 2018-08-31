<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<c:set var="baseUrl" value="${pageContext.request.contextPath}/fuel" />

<h4 class="header">fuels</h4>

<table class="bordered highlight">
    <tbody>
    <tr>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="name">name</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="version">version</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="createdDate">createdDate</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="lastModifiedDate">lastModifiedDate</mytaglib:sort-link></th>
        <th></th>
    </tr>
    <c:forEach var="fuel" items="${listDto.list}" varStatus="loopCounter">
        <tr>
            <td><c:out value="${fuel.id}" /></td>
            <td><c:out value="${fuel.name}" /></td>
            <td><c:out value="${fuel.version}" /></td>
            <td><javatime:format value="${fuel.createdDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>
            <td><javatime:format value="${fuel.lastModifiedDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>
            <td class="right">
                <a class="btn-floating"	href="${baseUrl}/${fuel.id}">
                    <i class="material-icons">info</i>
                </a>
                <a class="btn-floating"	href="${baseUrl}/${fuel.id}/edit">
                    <i class="material-icons">edit</i>
                </a>
                <a class="btn-floating red" href="${baseUrl}/${fuel.id}/delete">
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