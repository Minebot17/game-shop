<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
    <form method="post" class="login-form">
        <div class="mb-3">
            <label for="login" class="form-label">Login</label>
            <input type="text" class="form-control" id="login" name="login" value="<sec:authentication property="principal.username" />">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="<sec:authentication property="principal.email" />">
            <c:if test="${emailConfirmed}">
                <div id="emailHelp" class="form-text" style="color: green !important">Email confirmed</div>
            </c:if>
            <c:if test="${!emailConfirmed}">
                <div id="emailHelp" class="form-text" style="color: red !important">Email not confirmed</div>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Old password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="mb-3">
            <label for="newPassword" class="form-label">New password</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword">
        </div>
        <button type="submit" class="btn btn-secondary">Save changes</button>
        <c:if test="${requestScope.containsKey('errorMessage')}">
            <p class="alert-login">${errorMessage}</p>
        </c:if>
    </form>
</div>
</body>
</html>