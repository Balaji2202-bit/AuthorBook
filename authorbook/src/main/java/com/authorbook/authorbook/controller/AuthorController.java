package com.authorbook.authorbook.controller;

import com.authorbook.authorbook.entity.Author;
import com.authorbook.authorbook.payload.AuthorDTo;
import com.authorbook.authorbook.payload.AuthorResponse;
import com.authorbook.authorbook.service.impl.AuthorServiceImpl;
import com.authorbook.authorbook.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/author")
public class AuthorController {
    public AuthorServiceImpl authorServiceImpl;

    public AuthorController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }
    @PostMapping
    public ResponseEntity<AuthorDTo> addAuthor(@RequestBody AuthorDTo authorDTo)
    {
        return new ResponseEntity<>(authorServiceImpl.createAuthor(authorDTo), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTo> update(@PathVariable() Long id, @RequestBody AuthorDTo authorDTo)
    {
        return new ResponseEntity<>(authorServiceImpl.updateAuthor(authorDTo,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable() Long id, @RequestBody Author author)
    {
        authorServiceImpl.deleteAuthorById(id);
        return new ResponseEntity<>("Successfully deleted author name",HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<AuthorResponse> get(@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                              @RequestParam(name = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                              @RequestParam(name = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                              @RequestParam(name = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir)
    {
        return new ResponseEntity<>(authorServiceImpl.getAllAuthor(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable() Long id)
    {
        return new ResponseEntity(authorServiceImpl.getAuthorById(id), HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> updated(@PathVariable()Long id,@RequestBody AuthorDTo authorDTo)
    {
        authorServiceImpl.updateResource(id,authorDTo);
        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateData(@PathVariable Long id, @RequestBody AuthorDTo newData) {
        authorServiceImpl.updateData(id, newData);
        return ResponseEntity.ok("Data updated successfully.");
    }
}
