<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
        <style>
            .required {
                color: crimson;
            }
        </style>
        <script type="text/javascript">
            function checkPassword() {
                if (document.getElementById('password1').value == document.getElementById('password2').value) {
                    document.getElementById('passwordMessage').style.color = 'olivedrab';

                    // TODO localize
                    document.getElementById('passwordMessage').innerHTML = 'matching';
                } else {
                    document.getElementById('passwordMessage').style.color = 'crimson';

                    // TODO localize
                    document.getElementById('passwordMessage').innerHTML = 'not matching';
                }
            }

            // checks if both passwords match, alerts if not
            function validateForm(form) {
                if(document.getElementById('password1').value == document.getElementById('password2').value) {

                    // TODO localize
                    alert('The passwords don\'t match!');
                    return false;
                }
                else {

                    // TODO localize
                    return form.submit();
                }
            }
        </script>
    </head>
    <body>
        <form onsubmit="return validateForm(this);" name=registrationForm method = "POST" action="Controller">
            <input type = "hidden" name = "command" value = "submit_registration"/>
            Login<span class="required">*</span> <input type="text" name="login" required/><br/>
            Password<span class="required">*</span> <input type="password" name="password1" id="password"
                                                           onkeyup="checkPassword()" required/><br/>
            Repeat password<span class="required">*</span> <input type="password" name="password2" id="password2"
                                                                  onkeyup="checkPassword()" required/><br/>
            <span id="passwordMessage"></span>
            First name<span class="required">*</span> <input type="text" name="firstName" required/><br/>
            Middle name <input type="text" name="middleName"/><br/>
            Last name<span class="required">*</span> <input type="text" name="lastName" required/><br/>
            E-mail<span class="required">*</span> <input type="text" name="email" required/><br/>
            Phone number <input type="text" name="phoneNumber"/><br/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
