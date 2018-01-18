<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <style>
            .required {
                color: crimson;
            }
        </style>
        <h3>Login</h3>
            <hr/>
            <div align="center">
                <form name = "loginForm" method = "POST" action = "Controller">
                    <input type = "hidden" name = "command" value = "login"/>
                    Login<span class="required">*</span> <input type = "text" name = "login" required><br/>
                    Password<span class="required">*</span> <input type = "password" name = "password" required><br/>
                    <input type ="submit" value = "Login">
                </form>
                <form name = "registerForm" action = "Controller">
                    <input type = "hidden" name = "command" value = "register"/>
                    <input type ="submit" value = "Register">
                </form>
            </div>
            <hr/>
    </body>
</html>
