<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" type="text/css" href="css/header.css"/>
<header>
    <script src="js/header.js"></script>
    <a href="/"><img height="100" src="res/logo.png" /></a>
    <a href="/shop"><button type="button" class="btn btn-secondary">Shop</button></a>
    <input type="text" class="form-control search-input" name="search-input" value="${param.search}">
    <button type="button" class="btn btn-secondary search-button" onclick="searchClick()">Search</button>
    <div class="header-right">
        <sec:authorize access="isAuthenticated()">
            <button type="button" class="btn btn-secondary" name="library">My Games</button>
            <a href="/profile"><button type="button" class="btn btn-secondary" name="profile">
                <sec:authentication property="principal.username" /> Profile
            </button></a>
            <a href="/logout"><button type="button" class="btn btn-secondary" name="logout">Log out</button></a>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <a href="/login"><button type="button" class="btn btn-secondary" name="login">Log in</button></a>
            <a href="/register"><button type="button" class="btn btn-secondary" name="register">Register</button></a>
        </sec:authorize>
    </div>
</header>