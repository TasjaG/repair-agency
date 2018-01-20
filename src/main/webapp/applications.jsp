<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Applications</title>
    <style>
        li {
            display: inline;
        }
    </style>
    <script type="text/javascript">

        // asks for reason for reason for rejection in popup window
        function askForReason(form) {

            // TODO Localize
            if (confirm("Are you sure you want to reject this application?")) {

                // TODO Localize
                var reason = prompt("Please enter the reason for rejecting this application:");

                if (reason == null || person == "") {
                } else {
                    form.setAttribute("rejection_comment", reason);
                    form.submit();
                }
            }
            //else {}
        }

        // asks for price in popup window
        function askForPrice(form) {

            // TODO Localize
            if (confirm("Are you sure you want to accept this application?")) {

                // TODO Localize
                var price = prompt("Please enter approximate price of the repairs:", "0.0");

                if (price == null || person == "") {
                } else {
                    form.setAttribute("price", price);
                    form.submit();
                }
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
                <c:when test="${applicationsList != null}">
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
                        <c:forEach var="application" items="${applicationsList}">
                                <td>${application.userId}</td>
                                <td>${application.productName}</td>
                                <td>${application.productComment}</td>
                                <td>${application.dateAdded}</td>
                                <td>${application.status}</td>
                                <td>${application.comment}</td>
                                <td>${application.dateProcessed}</td>
                                <td>
                                    <form onsubmit="askForReason(this);" name = "rejectApplicationForm"
                                          action = "Controller">
                                        <input type = "hidden" name = "command" value = "reject_application"/>
                                        <input type = "hidden" name = "application_id" value = ${application.id}/>
                                        <input type = "hidden" name = "rejection_comment" value = ${null}/>
                                        <input type = "submit" value = "Reject">
                                    </form>
                                </td>
                                <td>
                                    <form onsubmit="askForPrice(this);" name = "acceptApplicationForm"
                                          action = "Controller">
                                        <input type = "hidden" name = "command" value = "accept_application"/>
                                        <input type = "hidden" name = "application_id" value = ${application.id}/>
                                        <input type = "hidden" name = "price" value = ${null}/>
                                        <input type ="submit" value = "Accept">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${pageNum != 1}">
                        <td><a href="Controller?command=load_applications&pageNum=${pageNum - 1}"><<</a></td>
                    </c:if>
                    <table border="1" cellpadding="5" cellspacing="5">
                       <tr>
                            <c:forEach begin="1" end="${numOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${pageNum eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="Controller?command=load_applications&pageNum=${i}">${i}</a></td>
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
                    No applications found!
                </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
