package com.authorbook.authorbook.service.impl;

import com.authorbook.authorbook.entity.Author;
import com.authorbook.authorbook.entity.Book;
import com.authorbook.authorbook.exception.ResourceNotFoundException;
import com.authorbook.authorbook.payload.AuthorDTo;
import com.authorbook.authorbook.payload.AuthorResponse;
import com.authorbook.authorbook.payload.BookDTo;
import com.authorbook.authorbook.repository.AuthorRepository;
import com.authorbook.authorbook.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AuthorServiceImpl implements AuthorService {
    public AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTo createAuthor(AuthorDTo authorDTo)
    {
        Author author=mapToEntity(authorDTo);
        // converting entity toDTO
        Author author1=authorRepository.save(author);
        AuthorDTo authorResponse=mapToDTO(author1);
        return authorResponse;
    }

    @Override
    public AuthorDTo updateAuthor(AuthorDTo authorDTo, Long id) {

        Author author=authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("author","authorId",id));
        author.setAuthorName(authorDTo.getAuthorName());
        author.setAuthorCountry(authorDTo.getAuthorCountry());
        author.setAuthorGender(authorDTo.getAuthorGender());
        Author updatePost=authorRepository.save(author);
        return mapToDTO(updatePost);
    }
    @Override
    public AuthorDTo updateResource(Long id, AuthorDTo authorDTo)
    {
        Author author=authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("author","authorId",id));
        author.setAuthorName(authorDTo.getAuthorName());
        author.setAuthorCountry(authorDTo.getAuthorCountry());
        author.setAuthorGender(authorDTo.getAuthorGender());
        Author updatePost=authorRepository.save(author);
        return mapToDTO(updatePost);
    }
    @Transactional
    public void updateData(Long id, AuthorDTo newData) {
        // Retrieve the existing entity from the database
        Author author = authorRepository.findById(id).orElse(null);

        if (author != null) {
            // Update the data
            author.setAuthorName(newData.getAuthorName());
            author.setAuthorCountry(newData.getAuthorCountry());
            author.setAuthorGender(newData.getAuthorGender());
            // Update other fields as needed

            // Save the updated entity back to the database
            authorRepository.save(author);
        }
    }
    @Override
    public void deleteAuthorById(Long id)
    {
        Author author=authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("author","authorId",id));
        authorRepository.delete(author);
    }

    @Override
    public AuthorDTo getAuthorById(long id) {
        Author author=authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("author","authorId",id));
        return mapToDTO(author);
    }

    @Override
    public AuthorResponse getAllAuthor(int pageNo, int pageSize, String sortBy,String sortDir)
    {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        // create pageable instance
        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);

        Page<Author> authors=authorRepository.findAll(pageable);

        // get content for page object
        List<Author> listOfPosts=authors.getContent();
        List<AuthorDTo> content= listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        AuthorResponse authorResponse=new AuthorResponse();
        authorResponse.setContent(content);
        authorResponse.setPageNo(authors.getNumber());
        authorResponse.setPageSize(authors.getSize());
        authorResponse.setTotalElements(authors.getTotalElements());
        authorResponse.setTotalPages(authors.getTotalPages());
        authorResponse.setLast(authors.isLast());
        return authorResponse;
    }

    private AuthorDTo mapToDTO(Author author)
    {
        AuthorDTo authorDTo=new AuthorDTo();
        authorDTo.setAuthorId(author.getAuthorId());
        authorDTo.setAuthorName(author.getAuthorName());
        authorDTo.setAuthorCountry(author.getAuthorCountry());
        authorDTo.setAuthorGender(author.getAuthorGender());
        authorDTo.setTitle(author.getBook().stream().map(this::mapToDTOBook).collect(Collectors.toList()));
        return authorDTo;
    }
    private Author mapToEntity(AuthorDTo authorDTo)
    {
        Author author=new Author();
        author.setAuthorName(authorDTo.getAuthorName());
        author.setAuthorCountry(authorDTo.getAuthorCountry());
        author.setAuthorGender(authorDTo.getAuthorGender());
        return author;
    }

//    -----------------------------------------------------------------//
    private BookDTo mapToDTOBook(Book newComment)
    {
        BookDTo bookDTo = new BookDTo();
        bookDTo.setBookId(newComment.getBookId());
        bookDTo.setBookTitle(newComment.getBookTitle());
        bookDTo.setBookDescription(newComment.getBookDescription());
        bookDTo.setPublicationYear(newComment.getPublicationYear());
        return bookDTo;
    }
}
