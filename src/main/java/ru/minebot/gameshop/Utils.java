package ru.minebot.gameshop;

import org.hibernate.query.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.minebot.gameshop.model.UserShop;
import ru.minebot.gameshop.security.UserShopDetails;

import javax.persistence.NoResultException;

public class Utils {

    public static Object resultOneOrNull(Query query) {
        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public static String getRandomString(int length) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++)
            result.append(rndChar());

        return result.toString();
    }

    public static char rndChar() {
        int rnd = (int) (Math.random() * 52);
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }

    public static UserShop getCurrentUser() {
        return new UserShop(((UserShopDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    public static void updateUser(UserShop userShop) {
        UserShopDetails newUserDetails = new UserShopDetails(userShop);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUserDetails, newUserDetails.getPassword(), newUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
