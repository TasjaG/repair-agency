<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${errorTitle}</title>
    </head>
    <body>
    <div><h4>${errorTitle}</h4></div>
    <hr/>
            <jsp:expression>(request.getAttribute("error") != null)
                ? (String) request.getAttribute("error") : "unknown error"</jsp:expression>
    <hr/>
    <a href="Controller"><--${Login}</a>
    </body>
</html>