package com.example.springproj10.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

// create table reference_table_3(id serial primary key, referenceLinkURL varchar(200) not null);

@Entity
@Table(name = "reference_table_3")
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "referencelinkurl")
    @NotNull
    private String referenceLinkURL;

    public long getId() {
        return id;
    }

    public Reference(String referenceLinkURL) {
        this.referenceLinkURL = referenceLinkURL;
    }

    public Reference() {}

    public String toString() {
        return "User [id=" + id + ", referenceLinkURL=" + referenceLinkURL + "]";
    }
}