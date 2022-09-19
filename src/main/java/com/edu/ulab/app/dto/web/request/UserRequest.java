package com.edu.ulab.app.dto.web.request;

import lombok.Data;

@Data
public class UserRequest {
    private String fullName;
    private String title;
    private int age;
}
