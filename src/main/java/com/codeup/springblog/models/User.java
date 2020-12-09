package com.codeup.springblog.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length=150, unique=true)
    private String username;

    @Column(nullable = false, length=150, unique=true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private Date memberSince;

    public User() {

    }

    // READ
    public User(long id, String username, String email, String password, Date memberSince) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.memberSince = memberSince;
    }

    // CREATE
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // USER COPY FOR SPRING SECURITY
    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }
}
