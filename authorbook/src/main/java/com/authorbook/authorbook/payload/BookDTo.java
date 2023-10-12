package com.authorbook.authorbook.payload;

import lombok.Data;

@Data
public class BookDTo {
//    private Long id;
//    private String title;

    private Long bookId;
    private  String bookTitle;
    private  String bookDescription;
    private String  publicationYear;
}