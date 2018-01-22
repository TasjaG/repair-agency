<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Leave Request</title>
        <style>
            .required {
                color: crimson;
            }
        </style>
    </head>
    <body>
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
        <div align="center">
            <form name=registrationForm method = "POST" action="Controller">
                <input type = "hidden" name = "command" value = "submit_application"/>
                Name of product that needs repairing<span class="required">*</span>
                <input type="text" name="product_name" required/><br/>
                Additional information<textarea rows=4 cols="40" name="product_comment"></textarea><br/>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </body>
</html>
