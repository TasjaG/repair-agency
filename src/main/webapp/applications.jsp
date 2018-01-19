<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applications</title>
    <script type="text/javascript">

        // asks for price in popup window
        function askForPrice(form) {

            if(document.getElementById('password1').value == document.getElementById('password2').value) {

                // TODO localize
                alert('The passwords don\'t match!');
                return false;
            }
            else {

                // TODO localize
                return form.submit();
            }
        }
    </script>
</head>
<body>
    <div align="right" style="float: right; display: inline-block;">
        <hr/>
        <c:out value="Hello, ${user}!"/>
        <hr/>
    </div>

    <!-- to be changed -->
    <div align="right" style="float: right; display: inline-block;">
        <hr/>
        <a href ="Controller?command=logout">Logout</a>
        <hr/>
    </div>
    <div align="center">
        <c:when test="${applicationList != null}">
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
                            <form onsubmit="return askForReason(this);" name = "acceptApplicationForm"
                                  action = "Controller">
                                <input type = "hidden" name = "command" value = "reject_application"/>
                                <input type = "hidden" name = "application_id" value = ${application.id}/>

                                <input type = "hidden" name = "rejection_comment" value = ${null}/>

                                <input type = "submit" value = "Reject">
                            </form>
                            <form onsubmit="return askForPrice(this);" name = "acceptApplicationForm"
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
    </div>
</body>
</html>
