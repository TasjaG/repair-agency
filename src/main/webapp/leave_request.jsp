<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Leave Request</title>
        <style>
            .required {
                color: crimson;
            }
        </style>
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
        <div align="center">
            <form name=registrationForm method = "POST" action="Controller">
                <input type = "hidden" name = "command" value = "submit_application"/>
                Name of product that needs repairing<span class="required">*</span>
                <input type="text" name="product_name" required/><br/>
                Additional information<textarea rows=4 cols="40" name="product_comment"></textarea><br/>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </body>
</html>
