package ru.minebot.gameshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.minebot.gameshop.EmailServiceImpl;
import ru.minebot.gameshop.Utils;
import ru.minebot.gameshop.model.ConfirmToken;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.ConfirmTokenOperations;
import ru.minebot.gameshop.orm.UserOperations;
import ru.minebot.gameshop.security.UserShopDetails;

@Controller
public class AuthController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute UserShop userShop, Model model) {
        UserOperations userOperations = new UserOperations();
        UserShop userByLogin = userOperations.getByName(userShop.getLogin());
        UserShop userByEmail = userOperations.getByEmail(userShop.getEmail());

        if (userByLogin != null) {
            model.addAttribute("errorMessage", "Error. This login is already taken");
            return "register";
        }

        if (userByEmail != null) {
            model.addAttribute("errorMessage", "Error. This email is already taken");
            return "register";
        }

        if (!userShop.getPassword().equals(userShop.getPasswordConfirmation())) {
            model.addAttribute("errorMessage", "Error. Passwords do not match");
            return "register";
        }

        userOperations.createUser(userShop);
        emailService.sendConfirmationMailForUser(userShop.getEmail());
        Utils.updateUser(userShop);
        return "redirect:/register_complete";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/email_confirm/{token}")
    public String email_confirm(@PathVariable String token, Model model) {
        ConfirmTokenOperations tokenOperations = new ConfirmTokenOperations();
        UserOperations userOperations = new UserOperations();
        ConfirmToken tokenObject = tokenOperations.getByToken(token);
        if (tokenObject == null)
            return "redirect:/";

        UserShop userShop = userOperations.getById(tokenObject.getUserId());
        userShop.setEmailConfirmed(true);
        userOperations.updateUser(userShop);
        tokenOperations.deleteToken(tokenObject);
        Utils.updateUser(userShop);
        return "redirect:/email_confirmed";
    }
}
