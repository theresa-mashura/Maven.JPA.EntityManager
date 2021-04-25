package services;

import entities.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class BookService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BooksDB");
    EntityManager em = emf.createEntityManager();

    // findById()
    public Book findBook(Long id) {
        Book book = em.find(Book.class, new Long(id));
        em.detach(book);
        return book;
    }

    // findAll()
    public Collection<Book> findAllBooks() {
        Query query = em.createQuery("SELECT b FROM Book b");
        return (Collection<Book>) query.getResultList();
    }

    // update()
    public void updateBook(Long id, Book newBook) {
        Book bookToUpdate = findBook(id);
        em.detach(bookToUpdate);

        bookToUpdate.setTitle(newBook.getTitle());
        bookToUpdate.setPublisher(newBook.getPublisher());
        bookToUpdate.setPages(newBook.getPages());
        bookToUpdate.setGenre(newBook.getGenre());
        bookToUpdate.setAuthor(newBook.getAuthor());

        em.getTransaction().begin();
        em.merge(bookToUpdate);
        em.getTransaction().commit();

    }

    // create()
    public Long createBook(Book book) {
        em.getTransaction().begin();
        em.persist(book);
        em.detach(book);
        em.getTransaction().commit();
        return book.getId();
    }

    // delete()
    public void deleteBook(Long id) {
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        em.getTransaction().commit();
    }


}
