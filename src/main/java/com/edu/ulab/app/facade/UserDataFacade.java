package com.edu.ulab.app.facade;


import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.dto.web.request.BookRequest;
import com.edu.ulab.app.dto.web.response.*;

import com.edu.ulab.app.exception.IncorrectUserIdException;
import com.edu.ulab.app.exception.NotFoundUserInRequestException;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.facade.service.BookService;
import com.edu.ulab.app.facade.service.UserService;
import com.edu.ulab.app.dto.web.request.UserBookRequest;
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
        UserDto userDto = Optional.ofNullable(userMapper.userRequestToUserDto(userBookRequest.getUserRequest())).orElseThrow(()-> new NotFoundUserInRequestException());
        log.info("Mapped user request: {}", userDto);

        UserDto createdUser = userService.createUser(userDto);
        log.info("Created user: {}", createdUser);

        Optional<List<BookRequest>> booksId = Optional.ofNullable(userBookRequest.getBookRequests());

        List<BookResponse> bookResponses = booksId.orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(createdUser.getId()))
                .peek(mappedBookDto -> log.info("mapped book: {}", mappedBookDto))
                .map(bookService::createBook)
                .peek(createdBook -> log.info("Created book: {}", createdBook))
                .map(bookMapper::bookDtoToBookRequest)
                .toList();
        log.info("Collected book responses: {}", bookResponses);

        return UserBookResponse.builder()
                .userResponse(userMapper.userDtoToUserRequest(createdUser))
                .bookResponses(bookResponses)
                .build();
    }

    public UserBookResponse updateUserWithBooks(UserBookRequest userBookRequest) {

        UserDto userDto = Optional.ofNullable(userMapper.userRequestToUserDto(userBookRequest.getUserRequest())).orElseThrow(()-> new NotFoundUserInRequestException());
        log.info("Got user dto from request: {}", userDto);
        Long id = Optional.ofNullable(userDto.getId()).orElseThrow(()->new IncorrectUserIdException(userDto.getId()));
        if (id == 0) {
            log.error("Incorrect user Id:", userDto.getId());
            throw new IncorrectUserIdException(userDto.getId());

        } else {
            Optional<List<BookRequest>> bookRequests = Optional.ofNullable(userBookRequest.getBookRequests());
            log.info("Collected book requests: {}", bookRequests);
            List<BookResponse> bookResponses = bookRequests.orElse(Collections.emptyList()).stream()
                    .filter(Objects::nonNull)
                    .map(bookMapper::bookRequestToBookDto)
                    .peek(bookDto -> bookDto.setUserId(userDto.getId()))
                    .peek(bookService::updateBook)
                    .map(bookDto -> bookMapper.bookDtoToBookRequest(bookDto))
                    .toList();
            log.info("Collected book responses: {}", bookResponses);
            UserDto updatedUser = userService.updateUser(userDto);
            log.info("Collected user DTO: {}", updatedUser);
            return UserBookResponse.builder()
                    .userResponse(userMapper.userDtoToUserRequest(updatedUser))
                    .bookResponses(bookResponses)
                    .build();
        }
    }

    public UserBookResponse getUserWithBooks(Long userId) {
        log.error("Incorrect user id {}", userId);
        if (userId == 0) throw new IncorrectUserIdException(userId);
        log.info("Got user id: {}", userId);
        UserDto userDto = userService.getUserById(userId);
        log.info("Mapped user dto from DB by id: {}", userDto);
        List<BookResponse> bookResponses = bookService.getBooksByUserId(userId)
                .stream()
                .map(bookMapper::bookDtoToBookRequest)
                .toList();
        log.info("Collected book responses: {}", bookResponses);
        return UserBookResponse.builder()
                .userResponse(userMapper.userDtoToUserRequest(userDto))
                .bookResponses(bookResponses)
                .build();
    }

    public UserBookResponse deleteUserWithBooks(Long userId) {
        log.error("Incorrect user id {}", userId);
        if (userId == 0) throw new IncorrectUserIdException(userId);
        log.info("Got user id that need to be deleted with books: {}", userId);
        UserDto deletedUser = userService.deleteUserById(userId);
        log.info("Got user dto of deleted user: {}", deletedUser);
        List<BookResponse> deletedBooks = bookService.deleteBookById(userId)
                .stream()
                .map(bookMapper::bookDtoToBookRequest)
                .toList();
        log.info("Got deleted book responses of deleted user: {}", deletedBooks);
        return UserBookResponse.builder()
                .userResponse(userMapper.userDtoToUserRequest(deletedUser))
                .bookResponses(deletedBooks)
                .build();
    }
}
