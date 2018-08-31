package com.m2i.poe.media;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@Path("/bookrepo")// JE SAIS PAS SI C'EST NECESSAIRE
public class BookJPARepository implements IBookRepository {

    private static EntityManager em = EntityManagerFactorySingleton.getEntityManager();


    @Override
    public void load(String uri) throws IOException, ClassNotFoundException, SQLException {
    }

    @Override
    public List<Book> getAll() throws SQLException {
        return em.createQuery("select b from Book b").getResultList();
    }

    @Override
    public Book getById(int id) throws SQLException {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getByTitle(String title) throws SQLException {
        return em.createQuery("select b from Book b where upper(b.title) like '%"+ title.toUpperCase() + "%'").getResultList();
    }

    @Override
    public List<Book> getByPrice(double price) throws SQLException {
        return em.createQuery("select b from Book b where b.price <= "+ price).getResultList();
    }

    @Override
    public List<Book> getByPublisher(String publisherName) throws SQLException {
        return em.createQuery("select b from Book b where p.publisher ==" + Integer.parseInt(publisherName)).getResultList();
    }

    //createBook by Baptiste for method PUT in HelloWorldRest
     public static Response createBook(String title, double price){
        Book b = new Book();
        b.setTitle(title);
        b.setPrice(price);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(b);
        t.commit();
        return Response.ok().build();
     }


   /*I DON'T KNOW HOW TO MAKE IT WORK IF I DEFINE THE METHOD HERE
   public static Response updateBook(String title, double price) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(book);
        em.persist(book);
        t.commit();
        return Response.ok().build();
    }*/

    @Override
    public void add(Book b) throws SQLException {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(b);
        t.commit();
    }

    //Remove by Baptiste, using the method DELETE of HelloWorldRest
    //@Override
    public void remove(int id) throws SQLException {
        Book b = em.find(Book.class, id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(b);
        t.commit();
    }

    @Override
    public void remove(Book b) throws SQLException {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(b);
        t.commit();
    }

    @Override
    public void update(Book b) throws SQLException {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(em.merge(b));
        t.commit();
    }
}
