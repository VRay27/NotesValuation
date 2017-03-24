package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.realstate.entity.notes.Note;
 
public class Test 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
 
        Session session = sessionFactory.openSession();
 
        Transaction tx = session.beginTransaction();
        Note task = new Note();
        task.setNoteId(new Integer(1));
        session.save(task);
        tx.commit();
        session.close();
    }
}
