<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="css/game_block.css"/>
<div class="game_block">
    <p class="game_name">${game.title}</p>
    <img src="res/games/${game.id}.png" width="200" /><br/><br/>
    <p class="game_description">${game.description}</p>
    <button type="button" class="btn btn-secondary" name="buy_game">Buy ${game.price}$</button>
</div>