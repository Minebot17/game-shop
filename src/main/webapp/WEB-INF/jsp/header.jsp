<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="css/header.css"/>
<header>
    <img height="100" src="res/logo.png" />
    <button type="button" class="btn btn-secondary">Shop</button>
    <form class="search-form">
        <input type="text" class="form-control" name="search-input">
        <button type="button" class="btn btn-secondary" name="search">Search</button>
    </form>
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