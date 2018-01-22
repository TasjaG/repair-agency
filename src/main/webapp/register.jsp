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
                var matchText, doNotMatchText;

                // default is EN
                if (document.getElementById('locale').value == 'UK') {
                    matchText = " співпадають";
                    doNotMatchText = " не співпадають";
                } else {
                    matchText = " matching";
                    doNotMatchText = " not matching";
                }

                if (document.getElementById('password1').value == document.getElementById('password2').value) {
                    document.getElementById('passwordMessage').style.color = 'olivedrab';
                    document.getElementById('passwordMessage').innerHTML = matchText;
                } else {
                    document.getElementById('passwordMessage').style.color = 'crimson';
                    document.getElementById('passwordMessage').innerHTML = doNotMatchText;
                }
            }

            // checks if both passwords match, alerts if not
            function passwordsMatch() {
                var doNotMatchAlert;

                // default is EN
                if (document.getElementById('locale').value == 'UK') {
                    doNotMatchAlert = "Паролі не співпадають!";
                } else {
                    doNotMatchAlert = "The passwords don't match!";
                }

                if(document.getElementById('password1').value == document.getElementById('password2').value) {
                    return true;
                }
                else {
                    alert(doNotMatchAlert);
                    document.getElementById('password1').value = null;
                    document.getElementById('password2').value = null;
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <!-- To be used in JavaScript -->
        <input type="hidden" id="locale" value="${locale}">
        <div><h4>${registerTitle}</h4></div>
        <div align="right" style="display: inline-block">
            <c:choose>
                <c:when test="${locale == 'UK'}">
                    <a href="Controller?command=change_locale&newLocale=EN&currentPage=register.jsp"><label>${localeENLink}</label></a>
                    <label> | </label>
                    <label>${localeUKLink}</label>
                </c:when>
                <c:when test="${locale == 'EN'}">
                    <label>${localeENLink}</label>
                    <label> | </label>
                    <a href="Controller?command=change_locale&newLocale=UK&currentPage=register.jsp"><label>${localeUKLink}</label></a>
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
