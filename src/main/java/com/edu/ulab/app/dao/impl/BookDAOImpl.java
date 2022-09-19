package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.BookDAO;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAOImpl implements BookDAO {
    Storage storage = Storage.getStorage();

    @Override
    public Book create(Book book) {
        Book createdBook = storage.getBookTable().create(book);
        return book;
    }

    @Override
    public List<Book> getBooksByUserId(Long userId) {
        Optional<List<Book>> optionalBooks = storage.getBookTable().getAll();
        List<Book> books = optionalBooks
                .orElse(Collections.emptyList())
                .stream()
                .filter(x -> x.getUserId() == userId)
                .toList();
        return books;
    }

    @Override
    public List<Book> deleteByUserId(Long userId) {
        Optional<List<Book>> optionalBooks = storage.getBookTable().getAll();
        Optional<List<Long>> ids = Optional.ofNullable(optionalBooks
                .orElse(Collections.emptyList())
                .stream()
                .filter(x -> x.getUserId() == userId)
                .map(x -> x.getBookId())
                .toList());
        List<Book> deletedBooks = ids.orElse(Collections.emptyList())
                .stream()
                .map(x -> storage.getBookTable().delete(x).get())
                .toList();
        return deletedBooks;
    }
}
