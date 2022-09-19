package com.edu.ulab.app.dao;

import com.edu.ulab.app.entity.Book;

import java.util.List;

public interface BookDAO {
    public Book create(Book book);

    public List<Book> getBooksByUserId(Long userId);

    public List<Book> deleteByUserId(Long userId);

    public Book update(Book book);
}
