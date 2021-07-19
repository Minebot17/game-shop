package ru.minebot.gameshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.minebot.gameshop.model.Game;
import ru.minebot.gameshop.orm.GameCRUD;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        GameCRUD gameCRUD = new GameCRUD();
        List<Game> games = gameCRUD.getAll();
        model.addAttribute("games", games);
        return "index";
    }
}
