package entities;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private int pages;

    private Long aId;
    @ManyToOne
    @JoinColumn(name = "aId", referencedColumnName = "id", insertable = false, updatable = false)
    private Author a;

    public Book() {
    }

    public Book(String title, String author, String publisher, String genre, int pages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.pages = pages;
    }

    public Book(Long id, String title, String author, String publisher, String genre, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.pages = pages;
    }

    public Book(Long id, String title, String author, String publisher, String genre, int pages, Long aId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.pages = pages;
        this.aId = aId;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Author getA() {
        return a;
    }

    public void setA(Author a) {
        this.a = a;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", pages=" + pages +
                '}';
    }
}
