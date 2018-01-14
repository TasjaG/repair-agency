<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Main</title>
    </head>
    <body>
        <h3>Main</h3>
        <hr/>
            <c:out value="${user}, Hello!"/>
        <hr/>
        <a href ="Controller">Return to login</a>
        <div>
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <th>UserId</th>
                    <th>Comment</th>
                </tr>

                <c:forEach var="comment" items="${commentList}">
                    <tr>
                        <td>${comment.userId}</td>
                        <td>${comment.commentText}</td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${pageNum != 1}">
                <td><a href="comment.do?page=${currentPage - 1}">Previous</a></td>
            </c:if>

            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${numOfPages}" var="i">
                        <c:choose>
                            <c:when test="${pageNum eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="comment.do?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <c:if test="${pageNum lt numOfPages}">
                <td><a href="comment.do?page=${pageNum + 1}">Next</a></td>
            </c:if>
        </div>
    </body>
</html>