<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Comments</title>
    <style>
        li {
            display: inline;
        }
    </style>
</head>
<body>
    <div align="right" style="float: right; display: inline-block;">
        <hr/>
            <c:out value="Hello, ${user}!"/>
        <hr/>
    <div align="right" style="float: right; display: inline-block;">
        <hr/>
            <a href ="Controller?command=logout">Logout</a>
        <hr/>
    </div>
        <ul>
            <li><a href="main.jsp">Main</a></li>
            <li><a href="Controller?command=load_comments">Comments</a></li>

            <c:if test="${user_type == 'user'}">
                <li><a href="leave_request.jsp">Leave request</a></li>
            </c:if>
            <c:if test="${user_type == 'manager'}">
                <li><a href="Controller?command=load_applications">Applications</a></li>
            </c:if>
            <c:if test="${user_type == 'repairman'}">
                <li><a href="Controller?command=load_accepted_apps">Requests</a></li>
            </c:if>
            <!--
                <li><a href="Controller?command=info">About us</a></li>
            -->
        </ul>
    <div align="center">
        <c:choose>
            <c:when test="${commentList != null}">
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
            Enter comment:<br/>
            <textarea rows=4 cols="50" name="comment_text" required></textarea><br/>
            <input type ="submit" value = "Comment">
        </form>
    </div>
</body>
</html>
