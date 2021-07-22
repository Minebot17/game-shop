package ru.minebot.gameshop.orm;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.minebot.gameshop.Utils;
import ru.minebot.gameshop.model.ConfirmToken;
import ru.minebot.gameshop.model.UserShop;

public class ConfirmTokenOperations {

    public ConfirmToken addToken(long userId) {
        ConfirmToken confirmToken = new ConfirmToken(Utils.getRandomString(64), userId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(confirmToken);
        session.flush();
        session.close();
        return confirmToken;
    }

    public ConfirmToken getByToken(String token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM ConfirmToken T WHERE T.token = '" + token + "'");
        return (ConfirmToken) Utils.resultOneOrNull(query);
    }

    public void deleteToken(ConfirmToken token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(token);
        session.flush();
        session.close();
    }
}
