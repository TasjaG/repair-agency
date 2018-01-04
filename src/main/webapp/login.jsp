<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h3>Login</h3>
        <hr/>
        <form name = "loginForm" method = "POST" action = "Controller">
            <input type = "hidden" name = "command" value = "login"/>
            Login: <input type = "text" name = "login" value = ""><br/>
            Password: <input type = "password" name = "password" value=""><br/>
            <input type ="submit" value = "Sing in">
        </form>
        <hr/>
    </body>
</html>
