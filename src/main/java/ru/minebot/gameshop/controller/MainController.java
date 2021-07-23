package ru.minebot.gameshop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.minebot.gameshop.Utils;
import ru.minebot.gameshop.model.Game;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.GameOperations;
import ru.minebot.gameshop.orm.UserOperations;
import ru.minebot.gameshop.security.UserShopDetails;

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
        GameOperations gameOperations = new GameOperations();
        List<Game> games = gameOperations.getAll();
        List<Long> bought_games = gameOperations.getOwnedGames(Utils.getCurrentUser());
        model.addAttribute("games", games);
        model.addAttribute("bought_games", bought_games);
        return "shop";
    }

    @GetMapping("/library")
    public String library(Model model) {
        GameOperations gameOperations = new GameOperations();
        List<Game> games = gameOperations.getAll();
        List<Long> bought_games = gameOperations.getOwnedGames(Utils.getCurrentUser());
        model.addAttribute("games", games);
        model.addAttribute("bought_games", bought_games);
        return "library";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("emailConfirmed", Utils.getCurrentUser().isEmailConfirmed());
        return "profile";
    }


    @PostMapping("/profile")
    public String profileSubmit(@RequestParam Map<String, String> body, Model model) {
        UserOperations crud = new UserOperations();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserShopDetails userDetails = (UserShopDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserShop newUserShop = new UserShop(userDetails);
        String newLogin = body.get("login");
        String newEmail = body.get("email");
        String password = body.get("password");
        String newPassword = body.get("newPassword");

        UserShop userByLogin = crud.getByName(newLogin);
        UserShop userByEmail = crud.getByEmail(newEmail);

        if (userByLogin != null && !userDetails.getUsername().equals(newLogin)) {
            model.addAttribute("errorMessage", "Error. This login is already taken");
            return "profile";
        }
        else
            newUserShop.setLogin(newLogin);

        if (userByEmail != null && !userDetails.getEmail().equals(newEmail)) {
            model.addAttribute("errorMessage", "Error. This email is already taken");
            return "profile";
        }
        else
            newUserShop.setEmail(newEmail);

        if (!password.isEmpty() || !newPassword.isEmpty()) {
            if (password.equals(newPassword)) {
                model.addAttribute("errorMessage", "Error. Passwords are equal");
                return "profile";
            }
            else if (newPassword.isEmpty()) {
                model.addAttribute("errorMessage", "Error. New password is empty");
                return "profile";
            }
            else if (!encoder.matches(password, userDetails.getPassword())) {
                model.addAttribute("errorMessage", "Error. Original password not correct");
                return "profile";
            }
            else
                newUserShop.setPassword(encoder.encode(newPassword));
        }

        crud.updateUser(newUserShop);
        Utils.updateUser(newUserShop);
        return "redirect:/";
    }

    @GetMapping("/profile/get_money")
    public String profileGetMoney(Model model) {
        UserShop userShop = Utils.getCurrentUser();
        userShop.setMoney(userShop.getMoney() + 10);
        new UserOperations().updateUser(userShop);
        Utils.updateUser(userShop);
        return "redirect:/profile";
    }

    @GetMapping("/register_complete")
    public String registerComplete(Model model) {
        return "register_complete";
    }

    @GetMapping("/email_confirmed")
    public String emailConfirmed(Model model) {
        return "email_confirmed";
    }

    @GetMapping("/shop/buy/{gameId}")
    public String buyGame(@PathVariable long gameId, Model model) {
        GameOperations gameOperations = new GameOperations();
        UserOperations userOperations = new UserOperations();
        UserShop userShop = Utils.getCurrentUser();
        Game gameToBuy = gameOperations.getById(gameId);

        if (userShop.getMoney() < gameToBuy.getPrice())
            return "redirect:/shop?notEnoughMoney";

        gameOperations.buyGame(userShop, gameToBuy);
        userShop.setMoney(userShop.getMoney() - gameToBuy.getPrice());
        userOperations.updateUser(userShop);
        Utils.updateUser(userShop);
        return "redirect:/shop";
    }
}
