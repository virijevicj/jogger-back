package com.jogger.rs.dto;

import com.jogger.rs.role.Role;
import com.jogger.rs.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsersAndRolesDto {
    List<User> users;
    List<Role> roles;
}
