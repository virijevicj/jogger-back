package com.jogger.rs.user;

import com.jogger.rs.role.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;;

@Getter
@Setter
@ToString
public class UserSession {
    private Integer keyUser;
    List<Role> roles;
}
