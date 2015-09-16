package by.amelkov.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "notes")
public class Note implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login", nullable = false)
    private User login;

    @Column(name = "datecreate", nullable = false)
    private Timestamp dateCreate;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getLogin() {
        return login;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLogin(User login) {
        this.login = login;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Note(int id, String text, User login, Timestamp dateCreate) {
        this.id = id;
        this.text = text;
        this.login = login;
        this.dateCreate = dateCreate;
    }

    public Note(String text, User login, Timestamp dateCreate) {
        this.text = text;
        this.login = login;
        this.dateCreate = dateCreate;
    }

    public Note() {
    }
}
