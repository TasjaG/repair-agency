<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>${leaveRequestTitle}</title>
        <style>
            .required {
                color: crimson;
            }
        </style>
    </head>
    <body>
        <div><h4>${leaveRequestTitle}</h4></div>
        <div align="right" style="display: inline-block">
            <c:choose>
                <c:when test="${locale == 'UK'}">
                    <input type="hidden" name="newLocale" value="EN">
                    <a href="Controller?command=change_locale"><label>${localeENLink}</label></a>
                    <label> | </label>
                    <label>${localeUKLink}</label>
                </c:when>
                <c:when test="${locale == 'EN'}">
                    <input type="hidden" name="newLocale" value="UK">
                    <label>${localeENLink}</label>
                    <label> | </label>
                    <a href="Controller?command=change_locale"><label>${localeUKLink}</label></a>
                </c:when>
                <c:otherwise>
                    No locale specified!
                </c:otherwise>
            </c:choose>
        </div>
        <div align="right" style="float: right; display: inline-block;">
            <c:out value="${helloUserLabel}, ${user}!"/>
        </div>
        <div align="right" style="float: right; display: inline-block;">
            <a href ="Controller?command=logout">${logoutLink}</a>
        </div>
        <div align="center" class="navbar">
            <ul>
                <li><a href="main.jsp">${mainLink}</a></li>
                <li><a href="Controller?command=load_comments">${commentsLink}</a></li>
                <c:if test="${user_type == 'user'}">
                    <li><a href="leave_request.jsp">${leaveRequestLink}</a></li>
                </c:if>
                <c:if test="${user_type == 'manager'}">
                    <li><a href="Controller?command=load_applications">${applicationsLink}</a></li>
                </c:if>
                <c:if test="${user_type == 'repairman'}">
                    <li><a href="Controller?command=load_accepted_apps">${requestsLink}</a></li>
                </c:if>
                <!--
                    <li><a href="Controller?command=info">About us</a></li>
                -->
            </ul>
        </div>
        <div align="center">
            <form name=registrationForm method = "POST" action="Controller">
                <input type = "hidden" name = "command" value = "submit_application"/>
                <label>${productNameLabel}</label><span class="required">*</span>
                <input type="text" name="product_name" required/><br/>
                <label>${additionalInfoLabel}</label><textarea rows=4 cols="40" name="product_comment"></textarea><br/>
                <input type="submit" value="${submitButtonLeaveRequest}"/>
            </form>
        </div>
    </body>
</html>
