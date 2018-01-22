<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>${registerTitle}</title>
        <style>
            .required {
                color: crimson;
            }
        </style>
        <script type="text/javascript">
            function dynamicallyCheckPassword() {
                if (document.getElementById('password1').value == document.getElementById('password2').value) {
                    document.getElementById('passwordMessage').style.color = 'olivedrab';

                    // TODO localize
                    document.getElementById('passwordMessage').innerHTML = ' matching';
                } else {
                    document.getElementById('passwordMessage').style.color = 'crimson';

                    // TODO localize
                    document.getElementById('passwordMessage').innerHTML = ' not matching';
                }
            }

            // checks if both passwords match, alerts if not
            function passwordsMatch() {
                if(document.getElementById('password1').value == document.getElementById('password2').value) {

                    // TODO localize
                    return true;
                }
                else {
                    // TODO localize
                    alert('The passwords don\'t match!');
                    document.getElementById('password1').value = null;
                    document.getElementById('password2').value = null;
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <!-- To be used in JavaScript -->
        <input type="hidden" name="locale" value="${locale}">
        <div><h4>${registerTitle}</h4></div>
        <div align="right" style="display: inline-block">
            <c:choose>
                <c:when test="${locale == 'UK'}">
                    <input type="hidden" name="newLocale" value="EN">
                    <a href="Controller?command=change_locale"><label>${localeENLink}</label></a>
                    <label> | </label>
                    <label>${localeUKLink}</label>
                </c:when>
                <c:when test="${locale == 'EN'}">
                    <input type="hidden" name="newLocale" value="UK">
                    <label>${localeENLink}</label>
                    <label> | </label>
                    <a href="Controller?command=change_locale"><label>${localeUKLink}</label></a>
                </c:when>
                <c:otherwise>
                    No locale specified!
                </c:otherwise>
            </c:choose>
        </div>
        <div align="center">
            <form onsubmit="return passwordsMatch();" name=registrationForm method = "POST" action="Controller">
                <input type = "hidden" name = "command" value = "submit_registration"/>
                <label>${loginLabelRegister}</label><span class="required">*</span> <input type="text" name="login" required/><br/>
                <label>${passwordLabelRegister}</label><span class="required">*</span> <input type="password" name="password1" id="password1" onkeyup="dynamicallyCheckPassword();" required/><br/>
                <label>${repeatPasswordLabel}</label><span class="required">*</span> <input type="password" name="password2" id="password2" onkeyup="dynamicallyCheckPassword();" required/>
                <span id="passwordMessage"></span><br/>
                <label>${firstNameLabel}</label><span class="required">*</span> <input type="text" name="firstName" required/><br/>
                <label>${middleNameLabel}</label> <input type="text" name="middleName"/><br/>
                <label>${lastNameLabel}</label><span class="required">*</span> <input type="text" name="lastName" required/><br/>
                <label>${emailLabel}</label><span class="required">*</span> <input type="text" name="email" required/><br/>
                <label>${phoneNumberLabel}</label> <input type="text" name="phoneNumber"/><br/>
                <input type="submit" value="${submitButtonRegister}"/>
            </form>
        </div>
    </body>
</html>
