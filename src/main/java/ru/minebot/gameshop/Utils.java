package ru.minebot.gameshop;

import org.hibernate.query.Query;

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
}
