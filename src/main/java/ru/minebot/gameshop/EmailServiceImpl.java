package ru.minebot.gameshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.minebot.gameshop.model.ConfirmToken;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.ConfirmTokenOperations;
import ru.minebot.gameshop.orm.UserOperations;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendConfirmationMailForUser(String email) {
        long id = new UserOperations().getByEmail(email).getId();
        ConfirmToken token = new ConfirmTokenOperations().addToken(id);
        sendSimpleMessage(email, "game-shop: email confirm",
                "To confirm your email after registration, open this page: http://127.0.0.1:7777/email_confirm/" + token.getToken());
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@game-shop.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}