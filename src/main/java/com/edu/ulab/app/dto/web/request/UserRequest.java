package com.edu.ulab.app.dto.web.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {
    @Min(value = 1, message = "User id should be 1 or greater then 1")
    private Long id;
    @NotEmpty(message = "Users name can't be empty")
    private String fullName;
    @NotEmpty(message = "title can't be empty")
    private String title;
    @Min(value = 1, message = "users age should be greater then 0")
    private int age;
}
