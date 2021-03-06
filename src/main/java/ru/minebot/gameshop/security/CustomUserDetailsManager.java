package ru.minebot.gameshop.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.orm.UserOperations;

@Service
public class CustomUserDetailsManager implements UserDetailsManager {

    private UserOperations crud = new UserOperations();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserShop userShop = crud.getByName(s);
        if (userShop == null)
            throw new UsernameNotFoundException("User " + s + " not found");

        return new UserShopDetails(userShop);
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
