package com.authorbook.authorbook.payload;

import lombok.*;

import java.util.List;

@Data
public class AuthorDTo {
    private  Long authorId;
    private String authorName;
    private String authorCountry;
    private String authorGender;
//    private  Long id;
//    private String name;
    private List<BookDTo> title;
}