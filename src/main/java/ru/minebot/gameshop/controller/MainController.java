package ru.minebot.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.minebot.gameshop.model.Game;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.GameCRUD;
import ru.minebot.gameshop.orm.UserCRUD;

import java.util.List;

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
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
