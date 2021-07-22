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
}
