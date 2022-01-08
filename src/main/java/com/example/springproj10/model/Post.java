package com.example.springproj10.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

// create table posts_table(id serial primary key, title varchar(100) not null, content text not null);

@Entity
@Table(name = "posts_table")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post() {}

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + "]";
    }
}