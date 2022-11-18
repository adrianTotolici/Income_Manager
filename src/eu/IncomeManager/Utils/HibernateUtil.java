package eu.IncomeManager.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by adrian on 2/18/14.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactroy=buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory
                    ;
        }catch (Throwable ex){
           System.out.println("Initial SessionFactory creation failed." + ex);
           throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactroy(){
        return sessionFactroy;
    }

    public static void shutdown(){
        getSessionFactroy().close();
    }
}
