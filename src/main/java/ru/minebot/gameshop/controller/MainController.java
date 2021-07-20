package ru.minebot.gameshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.minebot.gameshop.model.Game;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.GameCRUD;
import ru.minebot.gameshop.orm.UserCRUD;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        GameCRUD gameCRUD = new GameCRUD();
        List<Game> games = gameCRUD.getAll();
        model.addAttribute("games", games);
        return "shop";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute UserShop userShop, Model model) {
        new UserCRUD().createUser(userShop);
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(Model model) {
        //System.out.println(userShop);
        return "login";
    }
}
