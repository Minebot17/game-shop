<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>game-shop</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <jsp:include page="header.jsp" />
    <div class="main-panel">
        <div class="login-form" style="width: 700px !important">
            Hey! This is my site, which I created when I was learning the Java backend technology stack.
            Here such technologies were used as:
            <ul>
                <li>Spring boot - as the main framework</li>
                <li>Spring MVC - for routing and processing requests</li>
                <li>JSP + JSTL - for server-side rendering of templates</li>
                <li>Hibernate - for working with the database, and the database itself was postgreSQL</li>
                <li>Spring security - for registration and authorization, and generally work with the user</li>
                <li>Java email sender - to send a letter to the mail with the purpose of its confirmation</li>
                <li>Bootstrap and jQuery were used on the frontend</li>
            </ul>

            <a href="https://www.youtube.com/watch?v=LEcAC3EnhPc">Here is a video demonstrating how the site works</a>
        </div>
    </div>
</body>
</html>