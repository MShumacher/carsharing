<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<c:set var="baseUrl" value="${pageContext.request.contextPath}/enginetype" />

<h4 class="header">engineTypes</h4>

<table class="bordered highlight">
    <tbody>
    <tr>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="name">name</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="fuel">fuel</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="version">version</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="createdDate">createdDate</mytaglib:sort-link></th>
        <th><mytaglib:sort-link pageUrl="${baseUrl}" column="lastModifiedDate">lastModifiedDate</mytaglib:sort-link></th>
        <th></th>
    </tr>
    <c:forEach var="engineType" items="${listDto.list}" varStatus="loopCounter">
        <tr>
            <td><c:out value="${engineType.id}" /></td>
            <td><c:out value="${engineType.name}" /></td>
            <td><c:out value="${engineType.fuelName}" /></td>
            <td><c:out value="${engineType.version}" /></td>
            <td><javatime:format value="${engineType.createdDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>
            <td><javatime:format value="${engineType.lastModifiedDate}" pattern="dd.MM.yyyy / HH:mm:ss" /></td>
            <td class="right">
                <a class="btn-floating"	href="${baseUrl}/${engineType.id}">
                    <i class="material-icons">info</i>
                </a>
                <a class="btn-floating"	href="${baseUrl}/${engineType.id}/edit">
                    <i class="material-icons">edit</i>
                </a>
                <a class="btn-floating red" href="${baseUrl}/${engineType.id}/delete">
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