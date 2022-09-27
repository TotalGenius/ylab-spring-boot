package com.edu.ulab.app.facade.service;


import com.edu.ulab.app.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto userDto);

    BookDto updateBook(BookDto userDto);

    List<BookDto> getBooksByUserId(Long userId);

    List<BookDto> deleteBooksByUserId(Long userId);

    BookDto getBookById(Long id);
}
