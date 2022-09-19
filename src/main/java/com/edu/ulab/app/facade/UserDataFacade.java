package com.edu.ulab.app.facade;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.dto.web.request.BookRequest;
import com.edu.ulab.app.dto.web.response.UserBookDeleteResponse;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.dto.web.request.UserBookRequest;
import com.edu.ulab.app.dto.web.response.UserBookGetResponse;
import com.edu.ulab.app.dto.web.response.UserBookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class UserDataFacade {
    private final UserService userService;
    private final BookService bookService;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    public UserDataFacade(UserService userService,
                          BookService bookService,
                          UserMapper userMapper,
                          BookMapper bookMapper) {
        this.userService = userService;
        this.bookService = bookService;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }

    public UserBookResponse createUserWithBooks(UserBookRequest userBookRequest) {
        log.info("Got user book create request: {}", userBookRequest);
        UserDto userDto = userMapper.userRequestToUserDto(userBookRequest.getUserRequest());
        log.info("Mapped user request: {}", userDto);

        UserDto createdUser = userService.createUser(userDto);
        log.info("Created user: {}", createdUser);

        Optional<List<BookRequest>> booksId = Optional.ofNullable(userBookRequest.getBookRequests());
        List<Long> bookIdList = booksId.orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(createdUser.getId()))
                .peek(mappedBookDto -> log.info("mapped book: {}", mappedBookDto))
                .map(bookService::createBook)
                .peek(createdBook -> log.info("Created book: {}", createdBook))
                .map(BookDto::getId)
                .toList();
        log.info("Collected book ids: {}", bookIdList);

        return UserBookResponse.builder()
                .userId(createdUser.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse updateUserWithBooks(UserBookRequest userBookRequest) {
        return null;
    }

    public UserBookGetResponse getUserWithBooks(Long userId) {
        log.info("Got user id: {}", userId);
        UserDto userDto = userService.getUserById(userId);
        log.info("Mapped user dto from DB by id: {}", userDto);
        List<BookDto> bookDtos = bookService.getBooksByUserId(userId);
        log.info("Collected book DTOS from DB: {}", bookDtos);
        return UserBookGetResponse.builder()
                .userModel(userDto)
                .bookModels(bookDtos)
                .build();
    }

    public UserBookDeleteResponse deleteUserWithBooks(Long userId) {
        log.info("Got user id that need to be deleted with books: {}", userId);
        UserDto deletedUser = userService.deleteUserById(userId);
        log.info("Got user dto of deleted user: {}", deletedUser);
        List<BookDto> deletedBooks = bookService.deleteBookById(userId);
        log.info("Got deleted books of deleted user: {}", deletedBooks);
        return UserBookDeleteResponse.builder()
                .userModel(deletedUser)
                .bookModels(deletedBooks)
                .build();
    }
}
