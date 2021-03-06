package ru.minebot.gameshop.orm;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import ru.minebot.gameshop.model.ConfirmToken;
import ru.minebot.gameshop.model.Game;
import ru.minebot.gameshop.model.UserShop;

public class HibernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/game_shop");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "admin");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
                registryBuilder.applySettings(settings);

                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(Game.class);
                sources.addAnnotatedClass(UserShop.class);
                sources.addAnnotatedClass(ConfirmToken.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();

                if (registry != null)
                    StandardServiceRegistryBuilder.destroy(registry);
            }
        }

        return sessionFactory;
    }

    public static void close() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}