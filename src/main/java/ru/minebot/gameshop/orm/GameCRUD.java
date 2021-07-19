package ru.minebot.gameshop.orm;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.minebot.gameshop.model.Game;

import java.util.List;

public class GameCRUD {
    public List<Game> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Game");
        return query.list();
    }
}
