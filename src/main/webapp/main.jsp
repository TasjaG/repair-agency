<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Main</title>
    </head>
    <style>
        li {
            display: inline;
        }
    </style>
    <body>
        <h3>Main</h3>
        Welcome to the Repair Agency website!
        <div align="right" style="float: right; display: inline-block;">
            <c:out value="Hello, ${user}!"/>
        </div>
        <div align="right" style="float: right; display: inline-block;">
            <a href="Controller?command=logout">Logout</a>
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
    </body>
</html>