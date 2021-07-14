package ru.minebot.gameshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class GameShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameShopApplication.class, args);

	}

}
