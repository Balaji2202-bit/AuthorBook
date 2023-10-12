package com.authorbook.authorbook.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long authorId;
    @Column(nullable = false)
    private String authorName;
    @Column(nullable = false)
    private String authorCountry;
    @Column(nullable = false)
    private String authorGender;
//    private Long id;
//    @Column(nullable = false)
//    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Book> book=new HashSet<>();
}