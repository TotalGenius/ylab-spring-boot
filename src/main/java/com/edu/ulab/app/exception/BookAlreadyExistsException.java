package com.edu.ulab.app.exception;

import com.edu.ulab.app.entity.Book;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(Book book){
        super("This book already exists:"+"title-"+book.getTitle()+", author-"+book.getAuthor());
    }
}
