<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Main</title>
    </head>
    <body>
        <h3>Main</h3>
        <hr/>
            <c:out value="${user}, Hello!"/>
        <hr/>
        <a href ="Controller">Return to login</a>
        <jsp:useBean id="mybean" scope="session" class="com.bionic.login.bean.MyBean"/>
        Bean: <jsp:getProperty name="mybean" property="field"/>
    </body>
</html>