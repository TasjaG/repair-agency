<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <style>
            .required {
                color: crimson;
            }
        </style>
        <form name=registrationForm method = "POST" action="Controller">
            <input type = "hidden" name = "command" value = "submit_registration"/>
            Login<span class="required">*</span> <input type="text" name="login" required/><br/>
            Password<span class="required">*</span> <input type="password" name="password1" required/><br/>
            Repeat password<span class="required">*</span> <input type="password" name="password2" required/><br/>
            First name<span class="required">*</span> <input type="text" name="firstName" required/><br/>
            Middle name <input type="text" name="middleName"/><br/>
            Last name<span class="required">*</span> <input type="text" name="lastName" required/><br/>
            E-mail<span class="required">*</span> <input type="text" name="email" required/><br/>
            Phone number <input type="text" name="phoneNumber"/><br/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
