package com.jogger.rs.role;

import java.util.List;

/**
 * Servis zaduzen za rad sa ulogama u sistemu.
 *
 * @author Jovan Virijevic
 */
public interface RoleServiceInterface {

    /**
     * Metoda koja pronalazi sve uloge u sistemu.
     *
     * @return listu rola u sistemu
     */
    List<Role> findAll();

    /**
     * Metoda koja pronalazi uloge na osnovu liste imena.
     *
     * @param roleNames lista imena uloga.
     * @return listu rola u sistemu
     */
    List<Role> findRolesByNames(List<String> roleNames);
}
