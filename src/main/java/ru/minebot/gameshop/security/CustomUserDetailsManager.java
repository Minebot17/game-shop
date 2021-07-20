package ru.minebot.gameshop.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.UserCRUD;

public class CustomUserDetailsManager implements UserDetailsManager {

    private UserCRUD crud = new UserCRUD();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserShop userShop = crud.getByName(s);
        return User.withDefaultPasswordEncoder()
                .username(userShop.getLogin())
                .password(userShop.getPassword())
                .authorities("USER")
                .build();
    }

    @Override
    public void createUser(UserDetails userDetails) {
        crud.createUser(new UserShop(userDetails));
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        crud.updateUser(new UserShop(userDetails));
    }

    @Override
    public void deleteUser(String s) {
        crud.deleteUser(crud.getByName(s));
    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return crud.getByName(s) != null;
    }
}
