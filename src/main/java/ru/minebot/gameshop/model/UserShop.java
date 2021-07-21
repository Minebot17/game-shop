package ru.minebot.gameshop.model;

import org.springframework.security.core.userdetails.UserDetails;
import ru.minebot.gameshop.orm.UserCRUD;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_shop")
public class UserShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirmation;

    public UserShop() {}

    public UserShop(UserDetails userDetails) {
        UserShop userShop = new UserCRUD().getByName(userDetails.getUsername());
        this.id = userShop.id;
        this.login = userShop.login;
        this.email = userShop.email;
        this.password = userShop.password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserShop userShop = (UserShop) o;
        return id == userShop.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserShop{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
