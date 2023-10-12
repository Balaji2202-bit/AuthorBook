package com.authorbook.authorbook.service.impl;

import com.authorbook.authorbook.entity.Author;
import com.authorbook.authorbook.entity.Book;
import com.authorbook.authorbook.exception.BlogAPIException;
import com.authorbook.authorbook.exception.ResourceNotFoundException;
import com.authorbook.authorbook.payload.BookDTo;
import com.authorbook.authorbook.repository.AuthorRepository;
import com.authorbook.authorbook.repository.BookRepository;
import com.authorbook.authorbook.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTo createBook(long authorId, BookDTo bookDTo)
    {

        Book book = mapToEntity(bookDTo);
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "authorId", authorId));
        book.setAuthor(author);
        Book newComment = bookRepository.save(book);
        return mapToDto(newComment);
    }
//    @Override
//    public List<BookDTo> getBooksByAuthorId(long authorId)
//    {
//        List<Book> comments = bookRepository.findByAuthorId(authorId);
//
//        // converting list of comment entity to list of DTO
//        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
//    }
    @Override
    public BookDTo getBookById(Long authorId, Long bookId)
    {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new ResourceNotFoundException("author", "authorId", authorId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));
        if (!(book.getAuthor().getAuthorId().equals(author.getAuthorId()))) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Book Doesn't exist from this Author ");
        }
        return mapToDto(book);
    }
    @Override
    public BookDTo updateBookName(Long authorId, long bookId, BookDTo bookDTo)
    {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new ResourceNotFoundException("author", "authorId", authorId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));
        if (!(book.getAuthor().getAuthorId().equals(author.getAuthorId()))) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Book does not belong to author ");
        }
        book.setBookTitle(bookDTo.getBookTitle());
        book.setBookDescription(bookDTo.getBookDescription());
        book.setPublicationYear(bookDTo.getPublicationYear());
        BookDTo bookDTo1=mapToDto(bookRepository.save(book));
        return bookDTo1;
    }
    @Override
    public void deleteBookName(Long bookId, Long authorId)
    {
        Author author=authorRepository.findById(authorId).orElseThrow(()->new ResourceNotFoundException("author","authorId", authorId));
        Book book=bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));
        if(book.getAuthor().getAuthorId().equals(authorId))
        {
            bookRepository.deleteById(bookId);
        }
        else {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Book Doesn't Exist from this Author Id");
        }
    }
    private BookDTo mapToDto(Book newComment)
    {
        BookDTo bookDTo = new BookDTo();
//        bookDTo.setId(newComment.getId());
//        bookDTo.setTitle(newComment.getTitle());
        bookDTo.setBookId(newComment.getBookId());
        bookDTo.setBookTitle(newComment.getBookTitle());
        bookDTo.setBookDescription(newComment.getBookDescription());
        bookDTo.setPublicationYear(newComment.getPublicationYear());
        return bookDTo;
    }
    private Book mapToEntity(BookDTo bookDTo)
    {
        Book book = new Book();
//        book.setId(bookDTo.getId());
//        book.setTitle(bookDTo.getTitle());
        book.setBookId(bookDTo.getBookId());
        book.setBookTitle(bookDTo.getBookTitle());
        book.setBookDescription(bookDTo.getBookDescription());
        book.setPublicationYear(bookDTo.getPublicationYear());
        return book;
    }
}
