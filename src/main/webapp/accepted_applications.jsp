<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Accepted Applications</title>
    <style>
        li {
            display: inline;
        }
    </style>
    <script type="text/javascript">

        // asks for reason for reason for rejection in popup window
        function confirmCompletion(form) {

            // TODO Localize
            if (confirm("Complete this order?")) {
                    form.submit();
            }
            //else {}
        }
    </script>
</head>
<body>
    <div align="right" style="float: right; display: inline-block;">
        <hr/>
        <c:out value="Hello, ${user}!"/>
        <hr/>
    </div>
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
            <c:when test="${acceptedAppsList != null}">
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
                    <c:forEach var="acceptedApp" items="${acceptedAppsList}">
                        <tr>
                            <td>${acceptedApp.userId}</td>
                            <td>${acceptedApp.productName}</td>
                            <td>${acceptedApp.productComment}</td>
                            <td>${acceptedApp.price}</td>
                            <td>${acceptedApp.status}</td>
                            <td>${acceptedApp.dateCompleted}</td>
                            <td>
                                <form onsubmit="confirmCompletion(this);" name = "completeOrderForm"
                                      action = "Controller">
                                    <input type = "hidden" name = "command" value = "complete_order"/>
                                    <input type = "hidden" name = "accepted_app_id" value = ${acceptedApp.id}/>
                                    <input type = "submit" value = "Complete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${pageNum != 1}">
                    <td><a href="Controller?command=load_accepted_apps&pageNum=${pageNum - 1}"><<</a></td>
                </c:if>
                <table border="1" cellpadding="5" cellspacing="5">
                   <tr>
                        <c:forEach begin="1" end="${numOfPages}" var="i">
                            <c:choose>
                                <c:when test="${pageNum eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="Controller?command=load_accepted_apps&pageNum=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                      </c:forEach>
                    </tr>
                </table>
                <c:if test="${pageNum lt numOfPages}">
                    <td><a href="Controller?command=load_accepted_apps&pageNum=${pageNum + 1}">>></a></td>
                </c:if>
            </c:when>
            <c:otherwise>
                No orders found!
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
