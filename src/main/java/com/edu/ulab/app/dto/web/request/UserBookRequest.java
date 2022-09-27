package com.edu.ulab.app.dto.web.request;

import lombok.Data;

import java.util.List;

@Data
public class UserBookRequest {
    private UserRequest userRequest;
    private List<BookRequest> bookRequests;
}
