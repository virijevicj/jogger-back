package com.jogger.rs.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("""
            select o from Role o
            where o.name in :roleNames
            """)
    List<Role> findRolesByRoleNames(List<String> roleNames);
}
