package com.example.springproj10.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

// create table posts_table_3(id serial primary key, title varchar(100) not null, content text not null, referenceID int, constraint fk_reference foreign key(referenceID) references reference_table_3(id));

@Entity
@Table(name = "posts_table_3")
public class PostWithReferenceId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "content")
    @NotNull
    private String content;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "referenceid")
    private Reference reference;

    public PostWithReferenceId() {}

    public PostWithReferenceId(String title, String content, Reference reference) {
        this.title = title;
        this.content = content;
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "PostWithReferenceId [id=" + id + ", title=" + title + ", reference=" + reference.getId() + "]";
    }
}