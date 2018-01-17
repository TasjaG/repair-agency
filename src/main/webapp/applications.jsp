<%--
  Created by IntelliJ IDEA.
  User: I_Katrin
  Date: 16.01.2018
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applications</title>
</head>
<body>
    <!-- Make this page accessible only to manager -->

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
                <th>Product</th>
                <th>User Comment</th>
                <th>Date Added</th>
                <th>Status</th>
                <th>Reason rejected</th>
                <th>Date Processed</th>
                <!-- Conditional -->
                <th>Reject</th>
                <th>Accept</th>
            </tr>

<!--            <form name = "commentForm" method = "POST" action = "Controller">
                <input type = "hidden" name = "command" value = "submit_application"/>

    -->
            <c:forEach var="application" items="${applicationsList}">
                <tr>
                    <td>${application.userId}</td>
                    <td>${application.productName}</td>
                    <td>${application.productComment}</td>
                    <td>${application.dateAdded}</td>
                    <td>${application.status}</td>
                    <td>${application.comment}</td>
                    <td>${application.dateProcessed}</td>
                    <!-- Instead a form and input -->
                    <td><input type="button" value="Reject"></td>
                    <td><input type="button" value="Submit"></td>
                </tr>
            </c:forEach>

       <!--     </form> -->

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
</body>
</html>
