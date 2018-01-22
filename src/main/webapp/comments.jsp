<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>${commentsTitle}</title>
        <style>
            li {
                display: inline;
            }
        </style>
    </head>
    <body>
        <div><h4>${commentsTitle}</h4></div>
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
            <c:choose>
                <c:when test="${commentsList != null}">
                    <table border="1" cellpadding="5" cellspacing="5">
                        <tr>
                            <th>${userIdTableHeaderComments}</th>
                            <th>${commentTableHeader}</th>
                        </tr>
                        <c:forEach var="comment" items="${commentsList}">
                            <tr>
                                <td>${comment.userId}</td>
                                <td>${comment.text}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${pageNum != 1}">
                        <td><a href="Controller?command=load_comments&pageNum=${pageNum - 1}"><<</a></td>
                    </c:if>
                    <table border="1" cellpadding="5" cellspacing="5">
                       <tr>
                            <c:forEach begin="1" end="${numOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${pageNum eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="Controller?command=load_comments&pageNum=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                          </c:forEach>
                       </tr>
                    </table>
                   <c:if test="${pageNum lt numOfPages}">
                       <td><a href="Controller?command=load_comments&pageNum=${pageNum + 1}">>></a></td>
                   </c:if>
                </c:when>
                <c:otherwise>
                    No comments found!
                </c:otherwise>
            </c:choose>
        </div>
        <div align="center">
            <form name = "commentForm" method = "POST" action = "Controller">
                <input type = "hidden" name = "command" value = "submit_comment"/>
                <lable>${enterCommentLabel}</lable>:<br/>
                <textarea rows=4 cols="50" name="comment_text" required></textarea><br/>
                <input type ="submit" value = "${commentButton}">
            </form>
        </div>
    </body>
</html>
