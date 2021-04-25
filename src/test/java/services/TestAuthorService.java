package services;

import entities.Author;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class TestAuthorService {

    private AuthorService a;

    @Before
    public void setup() {
        this.a = new AuthorService();
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
}
