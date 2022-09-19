package com.edu.ulab.app.dto.web.response;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserUpdateResponse {
    UserDto userModel;
    List<BookDto> bookModels;
}
