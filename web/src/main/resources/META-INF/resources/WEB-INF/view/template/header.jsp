<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<c:set var="baseUrl" value="${pageContext.request.contextPath}"/>

<header>
    <nav>
        <div class="nav-wrapper container">

            <ul id="nav-mobile" class="left hide-on-med-and-down">
                <sec:authorize access="!isAnonymous()">
                    <ul id="dropdown1" class="dropdown-content">
                        <li><a href="${baseUrl}/brand">Brands</a></li>
                        <li><a href="${baseUrl}/model">Models</a></li>
                        <li><a href="${baseUrl}/gearbox">Gearboxes</a></li>
                        <li><a href="${baseUrl}/bodytype">Body types</a></li>
                        <li><a href="${baseUrl}/drive">Drives</a></li>
                        <li><a href="${baseUrl}/fuel">Fuels</a></li>
                        <li><a href="${baseUrl}/enginetype">Engine types</a></li>
                        <li><a href="${baseUrl}/carparameter">Car parameters</a></li>
                        <li><a href="${baseUrl}/useraccount">User accounts</a></li>
                        <li><a href="${baseUrl}/passport">Passports</a></li>
                        <li><a href="${baseUrl}/drivinglicense">Driving licenses</a></li>
                        <li><a href="${baseUrl}/message">Messages</a></li>
                        <li><a href="${baseUrl}/calendar">Calendar</a></li>
                        <li><a href="${baseUrl}/carsphoto">Cars photos</a></li>
                        <li><a href="${baseUrl}/car">Cars</a></li>
                        <li><a href="${baseUrl}/ad">Ads</a></li>
                        <li class="divider"></li>
                    </ul>
                    <ul class="left">
                        <li><a class="dropdown-trigger" href="#!" data-target="dropdown1">dbMenu<i
                                class="material-icons right">arrow_drop_down</i></a></li>
                    </ul>
                </sec:authorize>
                <li><a href="${baseUrl}/">Home</a></li>
                <sec:authorize access="isAnonymous()">
                    <li><a href="${baseUrl}/registration">Registration</a></li>
                    <li><a href="${baseUrl}/login">Log in</a></li>
                </sec:authorize>

                <li><a href="${baseUrl}?language=ru">RU</a></li>
                <li><a href="${baseUrl}?language=en">EN</a></li>
                <sec:authorize access="!isAnonymous()">
                    <ul id="dropdown2" class="dropdown-content">
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href="${baseUrl}/ad/myads">my ads</a></li>
                            <li><a href="${baseUrl}/car/mycars">my cars</a></li>
                            <li class="divider"></li>
                            <li><a href="${baseUrl}/myprofile">editProfile</a></li>
                        </sec:authorize>
                        <li class="divider"></li>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                            <li><a href="${baseUrl}/upload">file uploading form</a></li>
                        </sec:authorize>
                        <li class="divider"></li>
                        <li>
                        <li><a href="${baseUrl}/execute_logout">logOut</a></li>
                        </li>
                    </ul>
                    <ul class="right">
                        <!-- Dropdown Trigger -->
                        <li><a class="dropdown-trigger" href="#!" data-target="dropdown2">
                            <sec:authentication property="principal"/>
                            <sec:authentication property="authorities"/>
                            <i class="material-icons right">arrow_drop_down</i>
                        </a></li>
                    </ul>
                </sec:authorize>
            </ul>
            <div class="col s3">
                <div class="right">
                    <span id="clock"></span>
                </div>
            </div>
        </div>
    </nav>


    <script type="text/javascript">
        obj_hours = document.getElementById("clock");
        name_month = new Array("января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября",
            "декабря");
        name_day = new Array("ВС", "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ");

        function wr_hours() {
            time = new Date();
            time_sec = time.getSeconds();
            time_min = time.getMinutes();
            time_hours = time.getHours();
            time_wr = ((time_hours < 10) ? "0" : "") + time_hours;
            time_wr += ":";
            time_wr += ((time_min < 10) ? "0" : "") + time_min;
            time_wr += ":";
            time_wr += ((time_sec < 10) ? "0" : "") + time_sec;
            time_wr = name_day[time.getDay()] + ", " + time.getDate() + " "
                + name_month[time.getMonth()] + " " + time.getFullYear() + ", "
                + time_wr;
            obj_hours.innerHTML = time_wr;
        }

        wr_hours();
        setInterval("wr_hours();", 1000);
    </script>
</header>