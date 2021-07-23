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
        <div class="login-form" style="font-size: 24pt">Shop</div><br/>
        <c:forEach var="game" items="${games}">
            <c:if test="${!param.containsKey('search') || game.title.toLowerCase().startsWith(param.search.toLowerCase())}">
                <c:set var="game" value="${game}" scope="request" />
                <c:set var="bought" value="${bought_games.contains(game.id)}" scope="request" />
                <jsp:include page="game_block.jsp" />
            </c:if>
            <c:if test="${param.containsKey('notEnoughMoney')}">
                <div class="modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" style="color: black">Not enough money</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <script>
                    window.onload = () => new bootstrap.Modal($("div[class=modal]")[0]).show();
                </script>
            </c:if>
        </c:forEach>
    </div>
</body>
</html>