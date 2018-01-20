<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Error</title>
    </head>
    <body>
        <h3>Error</h3>
        <hr/>
            <jsp:expression>(request.getAttribute("error") != null)
                ? (String) request.getAttribute("error") : "unknown error"</jsp:expression>
        <hr/>
        <a href="Controller">Return to login page</a>
    </body>
</html>