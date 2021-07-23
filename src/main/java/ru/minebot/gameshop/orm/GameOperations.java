package ru.minebot.gameshop.orm;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.minebot.gameshop.Utils;
import ru.minebot.gameshop.model.Game;
import ru.minebot.gameshop.model.UserShop;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GameOperations {

    public List<Game> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Game");
        return query.list();
    }

    public List<Long> getOwnedGames(UserShop userShop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createSQLQuery("SELECT owned_games(" + userShop.getId() + ")");
        List<BigInteger> result = (List<BigInteger>) query.getResultList();
        List<Long> casted = new ArrayList<>();
        for (BigInteger bigInteger: result)
            casted.add(bigInteger.longValue());

        return casted;
    }

    public Game getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Game G WHERE G.id = '" + id + "'");
        return (Game) Utils.resultOneOrNull(query);
    }

    public void buyGame(UserShop userShop, Game game) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createSQLQuery(String.format("INSERT INTO user_games VALUES (%d, %d)", userShop.getId(), game.getId()));
        session.beginTransaction();
        query.executeUpdate();
        session.flush();
        session.close();
    }
}
