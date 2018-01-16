<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accepted Applications</title>
</head>
<body>
    <!-- Make this page accessible only to repairman -->

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
            <th>Price</th>
            <th>Status</th>
            <th>Date Completed</th>
            <th>Complete</th>
        </tr>

        <!--            <form name = "commentForm" method = "POST" action = "Controller">
                        <input type = "hidden" name = "command" value = "submit_comment"/>

            -->
        <c:forEach var="acceptedApp" items="${acceptedAppsList}">
            <tr>
                <td>${acceptedApp.userId}</td>
                <td>${acceptedApp.productName}</td>
                <td>${acceptedApp.productComment}</td>
                <td>${acceptedApp.price}</td>
                <td>${acceptedApp.status}</td>
                <td>${acceptedApp.dateCompleted}</td>
                <!-- Instead a form and input -->
                <td><input type="button" value="Complete"></td>
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
