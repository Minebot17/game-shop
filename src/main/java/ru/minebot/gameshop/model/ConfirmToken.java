package ru.minebot.gameshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "confirm_tokens")
public class ConfirmToken {

    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "user_id")
    private long userId;

    public ConfirmToken() {
    }

    public ConfirmToken(String token, long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmToken that = (ConfirmToken) o;
        return token.equals(that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "ConfirmToken{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
