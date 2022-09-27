package com.edu.ulab.app.dto.web.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class BookRequest {
    @Min(value = 1, message = "Book id should be greater then 0")
    private Long id;
    @Min(value = 1, message = "user id in bookrequest should be greater then 0")
    private Long userId;
    @NotEmpty(message = "Books title can't be Empty")
    private String title;
    @NotEmpty(message = "Books author can't be empty")
    private String author;
    @Min(value = 1, message = "book page count should be greater then 0")
    private long pageCount;
}
