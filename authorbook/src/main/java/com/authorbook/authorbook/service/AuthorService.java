package com.authorbook.authorbook.service;

import com.authorbook.authorbook.entity.Author;
import com.authorbook.authorbook.payload.AuthorDTo;
import com.authorbook.authorbook.payload.AuthorResponse;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
//    public Author addAuthor(Author author);
//    Author updateAuthor(Author author, Long id);
//    void deleteAuthor(Long id);
//    List<Author> read(Author author);
//    Optional<Author> getById(Long id);


    AuthorDTo createAuthor(AuthorDTo authorDTo);
    AuthorDTo updateAuthor(AuthorDTo authorDTo, Long id);

    AuthorDTo updateResource(Long id, AuthorDTo authorDTo);

    void deleteAuthorById(Long id);
    AuthorDTo getAuthorById(long id);
    AuthorResponse getAllAuthor(int pageNo, int pageSize, String sortBy, String sortDir);
//    Optional<Author> getById(Long id);
//    List<Author> read(Author author);
}
