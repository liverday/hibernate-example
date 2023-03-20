package com.liverday;

import com.liverday.entity.Book;
import com.liverday.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Book book = new Book(
                    null,
                    "Harry Potter",
                    "A pedra filosofal"
            );

            session.persist(book);

            List<Book> results = session.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();

            System.out.println(results);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
    }
}
