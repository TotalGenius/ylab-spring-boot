package com.edu.ulab.app.dto.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String title;
    private int age;
}
