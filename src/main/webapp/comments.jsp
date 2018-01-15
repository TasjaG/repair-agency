<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments</title>
</head>
<>

<div align="right" style="float: right; display: inline-block;">
    <hr/>
    <c:out value="Hello, ${user}!"/>
    <hr/>
</div>


<!-- to be removed -->
<a href ="Controller">Return to login</a>
    <div align="center">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>UserId</th>
                <th>Comment</th>
            </tr>
            <c:forEach var="comment" items="${commentList}">
                <tr>
                    <td>${comment.userId}</td>
                    <td>${comment.text}</td>
                </tr>
            </c:forEach>
        </table>

<!--
        <c:if test="${pageNum != 1}">
            <td><a href="comments.jsp?pageNum=${pageNum - 1}"><<</a></td>
        </c:if>

        <table border="1" cellpadding="5" cellspacing="5">
           <tr>
                <c:forEach begin="1" end="${numOfPages}" var="i">
                    <c:choose>
                        <c:when test="${pageNum eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="comments.jsp?pageNum=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
              </c:forEach>
            </tr>
        </table>
       <c:if test="${pageNum lt numOfPages}">
            <td><a href="comments.jsp?pageNum=${pageNum + 1}">>></a></td>
        </c:if>
-->
    </div>

    <div>
        <form name = "commentForm" method = "POST" action = "Controller">
            <input type = "hidden" name = "command" value = "submit_comment"/>
            Enter comment:<br/>
            <textarea rows=4 cols="50" name="comment_text">Enter comment...</textarea><br/>
            <input type ="submit" value = "Comment">
        </form>
    </div>

</body>
</html>
