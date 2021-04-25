package services;

import entities.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class TestBookService {

    private BookService b;

    @Before
    public void setup() {
        this.b = new BookService();
    }


    @Test
    public void testFindBook() {
        // Arrange
        Book newBook = new Book("Harry Potter", "J.K. Rowling", "Random House", "Young Adult", 300);
        Long id = b.createBook(newBook);

        // Act
        Book book = b.findBook(id);
        Long actualId = book.getId();

        // Assert
        Assert.assertEquals(id, actualId);

        b.deleteBook(id);
    }

    @Test
    public void testFindAllBooks() {
        // Arrange

        // Act
        Collection<Book> books = b.findAllBooks();

        // Assert
        Assert.assertTrue(books.size() > 0);

        for (Book b : books) {
            System.out.println(b.toString());
        }
    }

    @Test
    public void testUpdateBook() {
        // Arrange
        Book givenBook = new Book("TEST UPDATE", "J.K. Rowling", "Penguin", "Fantasy", 300);
        Book updatedBook = new Book("TEST UPDATE", "NEW AUTHOR", "Penguin", "Fantasy", 300);

        // Act
        Long id = b.createBook(givenBook);
        b.updateBook(id, updatedBook);

        Book actualBook = b.findBook(id);
        String actualAuthor = actualBook.getAuthor();

        // Assert
        Assert.assertEquals("NEW AUTHOR", actualAuthor);

        b.deleteBook(id);
    }


    @Test
    public void testCreateBook() {
        // Arrange
        Book book = new Book("TEST CREATE", "J.K. Rowling", "Penguin", "Fantasy", 300);

        // Act
        Long id = b.createBook(book);

        // Assert
        Assert.assertNotNull(id);
        Assert.assertTrue(id > 0);

        b.deleteBook(id);
    }

    @Test (expected = NullPointerException.class)
    public void testDeleteBook() {
        // Arrange
        Book book = new Book("TEST DELETE", "J.K. Rowling", "Penguin", "Fantasy", 300);
        Long id = b.createBook(book);

        // Act
        b.deleteBook(id);
        Book bookAfterDelete = b.findBook(id);

        // Assert

    }

}
