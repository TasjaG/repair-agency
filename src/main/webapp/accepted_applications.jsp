<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>${requestsTitle}</title>
    <style>
        li {
            display: inline;
        }
    </style>
    <script type="text/javascript">

        // asks for reason for reason for rejection in popup window
        function confirmCompletion() {
            var confirmationText;

            if (document.getElementById('locale').value == 'UK') {
                confirmationText = "Виконати це замовлення?";
            } else {
                confirmationText = "Complete this order?";
            }

            if (confirm(confirmationText)) {
                return true;
            }
            return false;
        }
    </script>
</head>
<body>
    <!-- To be used in JavaScript -->
    <input type="hidden" id="locale" value="${locale}">
    <!--
    <input type="hidden" name="currentPage" value="/Controller?command=load_accepted_apps">
    -->
    <div><h4>${requestsTitle}</h4></div>
    <div align="right" style="display: inline-block">
        <c:choose>
            <c:when test="${locale == 'UK'}">
                <a href="Controller?command=change_locale&newLocale=EN&currentPage=Controller?command=load_accepted_apps"><label>${localeENLink}</label></a>
                <label> | </label>
                <label>${localeUKLink}</label>
            </c:when>
            <c:when test="${locale == 'EN'}">
                <label>${localeENLink}</label>
                <label> | </label>
                <a href="Controller?command=change_locale&newLocale=UK&currentPage=Controller?command=load_accepted_apps"><label>${localeUKLink}</label></a>
            </c:when>

            <c:otherwise>
                No locale specified!
            </c:otherwise>
        </c:choose>
    </div>
    <div align="right" style="float: right; display: inline-block;">
        <c:out value="${helloUserLabel}, ${user}!"/>
        <label>  </label>
    </div>
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
            <c:when test="${acceptedAppsList != null}">
                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <th>${userIdTableHeaderAcceptedApps}</th>
                        <th>${productNameTableHeaderAcceptedApps}</th>
                        <th>${userCommentTableHeaderAcceptedApps}User Comment</th>
                        <th>${priceTableHeader}</th>
                        <th>${statusTableHeaderAcceptedApps}</th>
                        <th>${dateCompletedTableHeader}</th>
                        <th>${completeTableHeader}</th>
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
                                <c:if test="${acceptedApp.status == 'waiting'}">
                                    <form onsubmit="return confirmCompletion();" method = "POST"
                                          name = "completeOrderForm" action = "Controller">
                                        <input type = "hidden" name = "command" value = "complete_order"/>
                                        <input type = "hidden" name = "accepted_app_id" value = "${acceptedApp.id}"/>
                                        <input type = "submit" value = "${completeButton}">
                                    </form>
                                </c:if>
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
