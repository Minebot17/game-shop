<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="css/header.css"/>
<header>
    <script src="js/header.js"></script>
    <a href="/"><img height="100" src="res/logo.png" /></a>
    <a href="/shop"><button type="button" class="btn btn-secondary">Shop</button></a>
    <input type="text" class="form-control search-input" name="search-input" value="${param.search}">
    <button type="button" class="btn btn-secondary search-button" onclick="searchClick()">Search</button>
    <div class="header-right">
        <c:if test="${autificated}">
            <button type="button" class="btn btn-secondary" name="library">My Games</button>
            <button type="button" class="btn btn-secondary" name="profile">${login}</button>
            <button type="button" class="btn btn-secondary" name="logout">Log out</button>
        </c:if>
        <c:if test="${!autificated}">
            <button type="button" class="btn btn-secondary" name="login">Log in</button>
            <button type="button" class="btn btn-secondary" name="register">Register</button>
        </c:if>
    </div>
</header>