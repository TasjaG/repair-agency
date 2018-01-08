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
    <form name=registrationForm action="Controller">
        <input type="text" name="login" value="Login..." onclick="this.value=''"/><br/>
        <input type="password" name="password1"  value="Password..." onclick="this.value=''"/><br/>
        <input type="password" name="password2"  value="Repeat password..." onclick="this.value=''"/><br/>
        <input type="text" name="firstName" value="First name..." onclick="this.value=''"/><br/>
        <input type="text" name="middleName" value="Middle name..." onclick="this.value=''"/><br/>
        <input type="text" name="lastName" value="Last name..." onclick="this.value=''"/><br/>
        <input type="text" name="email" value="E-mail..." onclick="this.value=''"/><br/>
        <input type="text" name="phoneNumber" value="Phone number..." onclick="this.value=''"/><br/>

        <input type="submit" value="submit_registration"/>
    </form>
</body>
</html>
