<%--
  Created by IntelliJ IDEA.
  User: I_Katrin
  Date: 08.01.2018
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form name=registrationForm method = "POST" action="Controller">
        <input type = "hidden" name = "command" value = "submit_registration"/>
        Login: <input type="text" name="login" onclick="this.value=''"/><br/>
        Password: <input type="password" name="password1"  onclick="this.value=''"/><br/>
        Repeat password: <input type="password" name="password2"  onclick="this.value=''"/><br/>
        First name: <input type="text" name="firstName" onclick="this.value=''"/><br/>
        Middle name: <input type="text" name="middleName" onclick="this.value=''"/><br/>
        Last name: <input type="text" name="lastName" onclick="this.value=''"/><br/>
        E-mail: <input type="text" name="email" onclick="this.value=''"/><br/>
        Phone number: <input type="text" name="phoneNumber" onclick="this.value=''"/><br/>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
