package com.jogger.rs.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozitorijum za rad sa ulogama u sistemu.
 *
 * @author Jovan Virijevic
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Metoda koja pronalazi uloge u sistemu na osnovu liste imena uloga
     *
     * @param roleNames lista imena uloga
     * @return listu rola u sistemu
     */
    @Query("""
            select o from Role o
            where o.name in :roleNames
            """)
    List<Role> findRolesByRoleNames(List<String> roleNames);
}
