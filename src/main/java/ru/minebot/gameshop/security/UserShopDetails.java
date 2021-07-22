package ru.minebot.gameshop.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.minebot.gameshop.model.UserShop;

import java.util.Arrays;
import java.util.Collection;

public class UserShopDetails implements UserDetails {

    private String username;
    private String password;

    private String email;
    private boolean emailConfirmed;

    public UserShopDetails(UserShop userShop) {
        this(userShop.getLogin(), userShop.getPassword(), userShop.getEmail(), userShop.isEmailConfirmed());
    }

    public UserShopDetails(String username, String password, String email, boolean emailConfirmed) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList((GrantedAuthority) () -> "USER");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
