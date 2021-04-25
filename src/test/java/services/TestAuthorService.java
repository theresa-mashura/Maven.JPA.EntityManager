package services;

import entities.Author;
import entities.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class TestAuthorService {

    private AuthorService a;
    private BookService b;

    @Before
    public void setup() {
        this.a = new AuthorService();
        this.b = new BookService();
    }


    @Test
    public void testFindAuthor() {
        // Arrange
        Author newBook = new Author("J.K.", "Rowling", "Robert Galbraith");
        Long id = a.createAuthor(newBook);

        // Act
        Author author = a.findAuthor(id);
        Long actualId = author.getId();

        // Assert
        Assert.assertEquals(id, actualId);

        a.deleteAuthor(id);
    }

    @Test
    public void testFindAllAuthors() {
        // Arrange

        // Act
        Collection<Author> authors = a.findAllAuthors();

        // Assert
        Assert.assertTrue(authors.size() > 0);

        for (Author aa : authors) {
            System.out.println(aa.toString());
        }
    }

    @Test
    public void testUpdateAuthor() {
        // Arrange
        Author givenAuthor = new Author("TEST UPDATE", "Rowling", "Robert Galbraith");
        Author updatedAuthor = new Author("NEW FIRST NAME", "Rowling", "Robert Galbraith");

        // Act
        Long id = a.createAuthor(givenAuthor);
        a.updateAuthor(id, updatedAuthor);

        Author actualAuthor = a.findAuthor(id);
        String actualFirstName = actualAuthor.getFirstName();

        // Assert
        Assert.assertEquals("NEW FIRST NAME", actualFirstName);

        a.deleteAuthor(id);
    }


    @Test
    public void testCreateAuthor() {
        // Arrange
        Author author = new Author("TEST CREATE", "Rowling", "Robert Galbraith");

        // Act
        Long id = a.createAuthor(author);
        Author actualAuthor = a.findAuthor(id);

        // Assert
        Assert.assertNotNull(id);
        Assert.assertTrue(id > 0);
        Assert.assertEquals("TEST CREATE", actualAuthor.getFirstName());

        a.deleteAuthor(id);
    }

    @Test (expected = NullPointerException.class)
    public void testDeleteAuthor() {
        // Arrange
        Author author = new Author("J.K.", "Rowling", "Robert Galbraith");
        Long id = a.createAuthor(author);

        // Act
        a.deleteAuthor(id);
        Author bookAfterDelete = a.findAuthor(id);

        // Assert

    }

    @Test
    public void testGetBooks() {
        // Arrange
        Author testAuthor = new Author(null, "TEST", "BOOK TO AUTHOR MAPPING", "none");
        Long idAuthor = a.createAuthor(testAuthor);
        Book book1 = new Book(null, "TEST BOOK TO AUTHOR MAPPING1", "J.K. Rowling", "Penguin", "Fantasy", 300, idAuthor);
        Book book2 = new Book(null, "TEST BOOK TO AUTHOR MAPPING2", "J.K. Rowling", "Penguin", "Fantasy", 300,idAuthor);
        Long idBook1 = b.createBook(book1);
        Long idBook2 = b.createBook(book2);

        // Act
        List<Book> booksWritten = a.getBooks(idAuthor);
        Assert.assertEquals(2, booksWritten.size());
        Assert.assertEquals(idBook1, booksWritten.get(0).getId());
        Assert.assertEquals(idBook2, booksWritten.get(1).getId());

        for (Book bk : booksWritten) {
            System.out.println(bk.toString());
        }

        b.deleteBook(idBook1);
        b.deleteBook(idBook2);
        a.deleteAuthor(idAuthor);
    }
}
