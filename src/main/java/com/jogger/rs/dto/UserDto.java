package com.jogger.rs.dto;

import com.jogger.rs.user.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Integer keyUser;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    List<String> roles;

}
