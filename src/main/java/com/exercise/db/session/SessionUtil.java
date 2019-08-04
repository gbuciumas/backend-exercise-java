package com.exercise.db.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class SessionUtil {
    private static final SessionUtil INSTANCE = new SessionUtil();
    private SessionFactory sessionFactory;

    public static SessionUtil getInstance(){
        return INSTANCE;
    }

    private SessionUtil(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        return session;
    }
}
