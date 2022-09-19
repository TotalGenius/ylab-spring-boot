package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dao.BookDAO;
import com.edu.ulab.app.dao.impl.BookDAOImpl;
import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    BookDAO bookDAO = new BookDAOImpl();

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = new Book(0L, bookDto.getUserId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPageCount());
        book = bookDAO.create(book);
        bookDto.setId(book.getId());
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {

        Book book = bookDAO.update(new Book(bookDto.getId(),bookDto.getUserId(),bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPageCount()));
        return new BookDto(book.getId(),bookDto.getUserId(),bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPageCount());
    }

    @Override
    public List<BookDto> getBooksByUserId(Long userId) {
        List<Book> books = bookDAO.getBooksByUserId(userId);
        List<BookDto> booksDto = books
                .stream()
                .map(x -> new BookDto(x.getId(), x.getUserId(), x.getTitle(), x.getAuthor(), x.getPageCount()))
                .toList();
        return booksDto;
    }

    @Override
    public List<BookDto> deleteBookById(Long id) {
        return bookDAO.deleteByUserId(id).stream()
                .map(x -> new BookDto(x.getId(), x.getUserId(), x.getTitle(), x.getAuthor(), x.getPageCount()))
                .toList();
    }
}
