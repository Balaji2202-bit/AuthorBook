package com.authorbook.authorbook.service;
import com.authorbook.authorbook.payload.BookDTo;

import java.util.List;

public interface BookService
{
//    Book createBook(Book book, Long authorId);
//    List<Book> getBooksByAuthorId(Long authorId);

    BookDTo createBook(long authorId, BookDTo bookDTo);
//    List<BookDTo> getBooksByAuthorId(long authorId);
    BookDTo getBookById(Long authorIs, Long bookId);
    BookDTo updateBookName(Long postId, long bookId, BookDTo bookDTo);
    void deleteBookName(Long bookId, Long authorId);
}

