package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.dto.web.request.UserRequest;
import com.edu.ulab.app.dto.web.response.BookResponse;
import com.edu.ulab.app.dto.web.response.UserResponse;
import com.edu.ulab.app.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userRequestToUserDto(UserRequest userRequest);

    UserResponse userDtoToUserRequest(UserDto userDto);

    User userDtoToUser(UserDto user);

    UserDto userToUserDto(User user);

}
