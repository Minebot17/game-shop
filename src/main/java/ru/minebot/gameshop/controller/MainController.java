package ru.minebot.gameshop.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.minebot.gameshop.model.Game;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.GameCRUD;
import ru.minebot.gameshop.orm.UserCRUD;
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
        UserCRUD crud = new UserCRUD();
        UserShop userByLogin = crud.getByName(userShop.getLogin());
        UserShop userByEmail = crud.getByEmail(userShop.getEmail());

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

        crud.createUser(userShop);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

    @PostMapping("/profile")
    public String profileSubmit(@RequestParam Map<String, String> body, Model model) {
        UserCRUD crud = new UserCRUD();
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
        UserShopDetails newUserDetails = new UserShopDetails(newUserShop);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUserDetails, newUserDetails.getPassword(), newUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
}
