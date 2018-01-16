<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Main</title>
    </head>
    <body>
        <h3>Main</h3>

        Welcome to the Repair Agency website!

        <div align="right" style="float: right; display: inline-block;">
            <hr/>
                <c:out value="Hello, ${user}!"/>
            <hr/>
        </div>
        <div align="right" style="float: right; display: inline-block;">
            <hr/>
                <input type="button" value="Logout">
            <hr/>
        </div>

        <ul>
            <li><a href="main.jsp">Main</a></li>
            <!-- there has to be a better way to do this -->
            <li><a href="Controller?command=load_comments&user=${user}">Comments</a></li>
            <li><a href="leave_request.jsp?user=${user}">Leave request</a></li>

            <!-- filter these, so only manager/repairman can see them -->
            <li><a href="Controller?command=load_applications&user=${user}">Applications</a></li>
            <li><a href="Controller?command=load_accepted_apps&user=${user}">Requests</a></li>
            <!--
            <c:if test="${userType != 1}">
                <td><input type="submit" value="<<"></td>
                </c:if>
            <li><a href="Controller?command=load_comments">Comments</a></li>
            <li><a href="Controller?command=load_comments">Comments</a></li>
            <li><a href="Controller?command=info">About us</a></li>

            -->
        </ul>

    </body>
</html>