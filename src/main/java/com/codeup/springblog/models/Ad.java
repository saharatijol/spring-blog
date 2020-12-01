package com.codeup.springblog.models;
import javax.persistence.*;

@Entity
public class Ad {

    // Creating tables into db
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    private long id; // MAKES IT PRIMARY KEY

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    // Constructor
    public Ad() {}

    // CREATE
    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // READ
    public Ad(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}