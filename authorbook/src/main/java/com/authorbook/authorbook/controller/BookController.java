package com.authorbook.authorbook.controller;

import com.authorbook.authorbook.payload.BookDTo;
import com.authorbook.authorbook.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/author")
public class BookController {
    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/{authorId}/book")
    public ResponseEntity<BookDTo> addBook(@PathVariable(value = "authorId") long id, @RequestBody BookDTo bookDTo)
    {
        return new ResponseEntity<>(bookService.createBook(id,bookDTo), HttpStatus.OK);
    }
//    @GetMapping("/{authorId}/book")
//    public  ResponseEntity<List<BookDTo>> showBook(@PathVariable(value = "authorId") Long authorId)
//    {
//        return new ResponseEntity<>(bookService.getBooksByAuthorId(authorId),HttpStatus.OK);
//    }
    @GetMapping("/{authorId}/book/{bookId}")
    public ResponseEntity<BookDTo> getBook(@PathVariable()Long authorId,@PathVariable()Long bookId)
    {
        return new ResponseEntity<>(bookService.getBookById(authorId,bookId),HttpStatus.OK);
    }
    @PutMapping("/{authorId}/book/{bookId}")
    public ResponseEntity<BookDTo> updateBookname(@PathVariable()Long authorId,@PathVariable()Long bookId,@RequestBody BookDTo bookDTo)
    {
        return new ResponseEntity<>(bookService.updateBookName(authorId,bookId,bookDTo),HttpStatus.OK);
    }
    @DeleteMapping("/{authorId}/book/{bookId}")
    public ResponseEntity<String> deleteBookName(@PathVariable() Long authorId,@PathVariable() Long bookId)
    {
        bookService.deleteBookName(bookId,authorId);
        return new ResponseEntity<>("Successfully BookName Deleted",HttpStatus.OK);
    }
}
