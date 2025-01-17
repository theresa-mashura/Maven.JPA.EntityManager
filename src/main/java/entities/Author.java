package entities;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudonym;

    @OneToMany(mappedBy = "a", fetch = FetchType.EAGER)
    private List<Book> bks;

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, String pseudonym) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudonym = pseudonym;
    }

    public Author(String firstName, String lastName, String pseudonym) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudonym = pseudonym;
    }

    public List<Book> getBks() {
        return bks;
    }

    public void setBks(List<Book> bks) {
        this.bks = bks;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                '}';
    }
}
