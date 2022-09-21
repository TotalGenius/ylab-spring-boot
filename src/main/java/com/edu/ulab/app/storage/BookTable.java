package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.BookAlreadyExistsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookTable implements Table<Book> {

    public static BookTable bookTable;
    private Long currentBookId;
    private Map<Long, Book> books;

    private BookTable() {
        currentBookId = 1L;
        books = new HashMap<>();
    }

    public static BookTable getBookTable() {
        if (bookTable == null) bookTable = new BookTable();
        return bookTable;
    }

    public Map<Long, Book> getTable() {
        return books;
    }

    private Long setBookId() {
        return currentBookId++;
    }

    @Override
    public Optional<Book> get(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public Optional<List<Book>> getAll() {
        return Optional.ofNullable(books.values().stream().toList());
    }

    @Override
    public Book create(Book element) {
        element.setId(setBookId());
        books.put(element.getId(), element);
        Book createdBook = books.get(element.getId());
        return createdBook;
    }

    @Override
    public Optional<Book> delete(Long id) {
        return Optional.ofNullable(books.remove(id));
    }

    @Override
    public Optional<Book> update(Book element) {

        Optional<Book> bookOptional = Optional.empty();

        boolean alreadyExits = books.values().stream().anyMatch(x->x.getAuthor().compareToIgnoreCase(element.getAuthor())==0
        &&x.getTitle().compareToIgnoreCase(element.getTitle())==0);
        if (alreadyExits) throw new BookAlreadyExistsException(element);

        if (books.isEmpty()) {
            element.setId(setBookId());
            books.put(element.getId(), element);
            return Optional.ofNullable(books.get(element.getId()));
        }

        if (books.containsKey(element.getId())) {
            Book book = books.get(element.getId());
            if (element.getTitle() != null) book.setTitle(element.getTitle());
            if (element.getAuthor() != null) book.setAuthor(element.getAuthor());
            if (element.getPageCount() != 0) book.setPageCount(element.getPageCount());
            bookOptional = Optional.ofNullable(books.get(element.getId()));
        } else{
            element.setId(setBookId());
            books.put(element.getId(), element);
            bookOptional = Optional.ofNullable(books.get(element.getId()));
        }
        return bookOptional;
    }
}
