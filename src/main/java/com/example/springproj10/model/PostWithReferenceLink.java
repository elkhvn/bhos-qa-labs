package com.example.springproj10.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

// create table posts_table_2(id serial primary key, title varchar(100) not null, content text not null, referenceLinkURL varchar(200) not null);

@Entity
@Table(name = "posts_table_2")
public class PostWithReferenceLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "content")
    @NotNull
    private String content;

    @Column(name = "referenceLinkURL")
    @NotNull
    private String referenceLinkURL;

    public PostWithReferenceLink(String title, String content, String referenceLinkURL) {
        this.title = title;
        this.content = content;
        this.referenceLinkURL = referenceLinkURL;
    }

    public PostWithReferenceLink() {}

    @Override
    public String toString() {
        return "PostWithReferenceLink [id=" + id + ", title=" + title + ", referenceLinkUrl=" + referenceLinkURL + "]";
    }
}