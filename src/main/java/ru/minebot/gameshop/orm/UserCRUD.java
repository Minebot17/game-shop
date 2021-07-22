package ru.minebot.gameshop.orm;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.minebot.gameshop.Utils;
import ru.minebot.gameshop.model.UserShop;

public class UserCRUD {

    public void createUser(UserShop userShop) {
        userShop.setPassword(new BCryptPasswordEncoder().encode(userShop.getPassword()));

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userShop);
        session.flush();
        session.close();
    }

    public void updateUser(UserShop userShop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(userShop);
        session.flush();
        session.close();
    }

    public void deleteUser(UserShop userShop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(userShop);
        session.flush();
        session.close();
    }

    public UserShop getByName(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM UserShop U WHERE U.login = '" + userName + "'");
        return (UserShop) Utils.resultOneOrNull(query);
    }

    public UserShop getByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM UserShop U WHERE U.email = '" + email + "'");
        return (UserShop) Utils.resultOneOrNull(query);
    }
}
