<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${loginTitle}</title>
        <style>
            .required {
                color: crimson;
            }
        </style>
    </head>
    <body>
        <div><h4>${loginTitle}</h4></div>
        <div align="right" style="display: inline-block">
            <c:choose>
                <c:when test="${locale == 'UK'}">
                    <a href="Controller?command=change_locale&newLocale=EN&currentPage=login.jsp"><label>${localeENLink}</label></a>
                    <label> | </label>
                    <label>${localeUKLink}</label>
                </c:when>
                <c:when test="${locale == 'EN'}">
                    <label>${localeENLink}</label>
                    <label> | </label>
                    <a href="Controller?command=change_locale&newLocale=UK&currentPage=login.jsp"><label>${localeUKLink}</label></a>
                </c:when>
                <c:otherwise>
                    No locale specified!
                </c:otherwise>
            </c:choose>
        </div>
        <div align="center">
            <form name = "loginForm" method = "POST" action = "Controller">
                <input type = "hidden" name = "command" value = "login"/>
                <label>${loginLabelLogin}</label><span class="required">*</span> <input type = "text" name = "login" required><br/>
                <label>${passwordLabelLogin}</label><span class="required">*</span> <input type = "password" name = "password" required><br/>
                <input type ="submit" value = "${loginButton}">
            </form>
            <form name = "registerForm" action = "Controller">
                <input type = "hidden" name = "command" value = "register"/>
                <input type ="submit" value = "${registerButton}">
            </form>
        </div>
    </body>
</html>
