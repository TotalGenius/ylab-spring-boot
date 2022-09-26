package com.edu.ulab.app.facade.service.impl;

import com.edu.ulab.app.dao.BookDAO;
import com.edu.ulab.app.dao.impl.BookDAOImpl;
import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.BookMapperImpl;
import com.edu.ulab.app.facade.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO = new BookDAOImpl();
    private final BookMapper bookMapper = new BookMapperImpl();


    @Override
    public BookDto createBook(BookDto bookDto) {
        Book createdBook = bookDAO.create(bookMapper.bookDtoToBook(bookDto));
        log.info("Got created book from storage{}", createdBook);
        return bookMapper.bookToBookDto(createdBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book updatedBook = bookDAO.update(bookMapper.bookDtoToBook(bookDto));
        log.info("Got created book from storage{}", updatedBook);
        return bookMapper.bookToBookDto(updatedBook);
    }

    @Override
    public List<BookDto> getBooksByUserId(Long userId) {
        List<Book> books = bookDAO.getBooksByUserId(userId);
        log.info("Got books from storage by userID{}", books);
        return books
                .stream()
                .map(bookMapper::bookToBookDto)
                .toList();
    }

    @Override
    public List<BookDto> deleteBookById(Long id) {
        return bookDAO.deleteByUserId(id).stream()
                .map(bookMapper::bookToBookDto)
                .toList();
    }
}
