package com.edu.ulab.app.dto.web.request;

import lombok.Data;

@Data
public class BookRequest {
    private Long id;
    private String title;
    private String author;
    private long pageCount;
}
