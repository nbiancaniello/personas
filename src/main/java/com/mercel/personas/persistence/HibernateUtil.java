package com.mercel.personas.persistence;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	
	static Logger log = LoggerFactory.getLogger(HibernateUtil.class);
	
	 private static final SessionFactory sessionFactory = buildSessionFactory();
	 
	 private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();  
	        } 
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            log.error("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	 
	 public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    
	 public static void shutdown() {
	   	    getSessionFactory().close();
     }
}
