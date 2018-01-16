<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leave Request</title>
</head>
    <body>
    <div align="right" style="float: right; display: inline-block;">
        <hr/>
        <c:out value="Hello, ${user}!"/>
        <hr/>
    </div>

    <div align="center">
        <form name=registrationForm method = "POST" action="Controller">
            <input type = "hidden" name = "command" value = "submit_application"/>
            Name of product that needs repairing: <input type="text" name="product_name" onclick="this.value=''"/><br/>
            Additional information: <input type="text" name="product_comment"  onclick="this.value=''"/><br/>

            <input type="submit" value="Submit"/>
        </form>
    </div>
</body>
</html>
