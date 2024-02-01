package com.jogger.rs.role;

import java.util.List;

public interface RoleServiceInterface {

    List<Role> findAll();

    List<Role> findRolesByNames(List<String> roleNames);
}
