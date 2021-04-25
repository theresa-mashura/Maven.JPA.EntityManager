package services;

import entities.Author;
import entities.Book;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


public class AuthorService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BooksDB");
    EntityManager em = emf.createEntityManager();

    // getAllBooks()
    public List<Book> getBooks(Long id) {
        Author author = em.find(Author.class, id);
        em.detach(author);
        List<Book> booksWritten = author.getBks();
        return booksWritten;
    }

    // findById()
    public Author findAuthor(Long id) {
        Author author = em.find(Author.class, new Long(id));
        em.detach(author);
        return author;
    }

    // findAll()
    public Collection<Author> findAllAuthors() {
        Query query = em.createQuery("SELECT a FROM Author a");
        return (Collection<Author>) query.getResultList();
    }

    // update()
    public void updateAuthor(Long id, Author newAuthor) {
        Author authorToUpdate = findAuthor(id);
        em.detach(authorToUpdate);

        authorToUpdate.setFirstName(newAuthor.getFirstName());
        authorToUpdate.setLastName(newAuthor.getLastName());
        authorToUpdate.setPseudonym(newAuthor.getPseudonym());

        em.getTransaction().begin();
        em.merge(authorToUpdate);
        em.getTransaction().commit();
    }

    // create()
    public Long createAuthor(Author author) {
        em.getTransaction().begin();
        em.persist(author);
        em.detach(author);
        em.getTransaction().commit();
        return author.getId();
    }

    // delete()
    public void deleteAuthor(Long id) {
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        em.remove(author);
        em.getTransaction().commit();
    }
}
